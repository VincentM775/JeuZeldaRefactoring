package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteDefensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementDommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

public abstract class Projectile extends Acteur implements ElementDommageable, ElementStockable
{
    private EntiteDefensive utilisateur;

    public Projectile(EntiteDefensive utilisateur, double pv, double vitesse, Hitbox hitbox)
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

    @Override
    public void agir()
    {
        if(peutSeDeplacer())
            seDeplace(1);
        else
            enleveToutPv();
    }

    @Override
    public void subitCollision(Acteur acteur) {
        if (acteur != utilisateur)
            enleveToutPv();
    }

    @Override
    public boolean peutSeDeplacer() {
        return !Environnement.getInstance().getMonde().estHorsMap(this) && !Environnement.getInstance().getMonde().collisionMap(this);
    }
    public void setUtilisateur(EntiteDefensive entite){this.utilisateur = entite;}
    @Override
    public void seFaitPousser(Acteur acteur) {}
    @Override
    public void subitAttaque(ElementDommageable causeDegat, EntiteOffensif entiteOffensif) {

    }

}