package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Archer extends Joueur
{
    public Archer( double x, double y, Direction direction) {
        super(ConstantesPersonnages.ARCHER_PV, ConstantesPersonnages.ARCHER_ATTAQUE, ConstantesPersonnages.ARCHER_DEFENSE, ConstantesPersonnages.ARCHER_ATTAQUE_SPECIAL, ConstantesPersonnages.ARCHER_DEFENSE_SEPCIAL, ConstantesPersonnages.ARCHER_VITESSE,new Sac(), new Arc(), x, y, direction, new Hitbox(ConstantesPersonnages.ARCHER_HITBOX_HAUTEUR,ConstantesPersonnages.ARCHER_HITBOX_LARGEUR));
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