package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats;


import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Joueur;

public class CompetenceUpVitesse extends Competence {
    private final double[] vitesseParNiveau = new double[]{0.001,0.002,0.002};

    @Override
    public int niveauMax() {
        return 3;
    }

    @Override
    public void monterDeNiveau(Joueur joueur) {
        if (niveauCompetence <= niveauMax()) {
            joueur.setVitesse(joueur.getVitesse() + vitesseParNiveau[niveauCompetence]);
        }
    }
}
