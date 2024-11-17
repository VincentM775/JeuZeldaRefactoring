package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;

public interface StockeurElement<T extends ElementStockable>
{
    boolean ajoutItem(T objet);

    void vider();
    boolean estPlein();
    boolean estVide();
    int nombresObjets();
    int getTailleMax();


    IntegerProperty getTailleProperty();

    T objetALemplacement(int emplacement);

    ArrayList<T> enleverObjet(int emplacement);
    T retourneObjet(int emplacement);
}
