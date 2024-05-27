package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class CompetenceUpPV extends Competence
{
    private final int[] pvParNiveau = new int[]{5,5,10,5,10,20};

    @Override
    public int niveauMax() {
        return 5;
    }

    @Override
    public void monterDeNiveau(Joueur joueur)
    {
        if (niveauCompetence <= niveauMax())
        {
            joueur.augmentePvMaximum(pvParNiveau[niveauCompetence]);
            niveauCompetence++;
        }

    }


}
