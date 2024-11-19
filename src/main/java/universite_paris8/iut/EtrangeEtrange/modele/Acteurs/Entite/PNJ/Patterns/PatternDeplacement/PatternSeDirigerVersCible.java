package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;
import java.util.List;

public class PatternSeDirigerVersCible implements Pattern {
    private Acteur acteur, cible;
    private Aetoile aetoile;
    private final int delai = 100;
    private long derniereMiseAJour;
    private List<Position> chemin;

    public PatternSeDirigerVersCible(Acteur acteur, Acteur cible) {
        this.acteur = acteur;
        this.cible = cible;
        this.aetoile = new Aetoile();
        this.chemin = new ArrayList<>();
        this.derniereMiseAJour = 0;
    }

    @Override
    public void effectue() {
        mettreAJourChemin();
        deplacerVersProchainePosition();

    }

    private void mettreAJourChemin(){
        long currentTime = System.currentTimeMillis();

        if (currentTime - derniereMiseAJour >= delai || chemin.isEmpty()) {
            chemin = aetoile.trouverChemin(acteur.getPosition(), cible.getPosition());
            derniereMiseAJour = currentTime;

        }
    }

    private void deplacerVersProchainePosition() {
        if (chemin.size() > 1) {
            Position prochainePosition = chemin.get(1);




            Direction direction = calculerDirectionVers(prochainePosition);




            if (direction != null) {
                acteur.setDirection(direction);

                if (acteur.peutSeDeplacer()) {
                    acteur.setSeDeplace(true);
                    acteur.seDeplace(1);
                } else {
                    acteur.setSeDeplace(false);
                }

                if (positionAtteinte(prochainePosition)) {
                    chemin.remove(1);
                }
            } else {
                acteur.setSeDeplace(false);
            }
        } else {
            acteur.setSeDeplace(false);
        }
    }

    private Direction calculerDirectionVers(Position prochainePosition) {
        double deltaX = prochainePosition.getX() - acteur.getPosition().getX();
        double deltaY = prochainePosition.getY() - acteur.getPosition().getY();
        return Direction.calculerDirection(deltaX, deltaY);
    }

    private boolean positionAtteinte(Position position) {
        return Math.abs(acteur.getPosition().getX() - position.getX()) < 0.1 &&
                Math.abs(acteur.getPosition().getY() - position.getY()) < 0.1;
    }
}
