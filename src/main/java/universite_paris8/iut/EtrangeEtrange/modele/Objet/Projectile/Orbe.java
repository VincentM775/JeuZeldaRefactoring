package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.*;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos.BFS;

public class Orbe extends Projectile
{
    public static final int DURABILITEE_ORBE = 10;

    public static final double DEGAT_PHYSIQUE_ORBE = 3;
    public static final double DEGAT_SPECIAL_ORBE = 7;
    public static final double VITESSE_ORBE = 0.05;
    public static final Hitbox HITBOX_ORBE = new Hitbox(0.2,0.2);
    public static final int PV_ORBE = 1;
    public static final int NOMBRE_UTLISATION_ORBE = 1;
    public static final int PRIX_ACHAT_ORBE = 15;
    public static final int STACK_MAX_ORBE = 64;
    public static final long DELAIE_CHERCHE_POSITION_ORBE = 10000;

    private final BFS bfs;
    private Position positionAsuivre;
    private Acteur acteurAsuivre;
    private Cooldown cooldownRechercheChemin;

    public Orbe(Entite utilisateur)
    {
        super(utilisateur, PV_ORBE,VITESSE_ORBE,HITBOX_ORBE);
        this.positionAsuivre = null;
        this.bfs = new BFS();
        this.acteurAsuivre = null;
        this.cooldownRechercheChemin = new Cooldown(BFS.DELAIE_RECHERCHE);
    }

    @Override
    public void agir()
    {
        chercheChemin();

        if (this.positionAsuivre != null)
        {
            double deltaX = this.positionAsuivre.getX() - getPosition().getX();
            double deltaY = this.positionAsuivre.getY() - getPosition().getY();

            if (Math.abs(deltaX) > Math.abs(deltaY))
                setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
            else
                setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);

            setSeDeplace(true);

            if (peutSeDeplacer())
                seDeplace(1);
            else
                enleveToutPv();

            if (positionAtteinte(this.positionAsuivre))
                this.positionAsuivre = this.bfs.prochainePosition();
        }
    }

    private boolean positionAtteinte(Position position)
    {
        return  this.position != null
                && Math.abs(getPosition().getX() - position.getX()) < 0.1
                && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    public void chercheChemin()
    {
        if(this.cooldownRechercheChemin.delaieEcoule() &&  this.acteurAsuivre != null) {
            this.bfs.chercherChemin(getPosition(), acteurAsuivre.getPosition());
            this.positionAsuivre = bfs.prochainePosition();
            this.cooldownRechercheChemin.reset();
        }
    }

    @Override
    public String typeActeur() { return "orbe"; }
    @Override
    public void derniereAction() {

    }
    @Override
    public boolean estUnEnemie() { return false; }
    @Override
    public double degatPhysique() { return DEGAT_PHYSIQUE_ORBE; }
    @Override
    public double degatSpecial() { return DEGAT_SPECIAL_ORBE; }
    @Override
    public String getNom() {return "orbe";}
    @Override
    public int stackMax() { return STACK_MAX_ORBE; }
    @Override
    public double durabilitee() {
        return DURABILITEE_ORBE;
    }
    @Override
    public int prixAchat() { return PRIX_ACHAT_ORBE; }
    @Override
    public boolean peutSeDeplacer() {return !Environnement.getInstance().getMonde().estHorsMap(this);}
}
