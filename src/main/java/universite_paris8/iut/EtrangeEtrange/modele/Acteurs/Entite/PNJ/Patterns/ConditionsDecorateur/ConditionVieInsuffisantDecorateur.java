package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.ESP;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionVieInsuffisantDecorateur extends ConditionPatternDecorateur
{
    private int pourcentagePvMini;
    private ESP esp;

    public ConditionVieInsuffisantDecorateur(ESP esp,Pattern patternAeffectuer, int pourcentagePvMini) {
        super(patternAeffectuer);
        this.pourcentagePvMini = pourcentagePvMini;
        this.esp = esp;
    }

    @Override
    public boolean conditionRespecter() {
        return esp.getStatsPv().getPourcentageDePv() <= pourcentagePvMini;
    }
}
