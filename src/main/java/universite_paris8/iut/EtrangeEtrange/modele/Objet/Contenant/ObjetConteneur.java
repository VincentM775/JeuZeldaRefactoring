package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Conteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

import java.util.ArrayList;

public abstract class ObjetConteneur<T extends Objet> extends Objet implements Conteneur<T>
{
    private Inventaire<T> inv;

    public ObjetConteneur(int taille)
    {
        this.inv = new Inventaire<>(taille);
    }
    @Override
    public boolean ajoutItem(T objet)
    {
       return this.inv.ajoutItem(objet);
    }

    @Override
    public void vider()
    {
        this.inv.vider();
    }

    @Override
    public boolean estPlein()
    {
        return this.inv.estPlein();
    }

    @Override
    public boolean estVide()
    {
        return this.inv.estVide();
    }

    @Override
    public int nombresObjets() {
        return this.inv.nombresObjets();
    }

    @Override
    public int getTailleMax() {
        return this.inv.getTailleMax();
    }

    @Override
    public ArrayList<T> retourneObjets(int emplacement) {
        return this.inv.retourneObjets(emplacement);
    }

    @Override
    public T retourneObjet(int emplacement) {
        return this.inv.retourneObjet(emplacement);
    }





}
