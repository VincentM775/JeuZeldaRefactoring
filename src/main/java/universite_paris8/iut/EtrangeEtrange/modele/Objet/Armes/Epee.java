package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Epee extends Acteur implements Dommageable,Rechargeable,Arme
{

    private static final int DURABILITE = ConstanteObjet.DURABILITE_EPEE;
    private static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_EPEE;
    private static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_EPEE;
    private static final double VITESSE = ConstanteObjet.VITESSE_EPEE;
    private static final Hitbox HITBOX = ConstanteObjet.HITBOX_EPEE;
    private static final long DELAIE_UTILISATION = ConstanteObjet.DELAIE_UTILISATION_EPEE;
    private final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_EPEE;
    private final int STACK_MAX = ConstanteObjet.STACK_MAX_EPEE;

    private boolean peutTaper;
    private short cycle;
    private long derniereApelle;
    private Entite utilisateur;


    public Epee()
    {
        super(DURABILITE, VITESSE, HITBOX);
        this.peutTaper = true;
        this.cycle = 0;
        this.derniereApelle = 0;
    }

    @Override
    public void utilise(Entite entite)
    {
        if (peutTaper)
        {
            utilisateur = entite;
            setPosition(entite.getPosition());
            setMonde(entite.getMonde());
            setDirection(entite.getDirection());

            setPositionAttaque();
            entite.getMonde().ajoutActeur(this);

            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);

            this.peutTaper = false;
        }
    }

    @Override
    public void unTour()
    {
        if (cycle <= 2)
        {
            seDeplace(1);
            cycle++;
        }
        else
        {
            this.getMonde().enleveActeur(this);
            cycle = 0;
        }
    }

    private void setPositionAttaque()
    {
        double posXJoueur = position.getX();
        double posYJoueur = position.getY();

        double decalageX = 0;
        double decalageY = 0;

        switch (direction)
        {
            case HAUT:
                posXJoueur = hitbox.getPointLePlusADroite(posXJoueur);
                posYJoueur = hitbox.getPointLePlusEnHaut(posYJoueur);
                decalageY = -hitbox.getHauteur();
                break;
            case BAS:
                posXJoueur = hitbox.getPointLePlusADroite(posXJoueur);
                posYJoueur = hitbox.getPointLePlusEnBas(posYJoueur);
                decalageY = hitbox.getHauteur();
                break;
            case DROITE:
                posXJoueur = hitbox.getPointLePlusEnBas(posXJoueur);
                posYJoueur = hitbox.getPointLePlusADroite(posYJoueur);
                decalageX = hitbox.getLargeur();
                break;
            case GAUCHE:
                posXJoueur = hitbox.getPointLePlusEnBas(posXJoueur);
                posYJoueur = hitbox.getPointLePlusAGauche(posYJoueur);
                decalageX = -hitbox.getLargeur();
                break;
        }

        this.position = new Position(posXJoueur+decalageX,posYJoueur+decalageY);
    }

    @Override
    public void seDeplace(double multiplicateur)
    {
        double x = this.direction.getX();
        double y = this.direction.getY();

        position.setX(position.getX() + x * VITESSE * multiplicateur);
        position.setY(position.getY() + y * VITESSE * multiplicateur);
    }

    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this,(EntiteOffensif) utilisateur);
        monde.ajoutActeurAsupprimer(this);
    }

    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }
    @Override
    public boolean peutSeDeplacer() { return true; }

    @Override
    public String typeActeur() {
        return "epee";
    }

    @Override
    public long delaie() {
        return DELAIE_UTILISATION;
    }

    @Override
    public boolean cooldown()
    {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie())
        {
            this.derniereApelle = -1;
            this.peutTaper = true;
            actionFait = true;
        }

        return actionFait;
    }

    @Override
    public String getNom() {
        return "epee";
    }

    @Override
    public int stackMax() {
        return STACK_MAX;
    }

    @Override
    public double durabilitee() {
        return getPv();
    }

    @Override
    public double degatPhysique() {
        return DEGAT_PHYSIQUE;
    }

    @Override
    public double degatSpecial() {
        return DEGAT_SPECIAL;
    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }


    @Override
    public void subitAttaque(Dommageable causeDegat, EntiteOffensif entiteOffensif) {  /*  NE FAIT RIEN */ }
    @Override
    public void subitCollision(Acteur acteur) {/*NE FAIT RIEN*/}
    @Override
    public void seFaitPousser(Acteur acteur) {/*NE FAIT RIEN*/}
    @Override
    public void dropApresMort() {/*NE FAIT RIEN*/}

}
