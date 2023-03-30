package Model;

import GUI.View;

import java.util.LinkedList;


public class Model implements Observe<Data>,Data{
    public Plateau plat;
    public Joueur[] joueurs;
    public int joueurCurrent = 0; //L'entier indique le joueur courant
    public boolean partieFinie;
    public int n;
    public State state;
    public LinkedList<Observeur<Data>> observeurs;
    private boolean estDefi ;


    public Model(int n, boolean x){
        observeurs= new LinkedList<>();
        joueurs = new Joueur[2];
        estDefi = x;

        if (x)  plat = new Defi(n);
        else plat = new Plateau(n);

        Joueur j1 = new Joueur(Colour.WHITE,n);
        Joueur j2 = new Joueur(Colour.BLACK,n);
        state=State.SUCCESS;
        joueurs[0] = j1;
        joueurs[1] = j2;
        this.n = n;
        
    }

    public void initialiseBille(){
        plat.initialiseBille();

    }

    public void setView(View v){
        addObserveur(v);
        plat.initialiseBille();
        noticeObserveurs(this);
    }

    public void setView2(View v){
        addObserveur(v);
        noticeObserveurs(this);
    }

    public int getN(){
        return n;
    }

    public Joueur getCurrentPlayer(){
        return joueurs[joueurCurrent];
    }

    public Joueur getOtherPlayer(){
        if (joueurCurrent==0){
            return joueurs[1];
        }
        return joueurs[0];
    }

    public void joueurSuivant(){
        joueurCurrent ++;
        if (joueurCurrent>=2){
            joueurCurrent = 0;
        }
    }

    public boolean isEnd(){
        return partieFinie;
    }

    public Plateau getPlateau(){
        return plat;
    }

    public void push(Position p,Direction d){
        state = plat.push(p, d, getCurrentPlayer(), getOtherPlayer());

        if(plat.isOver(joueurs[0],joueurs[1])==null){
            if(State.SUCCESS == state){
                joueurSuivant();
            }
        }
        else{
            partieFinie = true;
        }

        noticeObserveurs(this);
    }


    @Override
    public void addObserveur(Observeur<Data> obs) {
        if(!observeurs.contains(obs)){
            observeurs.add(obs);}
    }

    @Override
    public void noticeObserveurs(Data obj) {
        for (Observeur<Data> o: observeurs) {
            o.update(obj);
        }
    }

    @Override
    public Colour getMarble(int i, int j) {
        return plat.getColor(i,j);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Joueur getJoueur() {
        return getCurrentPlayer();
    }

    @Override
    public Joueur getVainqueur() {
        return plat.isOver(joueurs[0],joueurs[1]);
    }

    @Override
    public void reset(){
        partieFinie=false;
        plat.resetAll();
        plat.initialiseBille();
        for (int i = 0;i<2;i++){
            joueurs[i].resetData();
        }

        noticeObserveurs(this);
    }
}
