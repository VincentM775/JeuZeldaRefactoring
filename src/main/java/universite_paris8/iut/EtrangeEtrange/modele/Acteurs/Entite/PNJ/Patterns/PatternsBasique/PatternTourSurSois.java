package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class PatternTourSurSois implements Pattern {
    private Acteur acteur;

    public PatternTourSurSois(Acteur acteur) {
        this.acteur = acteur;
    }

    @Override
    public void effectue() {
        int iDirectionAct = acteur.getDirection().ordinal();

        for (int i = 1; i < Direction.values().length; i++) {
            int newDirectionIndex = (iDirectionAct + i) % Direction.values().length;
            acteur.setDirection(Direction.values()[newDirectionIndex]);
        }
    }
}


