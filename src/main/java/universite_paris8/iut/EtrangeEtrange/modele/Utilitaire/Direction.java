package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import java.util.ArrayList;
import java.util.Set;

public enum  Direction
{


    GAUCHE(-1,0),
    DROITE(1,0),
    HAUT(0,-1),
    BAS(0,1);


    private int x,y;
    private Direction(int x,int y)
    {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
    public static Direction randomDirection(){
        double random = Math.random();
        if(random<0.25)
            return GAUCHE;
        if(random<0.5)
            return DROITE;
        if(random<0.75)
            return HAUT;
        return BAS;
    }

    public static Direction calculerDirection(double deltaX, double deltaY) {
        double tolerance = 0.3;



        if (Math.abs(deltaX) > tolerance) {
            return deltaX < 0 ? GAUCHE : DROITE;
        }

        if (Math.abs(deltaY) > tolerance) {
            return deltaY < 0 ? HAUT : BAS;
        }

        return null;
    }





}
