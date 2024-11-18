package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.ESP;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;

public class ConditionJoueurDansVisionDecorateur extends ConditionPatternDecorateur
{
    private double rayon;
    private ESP esp;

    public ConditionJoueurDansVisionDecorateur(ESP esp,Pattern patternAeffectuer,double rayon) {
        super(patternAeffectuer);
        this.rayon = rayon;
        this.esp =esp;
    }

    @Override
    public boolean conditionRespecter() {
        return Environnement.getInstance().estDansRayon(esp.getPosition(),rayon);
    }
}
