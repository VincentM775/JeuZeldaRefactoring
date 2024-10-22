package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

public abstract class ObjetConteneur<T extends Objet> extends Inventaire<T> implements Objet
{
    public ObjetConteneur(int taille)
    {
        super(taille);
    }


    public void echangerEmplacement(Joueur joueur, int caseVerouille, int caseSurvole)
    {
        int tailleInventaire = joueur.getGestionnaireInventaire().getSac().getTailleMax();
        Objet o1;
        Objet o2;

        if(caseSurvole==tailleInventaire)
            o1 = joueur.getGestionnaireInventaire().retournerObjetMainDroite();
        else if (caseSurvole==tailleInventaire+1)
            o1 = joueur.getGestionnaireInventaire().retournerObjetMainGauche();
        else
            o1 = retourneObjet(caseSurvole);
        if(caseVerouille==tailleInventaire)
            o2 = joueur.getGestionnaireInventaire().retournerObjetMainDroite();
        else if (caseVerouille==tailleInventaire+1)
            o2 = joueur.getGestionnaireInventaire().retournerObjetMainGauche();
        else
            o2 = retourneObjet(caseVerouille);

        if(o2!=null) {
            if (caseSurvole == tailleInventaire)
                joueur.getGestionnaireInventaire().setObjetMainDroite(o2);
            else if (caseSurvole == tailleInventaire + 1)
                joueur.getGestionnaireInventaire().setObjetMainGauche(o2);
            else
                getEmplacement(caseSurvole).ajoutObjet((T) o2);
        }
        if(o1!=null){
            if(caseVerouille==tailleInventaire)
                joueur.getGestionnaireInventaire().setObjetMainDroite(o1);
            else if(caseVerouille==tailleInventaire+1)
                joueur.getGestionnaireInventaire().setObjetMainGauche(o1);
            else
                getEmplacement(caseVerouille).ajoutObjet((T)o1);
        }

    }
}
