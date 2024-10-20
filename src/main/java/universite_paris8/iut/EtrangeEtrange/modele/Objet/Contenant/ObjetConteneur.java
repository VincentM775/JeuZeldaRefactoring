package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

public abstract class ObjetConteneur<T extends ElementStockable> extends Inventaire<T> implements ElementStockable
{
    public ObjetConteneur(int taille)
    {
        super(taille);
    }


    public void echangerEmplacement(Joueur joueur, int caseVerouille, int caseSurvole)
    {
        int tailleInventaire = joueur.getSac().getTailleMax();

        ElementStockable o1 = getObjet(joueur, caseSurvole, tailleInventaire);
        ElementStockable o2 = getObjet(joueur, caseVerouille, tailleInventaire);

        if(o2!=null)
            ajoutObjet(o2, joueur, caseSurvole, tailleInventaire);
        if(o1!=null)
            ajoutObjet(o1, joueur, caseVerouille, tailleInventaire);
    }

    public ElementStockable getObjet(Joueur joueur, int emplacement, int tailleInventaire){
        if(emplacement==tailleInventaire)
            return joueur.retournerObjetMainDroite();
        else if (emplacement==tailleInventaire+1)
            return joueur.retournerObjetMainGauche();
        return retourneObjet(emplacement);
    }

    public void ajoutObjet(ElementStockable ob, Joueur joueur, int emplacement, int tailleInventaire){
        if (emplacement == tailleInventaire)
            joueur.setObjetMainDroite(ob);
        else if (emplacement == tailleInventaire + 1)
            joueur.setObjetMainGauche(ob);
        else
            getEmplacement(emplacement).ajoutObjet((T) ob);
    }
}
