package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Fleche extends Projectile
{

    public static final double DEGAT_PHYSIQUE_FLECHE = 20;
    public static final double DEGAT_SPECIAL_FLECHE = 0;
    public static final double VITESSE_FLECHE = 0.05;
    public static final Hitbox HITBOX_FLECHE = new Hitbox(0.25,0.25);
    public static final int DURABILITE_FLECHE = 10;
    public static final int PRIX_ACHAT_FLECHE = 12;
    public static final int STACK_MAX_FLECHE = 64;

    public Fleche()
    {
        super(null, DURABILITE_FLECHE,VITESSE_FLECHE,HITBOX_FLECHE);
    }

    @Override
    public double degatPhysique() {
        return DEGAT_PHYSIQUE_FLECHE;
    }
    @Override
    public double degatSpecial() {
        return DEGAT_SPECIAL_FLECHE;
    }
    @Override
    public String getNom() {
        return "fleche";
    }

    @Override
    public String typeActeur(){
        return "fleche";
    }

    @Override
    public void derniereAction() {

    }
    @Override
    public boolean estUnEnemie() {
        return false;
    }
    @Override
    public int stackMax() {return STACK_MAX_FLECHE;}
    @Override
    public double durabilitee(){ return getPv(); }
    @Override
    public int prixAchat() { return PRIX_ACHAT_FLECHE; }

}