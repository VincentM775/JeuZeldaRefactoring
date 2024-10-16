package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementDommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

public abstract class Projectile extends Acteur implements ElementDommageable, ElementStockable
{
    private Entite utilisateur;

    public Projectile(Entite utilisateur, double pv,double vitesse,Hitbox hitbox)
    {
        super(pv,vitesse,hitbox);
        this.utilisateur = utilisateur;
    }

    @Override
    public void causeCollision(Acteur acteur)
    {
        if (acteur != utilisateur)
        {
            acteur.subitAttaque(this,(EntiteOffensif) utilisateur);
            enleveToutPv();
        }
    }

//    @Override
//    public void agir()
//    {
//        if(peutSeDeplacer())
//            seDeplace(1);
//        else
//            enleveToutPv();
//    }

    @Override
    public void subitCollision(Acteur acteur) {
        if (acteur != utilisateur)
            enleveToutPv();
    }

    @Override
    public boolean peutSeDeplacer() {
        return !monde.estHorsMap(this) && !monde.collisionMap(this);
    }
    public void setUtilisateur(Entite entite){this.utilisateur = entite;}
    @Override
    public void seFaitPousser(Acteur acteur) {}
    @Override
    public void subitAttaque(ElementDommageable causeDegat, EntiteOffensif entiteOffensif) {

    }

}