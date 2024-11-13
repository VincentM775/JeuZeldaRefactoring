package universite_paris8.iut.EtrangeEtrange.modele.Map;

import javafx.collections.FXCollections;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Monde {

    /**
     * Taille du monde (généré aléatoirement)
     * Ces valeurs ne servent que pour tester le fonctionnement de la scrolling map, elles seront supprimées lorsque les tests seront finis.
     */
    private static final int sizeMondeHauteur = 70;
    private static final int sizeMondeLargeur = 70;
    private static final double xPointDeDepart = 7;
    private static final double yPointDeDepart = 7;
    private int[][] sol;
    private int[][] traversable;
    private int[][] nontraversable;
    /**
     * Ici sont stocké les informations des éléments du monde traversables (ex : buissons, fleurs, hautes herbes, etc.)
     */

    public Monde(String chemin, String nommap, int hauteur, int largeur){
        this.sol = new int[hauteur][largeur];
        this.traversable = new int[hauteur][largeur];
        this.nontraversable = new int[hauteur][largeur];

        ArrayList<int[][]> coucheMap = new ArrayList<>();
        coucheMap.add(this.sol);
        coucheMap.add(this.traversable);
        coucheMap.add(this.nontraversable);

        String[] fichiers = {"sol", "traversable", "nontraversable"};

        for(int i = 0 ; i < coucheMap.size() ; i++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(chemin+"/"+nommap+"_"+fichiers[i]+".csv"));
                String ligne;
                int ligneIndex = 0;

                while ((ligne = reader.readLine()) != null && ligneIndex < hauteur) {
                    String[] block = ligne.split(",");

                    for (int j = 0; j < hauteur && j < block.length; j++)
                        coucheMap.get(i)[ligneIndex][j] = Integer.parseInt(block[j]);

                    ligneIndex++;
                }

            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Erreur de format dans le fichier : " + e.getMessage());
            }
        }
    }
    public static double getxPointDeDepart(){
        return xPointDeDepart;
    }
    public static double getyPointDeDepart() {
        return yPointDeDepart;
    }
    public static int getSizeMondeHauteur(){
        return sizeMondeHauteur;
    }
    public static int getSizeMondeLargeur() {
        return sizeMondeLargeur;
    }

    public ArrayList<int[][]> getToutesLesCouches()
    {
        ArrayList<int[][] > couches = new ArrayList<>();
        couches.add(this.sol);
        couches.add(this.traversable);
        couches.add(this.nontraversable);
        return couches;
    }

    public int getTileType(int x, int y) {
        if (x >= 0 && x < sol[0].length && y >= 0 && y < sol.length) {
            return sol[y][x];
        } else {
            return -1;
        }
    }

    public boolean positionHorsMap(int x,int y)
    {
        return x < 0 || x >= sizeMondeLargeur || y < 0 || y >= sizeMondeHauteur;
    }

    public boolean estHorsMap(Acteur acteur)
    {
        boolean collision;

        Position position = acteur.getPosition();
        Direction direction = acteur.getDirection();
        Hitbox hitbox = acteur.getHitbox();
        double vitesse = acteur.getVitesse();

        if (direction == Direction.BAS) {
            collision = hitbox.getPointLePlusEnBas(position.getY()) + vitesse >= sizeMondeHauteur;
        } else if (direction == Direction.HAUT) {
            collision = hitbox.getPointLePlusEnHaut(position.getY()) - vitesse < 0;
        } else if (direction == Direction.DROITE) {
            collision = hitbox.getPointLePlusADroite(position.getX()) + vitesse >= sizeMondeLargeur;
        } else if (direction == Direction.GAUCHE) {
            collision = hitbox.getPointLePlusAGauche(position.getX()) - vitesse < 0;
        }
        else
        {
            collision = false;
        }

        return collision;
    }

    public boolean collisionMap(Acteur acteur) {
        Position position = acteur.getPosition();
        Direction direction = acteur.getDirection();
        Hitbox hitbox = acteur.getHitbox();
        double vitesse = acteur.getVitesse();

        double x = position.getX() + vitesse * direction.getX();
        double y = position.getY() + vitesse * direction.getY();

        double extremite1;
        double extremite2;

        if (direction == Direction.BAS || direction == Direction.HAUT) {
            extremite1 = hitbox.getPointLePlusAGauche(x);
            extremite2 = hitbox.getPointLePlusADroite(x);
        } else {
            extremite1 = hitbox.getPointLePlusEnHaut(y);
            extremite2 = hitbox.getPointLePlusEnBas(y);
        }

        boolean collision = false;
        int cpt = (int) extremite1;

        while (cpt <= extremite2 && !collision) {
            if (direction.equals(Direction.BAS)) {
                collision = nontraversable[(int) (hitbox.getPointLePlusEnBas(y))][cpt] != -1;
            } else if (direction.equals(Direction.HAUT)) {
                collision = nontraversable[(int) (hitbox.getPointLePlusEnHaut(y))][cpt] != -1;
            } else if (direction.equals(Direction.DROITE)) {
                collision = nontraversable[cpt][(int) (hitbox.getPointLePlusADroite(x))] != -1;
            } else if (direction.equals(Direction.GAUCHE)) {
                collision = nontraversable[cpt][(int) (hitbox.getPointLePlusAGauche(x))] != -1;
            }
            cpt++;
        }

        return collision;
    }

    public int[][] getNontraversable() {
        return nontraversable;
    }
}
