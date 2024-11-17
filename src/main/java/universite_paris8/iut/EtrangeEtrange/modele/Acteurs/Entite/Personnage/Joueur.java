package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Inventaire.GestionnaireInventaire;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Attaque;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.AttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Représente le joueur dans le jeu.
 */
public abstract class Joueur extends Entite implements Offensif {

    GestionnaireInventaire gestionnaireInventaire;
    protected Sac sac;

    private Attaque attaque;
    private AttaqueSpecial attaqueSpecial;

    private Set<Direction> directions;
    private Competences competences;

    protected Carquois carquois;
    private boolean estEntrainDeCourir;

    /**
     * Crée un nouveau joueur.
     *
     * @param pv             Les points de vie du joueur.
     * @param attaque        La valeur de l'attaque du joueur.
     * @param defense        La valeur de la défense du joueur.
     * @param attaqueSpecial La valeur de l'attaque spéciale du joueur.
     * @param defenseSpecial La valeur de la défense spéciale du joueur.
     * @param vitesse        La vitesse de déplacement du joueur.
     * @param objetMainDroite L'objet tenu dans la main droite du joueur.
     * @param x              La position horizontale du joueur dans le monde.
     * @param y              La position verticale du joueur dans le monde.
     * @param direction      La direction vers laquelle le joueur est orienté.
     * @param hitbox         La hitbox du joueur.
     */
    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, ObjetUtilisable objetMainDroite, double x, double y, Direction direction, Hitbox hitbox) {
        super(x, y, direction, pv,attaque,defense,attaqueSpecial,hitbox);

        this.gestionnaireInventaire = new GestionnaireInventaire(null,objetMainDroite,this.sac);
        this.competences = CreationArbre.arbres();
        this.estEntrainDeCourir = false;
        this.directions = new HashSet<>();
        this.attaque = new Attaque(attaque);
        this.attaqueSpecial = new AttaqueSpecial(attaqueSpecial);
    }



    @Override
    public double getAttaque() {
        return attaque.getAttaque();
    }

    @Override
    public double getAttaqueSpecial() {
        return attaqueSpecial.getAttaqueSpecial();
    }



    public boolean actionMainDroite() {
        if (this.gestionnaireInventaire.getObjetMainDroite()!= null) {
            if (this.gestionnaireInventaire.getObjetMainDroite() instanceof Arc arc ) {
                arc.setFleche(carquois.retourneUneFleche());
            }
            if (this.gestionnaireInventaire.getObjetMainDroite() instanceof ElementUtilisable){
                if (((ElementUtilisable)this.gestionnaireInventaire.getObjetMainDroite()).utilise(this))
                    this.gestionnaireInventaire.setObjetMainDroite(null);
                return true;
            }
        }
        return false;
    }

    @Override
    public void agir()
    {
        double coeff = 1;
        for (Direction direction1 : directions)
        {
            setDirection(direction1);

            if (estEntrainDeCourir)
                coeff = 1.3;


            seDeplace(coeff);
        }
    }


    public void lanceUnSort(int numSort)
    {
        if (this.gestionnaireInventaire.getObjetMainDroite() instanceof LivreMagique livreMagique)
        {
            Sortilege sortilege = livreMagique.getSortilege(numSort);

            if (sortilege != null)
                sortilege.utilise(this);
        }
    }
    @Override
    public void setDirection(Direction direction)
    {
        this.direction = direction;
        this.directions.add(direction);
    }

    public void enleveDirection(Direction direction)
    {
        this.directions.remove(direction);
    }
    @Override
    public boolean estUnEnemie(){return false;}
    @Override
    public String typeActeur(){ return "Joueur" ;}

    public void estEntrainDeCourir(boolean bool)
    {
        if (TypeCompetence.COURIR.getCompetence().estDebloquer())
        {
            this.estEntrainDeCourir = bool;
        }
    }
    public Competences getCompetences() { return this.competences; }

    public int getPiece(){
        int totalPiece = 0;
        for(int i = 0 ; i < sac.getTailleMax() ; i++){
            if(sac.getEmplacement(i).nomObjet()=="pieceor")
                totalPiece+= sac.getEmplacement(i).quantiteObjet();
        }

        return totalPiece;
    }

    public Sac getSac() {
        return this.sac;
    }

    public void ramasserObjet() {
        ArrayList<DropAuSol> dropAuSols = Environnement.getInstance().getDropAuSol();

        for(int i = 0 ; i < dropAuSols.size() ; i++){
            Position position1 = dropAuSols.get(i).getPosition();
            if(Math.abs(getPosition().getX()+getDirection().getX()-position1.getX())<1)
                if(Math.abs(getPosition().getY()+getDirection().getY()-position1.getY())<1) {
                    if (sac.ajoutItem(dropAuSols.get(i).getObjet())) {
                        Environnement.getInstance().enleverDropAuSol(dropAuSols.get(i));
                        i++;
                    }
                }
        }
    }

    public GestionnaireInventaire getGestionnaireInventaire() {
        return gestionnaireInventaire;
    }
}