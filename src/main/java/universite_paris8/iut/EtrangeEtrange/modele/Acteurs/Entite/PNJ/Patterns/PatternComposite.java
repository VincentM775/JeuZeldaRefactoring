package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;
import java.util.ArrayList;

public class PatternComposite implements Pattern {

    protected ArrayList<Pattern> patterns;

    public PatternComposite(ArrayList<Pattern> patterns) {
        this.patterns = patterns;
    }

    @Override
    public void effectue() {
        for (Pattern pattern : patterns) {
            pattern.effectue();
        }
    }
}