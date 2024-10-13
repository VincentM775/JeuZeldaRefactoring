package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import javafx.beans.property.IntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Conteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

import java.util.ArrayList;

public abstract class ObjetConteneur<T extends Objet> extends Inventaire<T> implements Objet
{
    public ObjetConteneur(int taille)
    {
        super(taille);
    }


    public void echangerEmplacement(Joueur joueur, int caseVerouille, int caseSurvole)
    {
        int tailleInventaire = joueur.getSac().getTailleMax();

        Objet o1 = getObjet(joueur, caseSurvole, tailleInventaire);
        Objet o2 = getObjet(joueur, caseVerouille, tailleInventaire);

        if(o2!=null)
            ajoutObjet(o2, joueur, caseSurvole, tailleInventaire);
        if(o1!=null)
            ajoutObjet(o1, joueur, caseVerouille, tailleInventaire);
    }

    public Objet getObjet(Joueur joueur, int emplacement, int tailleInventaire){
        if(emplacement==tailleInventaire)
            return joueur.retournerObjetMainDroite();
        else if (emplacement==tailleInventaire+1)
            return joueur.retournerObjetMainGauche();
        return retourneObjet(emplacement);
    }

    public void ajoutObjet(Objet ob, Joueur joueur, int emplacement, int tailleInventaire){
        if (emplacement == tailleInventaire)
            joueur.setObjetMainDroite(ob);
        else if (emplacement == tailleInventaire + 1)
            joueur.setObjetMainGauche(ob);
        else
            getEmplacement(emplacement).ajoutObjet((T) ob);
    }
}
