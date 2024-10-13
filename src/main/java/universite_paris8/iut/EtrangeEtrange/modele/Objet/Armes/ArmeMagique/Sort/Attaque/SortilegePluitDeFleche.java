package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.*;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;


public class SortilegePluitDeFleche extends Sortilege {
    private final int NOMBRE_FLECHE = ConstantesSortilege.NOMBRE_FLECHE_PLUIT_DE_FLECHES;

    @Override
    public void utilise(Entite utilisateur) {
        if (peutLancerSort) {
            double x = utilisateur.getPosition().getX();
            double y = utilisateur.getPosition().getY();
            Direction direction = utilisateur.getDirection();

            for (int i = 0; i < NOMBRE_FLECHE; i++) {
                int delaie = i + 1;
                Rechargeable rechargeable = new RechargeableFleche(utilisateur, direction, x, y, delaie);
                utilisateur.getMonde().ajoutRechargeable(rechargeable);
            }

            this.peutLancerSort = false;
            this.derniereApelle = System.currentTimeMillis();
            utilisateur.getMonde().ajoutRechargeable(this);
        }
    }

    @Override
    public long delaie() {
        return ConstantesSortilege.DELAIE_PLUIT_DE_FLECHES;
    }
}
