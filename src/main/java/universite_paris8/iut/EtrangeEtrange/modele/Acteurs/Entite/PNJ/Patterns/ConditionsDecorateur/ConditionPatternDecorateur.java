package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public abstract class ConditionPatternDecorateur implements Pattern
{
    protected Pattern patternAeffectuer;

    public ConditionPatternDecorateur(Pattern patternAeffectuer)
    {
        this.patternAeffectuer = patternAeffectuer;
    }

    public abstract boolean conditionRespecter();

    @Override
    public void effectue() {
        if (conditionRespecter())
            patternAeffectuer.effectue();
    }
}
