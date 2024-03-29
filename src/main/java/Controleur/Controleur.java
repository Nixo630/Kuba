package Controleur;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import Model.*;

public class Controleur extends MouseAdapter{

	public static boolean isTurnIA;
    Model model;
    int positionDepartX = -1;
    int positionDepartY = -1;
    
    int positionArriveX = -1;
    int positionArriveY = -1;
    
    int SIZE;


    public Controleur(Model m,int n){
        this.model = m;
        SIZE = n;
    }

    public void setModel(Model m){
        model = m;
    }
    
    private static Direction detailDirectionEstWest(Position depart, Position arrive, Direction dir) {
    	int detailX = arrive.getI() - depart.getI();
    	int detailY = arrive.getJ() - depart.getJ();
    	if (Math.abs(detailX)>Math.abs(detailY)){
    		if(detailX<=0) {
    			return Direction.WEST;
    		}
    		return Direction.EAST;
        }
    	return dir;
    }

    static Direction determineDirection(Position depart, Position arrive){
		if(depart.getJ()==arrive.getJ() && depart.getI() == arrive.getI()){
			return Direction.INVALID;
		}
        if (depart.getJ()<arrive.getJ()){
            return detailDirectionEstWest(depart,arrive,Direction.SOUTH);
        }
        else{
			return detailDirectionEstWest(depart,arrive,Direction.NORTH);
			
        }
    }

    
    public State move(Position depart, Position arrive){
        Direction direction = determineDirection(depart,arrive);
		State retour = State.SUCCESS;
        if(!model.isEnd()){
        	retour = model.push(new Position(depart.getJ(), depart.getI()),direction);
		}
		return retour;
    }
    
    
	@Override
    public synchronized void mouseReleased(MouseEvent e) {
        if (isTurnIA) {
            resetPosition();
            return;
        }

        if(model.tourIA()){
            Move m = model.determineBestMove();
            model.push(m.pos, m.dir);
            isTurnIA = true;
            Timer wait = new Timer();
            wait.schedule(new TimerTask() {
                int time = 1;
                public void run() {
                    if (time == 0) {
                        Controleur.isTurnIA = false;
                        return;
                    }
                    time--;
                }
            },0,750);
        }
        else{
            Position p1 = new Position(positionDepartX,positionDepartY);
            Position p2 = new Position(positionArriveX,positionArriveY);
            move(p1,p2);
        }
        resetPosition();
    }
	@Override
	public void mousePressed(MouseEvent e) {
		
		positionDepartX = e.getX()/SIZE;
		positionDepartY = e.getY()/SIZE;
		
		positionArriveX = positionDepartX;
		positionArriveY = positionDepartY;
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		if (positionDepartX != -1 && positionDepartY != -1) {
			if (positionDepartX != e.getX()/SIZE || positionDepartY != e.getY()/SIZE) {
				positionArriveX = e.getX()/SIZE;
				positionArriveY = e.getY()/SIZE;
			}
		}
		
	}
	
	public void resetPosition() {
		positionDepartX = -1;
		positionDepartY = -1;
		
		positionArriveX = -1;
		positionArriveY = -1;
	}
}



