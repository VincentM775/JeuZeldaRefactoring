package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementIterable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.*;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class SortilegePluitDeFleche extends Sortilege {
    public static final int NOMBRE_FLECHE_PLUIT_DE_FLECHES = 15;
    public static final long DELAIE_PLUIT_DE_FLECHES = 200;

    public SortilegePluitDeFleche() {
        super(DELAIE_PLUIT_DE_FLECHES);
    }

    @Override
    public boolean utilise(Entite utilisateur) {
        if (getCooldown().delaieEcoule()) {
            double x = utilisateur.getPosition().getX();
            double y = utilisateur.getPosition().getY();
            Direction direction = utilisateur.getDirection();

            for (int i = 0; i < NOMBRE_FLECHE_PLUIT_DE_FLECHES; i++) {
                ElementIterable pluieDeFleches = new LanceurFlecheARetardement(utilisateur, direction, x, y);
                Environnement.getInstance().ajoutIterable(pluieDeFleches);
            }
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
