package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Inventaire.GestionnaireInventaire;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente le joueur dans le jeu.
 */
public abstract class Joueur extends EntiteOffensif {
    private Set<Direction> directions;
    private Competences competences;
    protected Carquois carquois;
    private boolean estEntrainDeCourir;
    private GestionnaireInventaire gestionnaireInventaire;

    /**
     * Crée un nouveau joueur.
     *
     * @param pv             Les points de vie du joueur.
     * @param attaque        La valeur de l'attaque du joueur.
     * @param defense        La valeur de la défense du joueur.
     * @param attaqueSpecial La valeur de l'attaque spéciale du joueur.
     * @param defenseSpecial La valeur de la défense spéciale du joueur.
     * @param vitesse        La vitesse de déplacement du joueur.
     * @param sac            Le sac à dos du joueur.
     * @param objetMainGauche   L'objet tenu dans la main gauche du joueur.
     * @param objetMainDroite L'objet tenu dans la main droite du joueur.
     * @param monde          Le monde dans lequel évolue le joueur.
     * @param x              La position horizontale du joueur dans le monde.
     * @param y              La position verticale du joueur dans le monde.
     * @param direction      La direction vers laquelle le joueur est orienté.
     * @param hitbox         La hitbox du joueur.
     */
    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Sac sac, ObjetUtilisable objetMainGauche, ObjetUtilisable objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(monde, x, y, direction, pv,attaque,defense,attaqueSpecial,defenseSpecial,vitesse,hitbox);
        this.competences = CreationArbre.arbres();
        this.estEntrainDeCourir = false;
        this.directions = new HashSet<>();
        this.carquois = new Carquois();
        this.gestionnaireInventaire = new GestionnaireInventaire(objetMainGauche,objetMainDroite,sac);
        creerCarquois();
    }
    public void creerCarquois(){
        for(int i = 0 ; i < 100 ; i++){
            carquois.ajoutItem(new Fleche());
        }
    }

    public boolean actionMainDroite()
    {
        this.gestionnaireInventaire.getObjetMainDroite().utilise().uti
        boolean utilisation = false;
        if (this.gestionnaireInventaire.getObjetMainDroite() != null)
        {
            if (this.gestionnaireInventaire.getObjetMainDroite() instanceof ElementUtilisable utilisable)
            {
                if (this.gestionnaireInventaire.getObjetMainDroite() instanceof ObjetUtilisable)
                    attaque();

                utilisation =  utilisable.utilise(this);

                if (this.gestionnaireInventaire.getObjetMainDroite().durabilitee() == 0)
                    this.gestionnaireInventaire.setObjetMainDroite(null);

            }
        }
        return utilisation;
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

    @Override
    public void attaque()
    {
        if (this.gestionnaireInventaire.getObjetMainDroite() instanceof Arc arc)
        {
            Fleche flecheSimple = carquois.retourneUneFleche();

            if (flecheSimple != null)
                arc.setFleche(flecheSimple);
        }
    }

    @Override
    public void lanceUnSort(int numSort)
    {
        if (this.gestionnaireInventaire.getObjetMainDroite() instanceof LivreMagique livreMagique)
        {
            Sortilege sortilege = livreMagique.getSortilege(numSort);
            if (sortilege != null) {
                livreMagique.setSortilege(sortilege);
                livreMagique.utilise(this);
            }
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
        for(int i = 0 ; i < this.gestionnaireInventaire.getSac().getTailleMax() ; i++){
            if(this.gestionnaireInventaire.getSac().getEmplacement(i).nomObjet().equals("pieceor"))
                totalPiece+= this.gestionnaireInventaire.getSac().getEmplacement(i).quantiteObjet();
        }

        return totalPiece;
    }

    public GestionnaireInventaire getGestionnaireInventaire() {
        return gestionnaireInventaire;
    }
}
