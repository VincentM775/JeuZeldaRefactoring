package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementIterable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class LanceurFlecheARetardement implements ElementIterable {
    private final Entite utilisateur;
    private final Direction direction;
    private Position position;
    private Cooldown cooldown;
    private boolean flecheLancee;

    public LanceurFlecheARetardement(Entite utilisateur, Direction direction, double x, double y, int delaie) {
        this.utilisateur = utilisateur;
        this.direction = direction;
        this.position = new Position(x, y);
        this.cooldown = new Cooldown(delaie);
        this.cooldown.reset();
        this.flecheLancee = false;
    }

    public void creationFleche(){
        Fleche flecheSimple = new Fleche();
        flecheSimple.setDirection(this.direction);
        flecheSimple.setPosition(positionAleaAutourDe(this.position, this.direction));
        flecheSimple.setUtilisateur(this.utilisateur);
        Environnement.getInstance().ajoutActeur(flecheSimple);
        this.flecheLancee = true;
    }

    private Position positionAleaAutourDe(Position position, Direction direction) {
        double dispersionSurLesCotes = 3;
        double dispersionFace = 3 * Math.random();
        double dispertion = (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);

        double x = position.getX();
        double y = position.getY();

        if (direction == Direction.DROITE || direction == Direction.GAUCHE) {
            x += dispersionFace * direction.getX();
            y += dispertion;
        } else {
            x += dispertion;
            y += dispersionFace * direction.getY();
        }

        return new Position(x, y);
    }

    @Override
    public void unTour() {
        if(this.cooldown.delaieEcoule())
            creationFleche();
    }

    @Override
    public boolean iterationsFinie() {
        return flecheLancee;
    }
}
