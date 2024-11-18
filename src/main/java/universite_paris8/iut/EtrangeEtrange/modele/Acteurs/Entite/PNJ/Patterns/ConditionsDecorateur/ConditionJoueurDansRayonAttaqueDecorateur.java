package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;

public class ConditionJoueurDansRayonAttaqueDecorateur extends ConditionPatternDecorateur
{
    private EntiteOffensive entiteOffensive;

    public ConditionJoueurDansRayonAttaqueDecorateur(EntiteOffensive monstre,Pattern patternAeffectuer) {
        super(patternAeffectuer);
        this.entiteOffensive = monstre;
    }

    @Override
    public boolean conditionRespecter() {
        return Environnement.getInstance().estDansRayon(entiteOffensive.getPosition(),1);
    }
}
