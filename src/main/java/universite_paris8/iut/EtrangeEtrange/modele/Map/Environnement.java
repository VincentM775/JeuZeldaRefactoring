package universite_paris8.iut.EtrangeEtrange.modele.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc.Bloc;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.GestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;
import universite_paris8.iut.EtrangeEtrange.controller.GestionActeur;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Environnement {
    private Monde monde;
    private ArrayList<Rechargeable> rechargeables = new ArrayList<>();
    private Joueur joueur;
    private ObservableList<DropAuSol> dropsAuSol;
    private ObservableList<Acteur> acteurs = FXCollections.observableArrayList();
    private ArrayList<Acteur> acteursAsupprimer = new ArrayList<>();

    /**
     * Méthode création de monde à partir d'une TiledMap
     * @param chemin
     * @param nommap
     */
    public Environnement(String chemin, String nommap, int hauteur, int largeur)
    {
        this.monde = new Monde(chemin, nommap, hauteur, largeur);
        this.dropsAuSol = FXCollections.observableArrayList();
    }

    public void creationMonstre(String chemin, String nommap, int hauteur){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin+"/"+nommap+"_monstres.csv"));
            String ligne;
            int ligneIndex = 0;

            while ((ligne = reader.readLine()) != null && ligneIndex < hauteur) {
                String[] block = ligne.split(",");

                for (int j = 0; j < hauteur && j < block.length; j++) {
                    int monstre = Integer.parseInt(block[j]);
                    Acteur acteur = null;
                    if (monstre != -1) {
                        if (monstre == 4)
                            acteur = new Marchand(this, j+0.5 ,ligneIndex+0.5, Direction.BAS);
                        else if(monstre == 2)
                            acteur = new Slime(this, j+0.5, ligneIndex+0.5, Direction.BAS, new Hitbox(0.25, 0.5));
                        else if(monstre == 3)
                            acteur = new Squelette(this, j+0.5, ligneIndex+0.5,  Direction.BAS, new Hitbox(0.5, 0.5),joueur , new Aetoile(this));
                        else if(monstre == 1)
                            acteur = new RoiSquelette(this, j+0.5 , ligneIndex+0.5, Direction.BAS);
                        else if(monstre == 0)
                            acteur = new Bloc(this, j+0.5, ligneIndex+0.5, Direction.BAS, 1, 1, new Hitbox(1,1 ));
                        ajoutActeur(acteur);
                    }
                }

                ligneIndex++;
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format dans le fichier : " + e.getMessage());
        }
    }

    public void exerceCollision(Acteur acteur)
    {
        for (Acteur acteur2 : acteurs)
        {
            if (collisionAvecActeur(acteur,acteur2) && acteur != acteur2)
            {
                acteur2.subitCollision(acteur);
            }
        }

        if (collisionAvecActeur(acteur,joueur) && acteur != joueur)
        {
            joueur.subitCollision(acteur);
        }
    }

    public Acteur chercheEnemie()
    {
        Acteur acteur = null;

        for (Acteur act : acteurs)
        {
            if (act.estUnEnemie())
                acteur = act;
        }

        return acteur;
    }

    public void ajoutActeurAsupprimer(Acteur acteur)
    {
        this.acteursAsupprimer.add(acteur);
    }

    public void ajouterDropAuSol(DropAuSol dropAuSol){
        this.dropsAuSol.add(dropAuSol);
    }
    public ArrayList<DropAuSol> getDropAuSol() {return new ArrayList<>(this.dropsAuSol);}
    public void enleverDropAuSol(DropAuSol dropAuSol){
        this.dropsAuSol.remove(dropAuSol);
    }
    public void setJoueur(Joueur joueur){this.joueur = joueur;}
    public Joueur getJoueur(){return this.joueur;}

    public void ajoutActeur(Acteur acteur) {this.acteurs.add(acteur);}

    public void unTour()
    {
        this.joueur.unTour();

        for(int i = acteurs.size()-1 ; i>=0 ; i--)
            acteurs.get(i).unTour();

        for (Acteur acteur : acteursAsupprimer) {
            enleveActeur(acteur);
        }
        this.acteursAsupprimer.clear();

        for(int i = rechargeables.size()-1 ; i>=0 ; i--)
        {
            Rechargeable rechargeable = rechargeables.get(i);

            if (rechargeable.cooldown())
                this.rechargeables.remove(rechargeable);
        }
    }

    public void ajoutRechargeable(Rechargeable rechargeable)
    {
        this.rechargeables.add(rechargeable);
    }
    public boolean collisionAvecActeur(Acteur acteur1,Acteur acteur2)
    {
        double vitesse = acteur1.getVitesse();

        Position pos1 = acteur1.getPosition();
        Hitbox hitbox1 = acteur1.getHitbox();

        Position pos2 = acteur2.getPosition();
        Hitbox hitbox2 = acteur2.getHitbox();

        double dx = acteur1.getDirection().getX() * vitesse;
        double dy = acteur1.getDirection().getY() * vitesse;

        double x1Min = hitbox1.getPointLePlusAGauche(pos1.getX() + dx);
        double y1Min = hitbox1.getPointLePlusEnHaut(pos1.getY() + dy);
        double x1Max = hitbox1.getPointLePlusADroite(pos1.getX() + dx);
        double y1Max = hitbox1.getPointLePlusEnBas(pos1.getY() + dy);

        double x2Min = hitbox2.getPointLePlusAGauche(pos2.getX());
        double y2Min = hitbox2.getPointLePlusEnHaut(pos2.getY());
        double x2Max = hitbox2.getPointLePlusADroite(pos2.getX());
        double y2Max = hitbox2.getPointLePlusEnBas(pos2.getY());

        boolean collisionX = x1Min < x2Max && x1Max > x2Min;
        boolean collisionY = y1Min < y2Max && y1Max > y2Min;

        return collisionX && collisionY;
    }


    public boolean collision(Acteur acteur) { return this.monde.collisionMap(acteur) || collisionAvecActeurs(acteur); }

    private boolean collisionAvecActeurs(Acteur acteur1)
    {
        boolean aCollision = false;

        for (int i = 0 ; i < acteurs.size() && !aCollision;i++)
        {
            Acteur acteur2 = acteurs.get(i);

            if (collisionAvecActeur(acteur1,acteur2) && acteur2 != acteur1)
                aCollision = true;
        }
        if(collisionAvecActeur(acteur1,joueur) && joueur != acteur1)
            aCollision = true;

        return aCollision;
    }

    public void setListenerActeur(GestionActeur listenerActeur)
    {
        this.acteurs.addListener(listenerActeur);
    }
    public void setListenerListeDropsAuSol(GestionAffichageSpriteDropAuSol gestionAffichageDropAuSol) {
        this.dropsAuSol.addListener(gestionAffichageDropAuSol);
    }

    public boolean estDansRayon(Position positionCentre, double rayon){
        Position positionJoueur = getJoueur().getPosition();

        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - positionCentre.getX(), 2) + Math.pow(positionJoueur.getY() - positionCentre.getY(), 2));

        return distance <= rayon;
    }

    public void setListenerListeEntites(GestionAffichageSpriteEntite gestionAffichageSprite) {
        this.acteurs.addListener(gestionAffichageSprite);
    }

    public void enleveActeur(Acteur acteur) {
        acteur.dropApresMort();
        this.acteurs.remove(acteur);
    }

    public Acteur interactionAvecActeur()
    {
        Acteur act = null;
        double distance = -1;


        for (Acteur acteur : acteurs)
        {
            double distancePretendant = estEnFace(acteur);

             if (distancePretendant > distance)
             {
                 act = acteur;
                 distance = distancePretendant;
             }
        }

        return act;
    }

    private double estEnFace(Acteur acteur)
    {
        final double yDistanceMax = 2;
        final double xDistanceMax = 2;

        double dX = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
        double dY = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        Direction directionJoueur = joueur.getDirection();

        if (directionJoueur == Direction.GAUCHE ||directionJoueur == Direction.DROITE) {
            double pivot = dX;
            dX = dY;
            dY = pivot;
        }

        return dX <= xDistanceMax && dY <= yDistanceMax ?  dX+dY : -1;
    }

    public Monde getMonde() {
        return monde;
    }

}















































