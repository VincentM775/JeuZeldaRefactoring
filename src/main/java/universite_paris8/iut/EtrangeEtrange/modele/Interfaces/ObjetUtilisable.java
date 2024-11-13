package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteDefensive;

public interface ObjetUtilisable extends ElementUtilisable, ElementStockable {
    @Override
    boolean utilise(EntiteDefensive entite);
    static void agie() {

    }
}
