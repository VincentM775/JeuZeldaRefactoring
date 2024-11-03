package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;

public interface ObjetUtilisable extends ElementUtilisable, ElementStockable {
    @Override
    boolean utilise(Entite entite);
    static void agie() {

    }
}
