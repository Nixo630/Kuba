package Model;

import GUI.View;

public class Test {
	
	public static void main(String[] args) {
		
		//Joueur j1 = new Joueur(Colour.BLACK,3);
		//Joueur j2 = new Joueur(Colour.WHITE,3);
		/* 
		Plateau p = new Plateau(3);
		p.initialiseBille();
		p.push(new Position(0,0),Direction.SOUTH,j1,j2);
		p.affiche();
		State test = p.push(new Position(3,0),Direction.NORTH,j1,j2);
		p.affiche();
		System.out.println(test);
		//p.push(new Position(0,0),Direction.SOUTH,j1,j2)
		//IA ia = new IA(Color.BLACK,3);
		*/


		//--------------------------TEST MVC-----------------
		//Plateau p = new Plateau(3);
		//p.initialiseBille();
		//System.out.println("State : "+p.push(new Position(0,9),Direction.SOUTH,j1,j2));
		
		//p.affiche();


		Model m = new Model(7);

		View v = new View(m);
		m.setView(v);

	}
}