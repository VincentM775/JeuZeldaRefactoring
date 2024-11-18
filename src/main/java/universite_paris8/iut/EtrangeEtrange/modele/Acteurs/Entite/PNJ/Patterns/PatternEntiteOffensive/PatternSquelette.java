package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternEntiteOffensive;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionJoueurDansRayonAttaqueDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionJoueurDansVisionDecorateur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.PatternCompositeStrategie;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;


import java.util.ArrayList;
import java.util.List;

public class PatternSquelette implements Pattern
{
    private EntiteOffensive squelette;
    private Pattern pattern;

    public PatternSquelette(Squelette squelette)
    {
        this.squelette = squelette;
        initPattern();
    }

    private void initPattern(){

        Pattern p = () -> squelette.setSeDeplace(false);


        pattern = new PatternCompositeStrategie (
                new ConditionJoueurDansVisionDecorateur(squelette,
                        new PatternCompositeStrategie (
                                new ConditionJoueurDansRayonAttaqueDecorateur(squelette,
                                        new PatternAttaque(squelette)),
                                new PatternSeDirigerVersCible(squelette, Environnement.getInstance().getJoueur())
                        ),
                        6
                ),
                new PatternComposite(new ArrayList<>(List.of(p,
                        new PatternDeplacementAleatoire(squelette))))

        );


    }


    @Override
    public void effectue() {
        pattern.effectue();

    }
}
