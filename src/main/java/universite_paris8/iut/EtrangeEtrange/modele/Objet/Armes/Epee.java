package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementDommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Epee extends Acteur implements ElementDommageable, ObjetUtilisable
{

    public static final double DEGAT_PHYSIQUE_EPEE = 20;
    public static final double DEGAT_SPECIAL_EPEE = 0;
    public static final double VITESSE_EPEE = 0.125;
    public static final Hitbox HITBOX_EPEE = new Hitbox(0.25,0.25);
    public static final int DURABILITE_EPEE = 10;
    public static final int PRIX_ACHAT_EPEE = 12;
    public static final long DELAIE_UTILISATION_EPEE = 1000;
    public static final int STACK_MAX_EPEE = 1;

    private short cycle;
    private Entite utilisateur;
    private Cooldown cooldown;


    public Epee()
    {
        super(DURABILITE_EPEE, VITESSE_EPEE, HITBOX_EPEE);
        this.cooldown = new Cooldown(DELAIE_UTILISATION_EPEE);
        this.cycle = 0;
    }

    @Override
    public boolean utilise(Entite entite)
    {
        if (this.cooldown.delaieEcoule())
        {
            utilisateur = entite;
            setPosition(entite.getPosition());
            setDirection(entite.getDirection());

            setPositionAttaque();
            Monde.getInstance().ajoutActeur(this);

            this.cooldown.reset();
            return true;
        }
        return false;
    }


    @Override
    public void agir()
    {
        if (cycle <= 2)
        {
            seDeplace(1);
            cycle++;
        }
        else
        {
            Monde.getInstance().enleveActeur(this);
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

        position.setX(position.getX() + x * VITESSE_EPEE * multiplicateur);
        position.setY(position.getY() + y * VITESSE_EPEE * multiplicateur);
    }


    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this,(EntiteOffensif) utilisateur);
        Monde.getInstance().ajoutActeurAsupprimer(this);
    }

    @Override
    public void subitAttaque(ElementDommageable causeDegat, EntiteOffensif entiteOffensif) {
        //NE FAIS RIEN
    }

    @Override
    public int prixAchat() {
        return PRIX_ACHAT_EPEE;
    }
    @Override
    public boolean peutSeDeplacer() { return true; }

    @Override
    public String typeActeur() {
        return "epee";
    }

    @Override
    public void derniereAction() {
        
    }

    @Override
    public String getNom() {
        return "epee";
    }

    @Override
    public int stackMax() {
        return STACK_MAX_EPEE;
    }

    @Override
    public double durabilitee() {
        return getPv();
    }


    @Override
    public double degatPhysique() {
        return DEGAT_PHYSIQUE_EPEE;
    }

    @Override
    public double degatSpecial() {
        return DEGAT_SPECIAL_EPEE;
    }

    @Override
    public void seFaitPousser(Acteur acteur) {/*NE FAIT RIEN*/}

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public void subitCollision(Acteur acteur) {/*NE FAIT RIEN*/}

}
