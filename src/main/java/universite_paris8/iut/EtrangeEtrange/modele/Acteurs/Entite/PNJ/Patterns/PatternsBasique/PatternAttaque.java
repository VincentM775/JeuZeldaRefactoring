package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.ESP;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

public class PatternAttaque implements Pattern
{
    private EntiteOffensive entiteOffensive;

    public PatternAttaque(EntiteOffensive entiteOffensive) {
        this.entiteOffensive = entiteOffensive;
    }

    @Override
    public void effectue() {
        entiteOffensive.faitUneAttaque();
    }
}
