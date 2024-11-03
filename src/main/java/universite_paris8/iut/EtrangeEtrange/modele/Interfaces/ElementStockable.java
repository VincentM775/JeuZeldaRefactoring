package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

public interface ElementStockable {
    String getNom();
    int stackMax();
    double durabilitee();
    int prixAchat();
    abstract void agie();
}
