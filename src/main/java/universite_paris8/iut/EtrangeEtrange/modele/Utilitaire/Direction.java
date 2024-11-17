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
        if (deltaX < 0) {// Vérification des directions horizontales
            return GAUCHE;
        } else if (deltaX > 0) {
            return DROITE;
        }
        // Vérification des directions verticales
        if (deltaY < 0) {
            return HAUT;
        } else if (deltaY > 0) {
            return BAS;
        }


        // Si deltaX et deltaY sont tous les deux égaux à zéro, il n'y a pas de direction
        return null; // Ou une direction spéciale pour indiquer l'absence de mouvement
    }





}
