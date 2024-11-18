package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionDelaieRespecter extends ConditionPatternDecorateur
{
    private long derniereApelle;
    private long delaie;

    public ConditionDelaieRespecter(Pattern patternAeffectuer,long delaie) {
        super(patternAeffectuer);
        this.derniereApelle = 0;
        this.delaie = delaie;
    }

    @Override
    public boolean conditionRespecter() {
        long now = System.currentTimeMillis();
        return derniereApelle + delaie >= now;
    }
}
