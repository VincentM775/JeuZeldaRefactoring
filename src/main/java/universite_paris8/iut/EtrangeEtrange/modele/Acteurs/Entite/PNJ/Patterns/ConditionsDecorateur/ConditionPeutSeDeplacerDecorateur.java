package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class ConditionPeutSeDeplacerDecorateur extends ConditionPatternDecorateur
{
    private Acteur acteur;

    public ConditionPeutSeDeplacerDecorateur(Acteur acteur,Pattern patternAeffectuer) {
        super(patternAeffectuer);
        this.acteur = acteur;
    }

    @Override
    public boolean conditionRespecter() {
        return acteur.peutSeDeplacer();
    }
}
