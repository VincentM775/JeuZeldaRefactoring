package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Attaque;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.AttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

/**
 * Représente un être-vivant dans le monde du jeu pouvant attaquer.
 */
public abstract class EntiteOffensif extends Entite
{
    protected Attaque statsAttaque;
    
    protected AttaqueSpecial statsAttaqueSpecial;


    /**
     * Crée une nouvelle entité offensif.
     *
     * @param x               La position horizontale de l'entité.
     * @param y               La position verticale de l'entité.
     * @param direction       La direction dans laquelle l'entité est orientée.
     * @param pv              Les points de vie de l'entité.
     * @param attaque         La valeur de l'attaque de l'entité.
     * @param defense         La valeur de la défense de l'entité.
     * @param attaqueSpecial  La valeur de l'attaque spéciale de l'entité.
     * @param defenseSpecial La valeur de la défense spéciale de l'entité.
     * @param vitesse         La vitesse de déplacement de l'entité.
     * @param hitbox          La hitbox de l'entité.
     */
    public EntiteOffensif(double x, double y, Direction direction, double pv, double attaque, double defense, double attaqueSpecial , double defenseSpecial, double vitesse,Hitbox hitbox)
    {
        super(x,y,direction,pv,defense,defenseSpecial,vitesse,hitbox);
        this.statsAttaque = new Attaque(attaque);
        this.statsAttaqueSpecial = new AttaqueSpecial(attaqueSpecial);
    }

    /**
     * Effectue une attaque avec une arme.
     *
     */
    public abstract void attaque();

    /**
     * Lance un sort.
     * @param numSort Le numéro du sort à lancer.
     */
    public abstract void lanceUnSort(int numSort);
    public void setAttaque(double attaque) {this.statsAttaque.setAttaque(attaque);}
    public void augmenterAttaqueMaximum(double attaque)
    {
        this.statsAttaque.setAttaqueMaximum(getAttaque()+attaque);
    }
    public double getAttaque(){ return this.statsAttaque.getAttaque();}
    public double getAttaqueSpecial(){ return this.statsAttaqueSpecial.getAttaqueSpecial();}




}
