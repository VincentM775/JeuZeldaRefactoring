package universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

public abstract class Piece implements ElementStockable {
    private int valeur;
    public Piece(int valeur){
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
