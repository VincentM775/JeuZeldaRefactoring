package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternBoss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class PatternInvocationSquelette implements Pattern
{
    private final int nombreSquelettesMax = 5;
    private RoiSquelette roiSquelette;

    public PatternInvocationSquelette(RoiSquelette roiSquelette) {
        this.roiSquelette = roiSquelette;
    }

    @Override
    public void effectue() {
        invoqueSquelette();
    }


    private void invoqueSquelette() {

    }
}
