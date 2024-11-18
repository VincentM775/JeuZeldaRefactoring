package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.EpeeLourde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;

public class Guerrier extends Joueur
{
    public Guerrier( double x, double y, Direction direction) {
        super(ConstantesPersonnages.GUERRIER_PV, ConstantesPersonnages.GUERRIER_ATTAQUE, ConstantesPersonnages.GUERRIER_DEFENSE, ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL, ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL, ConstantesPersonnages.GUERRIER_VITESSE,new Sac(), new EpeeLourde(), x, y, direction, new Hitbox(0.50, 0.50));
    }

    @Override
    public void derniereAction() {}

    @Override
    public void faitUneAttaque() {

    }

    @Override
    protected Pattern initPattern() {
        return null;
    }
}
