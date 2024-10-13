package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class RechargeableFleche implements Rechargeable {
    private final Entite utilisateur;
    private final Direction direction;
    private final double x;
    private final double y;
    private final int delaie;
    private long derniereApelle = 0;

    public RechargeableFleche(Entite utilisateur, Direction direction, double x, double y, int delaie) {
        this.utilisateur = utilisateur;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.delaie = delaie;
    }

    @Override
    public long delaie() {
        return delaie * 2L;
    }

    @Override
    public boolean cooldown() {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie()) {
            creationFleche();
            derniereApelle = apelle;
            actionFait = true;
        }
        return actionFait;
    }

    public void creationFleche(){
        Fleche flecheSimple = new Fleche();
        flecheSimple.setDirection(direction);
        flecheSimple.setPosition(positionAleaAutourDe(x, y, direction));
        flecheSimple.setMonde(utilisateur.getMonde());
        flecheSimple.setUtilisateur(utilisateur);
        utilisateur.getMonde().ajoutActeur(flecheSimple);
    }

    private Position positionAleaAutourDe(double x, double y, Direction direction) {
        double dispersionSurLesCotes = 3;
        double dispersionFace = 3 * Math.random();

        double dispertion = (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);

        if (direction == Direction.DROITE || direction == Direction.GAUCHE) {
            x += dispersionFace * direction.getX();
            y += dispertion;
        } else {
            x += dispertion;
            y += dispersionFace * direction.getY();
        }

        return new Position(x, y);
    }
}
