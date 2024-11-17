package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class PatternDeplacementAleatoire implements Pattern
{
    private Acteur acteur;

    public PatternDeplacementAleatoire(Acteur acteur) {
        this.acteur = acteur;
    }

    @Override
    public void effectue() {
        if(Math.random()>0.95)
            acteur.setDirection(Direction.randomDirection());


        if (acteur.peutSeDeplacer()) {
            if(Math.random()>0.95){
                acteur.setSeDeplace(false);
            }
            else {
                acteur.seDeplace(1);
                acteur.setSeDeplace(true);
            }
        }
        else if(Math.random()>0.95)
            acteur.setSeDeplace(true);
    }
}
