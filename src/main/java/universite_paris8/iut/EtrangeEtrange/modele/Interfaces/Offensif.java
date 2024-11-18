package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public interface Offensif {

    public double getAttaque();
    public double getAttaqueSpecial();
    Position getPosition();
    Direction getDirection();
}