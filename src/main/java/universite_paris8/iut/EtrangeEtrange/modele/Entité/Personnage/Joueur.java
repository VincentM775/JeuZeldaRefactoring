package universite_paris8.iut.EtrangeEtrange.modele.Entité.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Entité.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entité.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public abstract class Joueur extends EntiteOffensif
{
    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
    }
}
