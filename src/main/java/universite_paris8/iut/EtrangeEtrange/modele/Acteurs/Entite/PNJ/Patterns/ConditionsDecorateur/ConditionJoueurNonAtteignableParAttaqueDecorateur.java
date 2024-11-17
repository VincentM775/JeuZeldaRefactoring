package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionJoueurNonAtteignableParAttaqueDecorateur extends ConditionPatternDecorateur
{
    private EntiteOffensive entiteOffensive;

    public ConditionJoueurNonAtteignableParAttaqueDecorateur(EntiteOffensive entiteOffensive,Pattern patternAeffectuer) {
        super(patternAeffectuer);
        this.entiteOffensive = entiteOffensive;
    }

    @Override
    public boolean conditionRespecter() {
        return true;
    }




}
