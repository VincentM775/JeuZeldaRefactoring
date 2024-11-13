package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteDefensive;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementIterable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.*;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class SortilegePluitDeFleche extends Sortilege {
    private final int NOMBRE_FLECHE = ConstantesSortilege.NOMBRE_FLECHE_PLUIT_DE_FLECHES;

    public SortilegePluitDeFleche(long delaiUtilisation) {
        super(delaiUtilisation);
    }

    @Override
    public boolean utilise(EntiteDefensive utilisateur) {
        if (getCooldown().delaieEcoule()) {
            double x = utilisateur.getPosition().getX();
            double y = utilisateur.getPosition().getY();
            Direction direction = utilisateur.getDirection();

            for (int i = 0; i < NOMBRE_FLECHE; i++) {
                int delaie = i*300;
                ElementIterable pluieDeFleches = new LanceurFlecheARetardement(utilisateur, direction, x, y, delaie);
                Monde.getInstance().ajoutIterable(pluieDeFleches);
            }
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
