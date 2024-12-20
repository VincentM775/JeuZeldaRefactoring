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
        Objet o1;
        Objet o2;

        if(caseSurvole==tailleInventaire)
            o1 = joueur.retournerObjetMainDroite();
        else if (caseSurvole==tailleInventaire+1)
            o1 = joueur.retournerObjetMainGauche();
        else
            o1 = retourneObjet(caseSurvole);
        if(caseVerouille==tailleInventaire)
            o2 = joueur.retournerObjetMainDroite();
        else if (caseVerouille==tailleInventaire+1)
            o2 = joueur.retournerObjetMainGauche();
        else
            o2 = retourneObjet(caseVerouille);

        if(o2!=null) {
            if (caseSurvole == tailleInventaire)
                joueur.setObjetMainDroite(o2);
            else if (caseSurvole == tailleInventaire + 1)
                joueur.setObjetMainGauche(o2);
            else
                getEmplacement(caseSurvole).ajoutObjet((T) o2);
        }
        if(o1!=null){
            if(caseVerouille==tailleInventaire)
                joueur.setObjetMainDroite(o1);
            else if(caseVerouille==tailleInventaire+1)
                joueur.setObjetMainGauche(o1);
            else
                getEmplacement(caseVerouille).ajoutObjet((T)o1);
        }

    }
}
