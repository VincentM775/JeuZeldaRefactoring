package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternEntiteOffensive.PatternBoss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.*;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternComposite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternsBasique.PatternTourSurSois;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class PatternRoiSquelette implements Pattern {
    private RoiSquelette roiSquelette;
    private Pattern pattern1;
    private Pattern pattern2;
    private Pattern patternAct;

    public PatternRoiSquelette(RoiSquelette roiSquelette) {
        this.roiSquelette = roiSquelette;
        PatternInvocationSquelette patternInvocation = new PatternInvocationSquelette(roiSquelette);
        initPatternDeplacement(patternInvocation);
        initPatternAttaque(patternInvocation);

        patternAct = pattern2;
    }

    private void initPatternDeplacement(PatternInvocationSquelette patternInvocation) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        //patterns.add(new PatternTourSurSois(roiSquelette));
        patterns.add(new PatternSeDirigerVersCible(roiSquelette,Environnement.getInstance().getJoueur()));


        patterns.add(patternInvocation::invoqueSquelette);
        patterns.add(() -> patternAct = pattern2);

        this.pattern1 = new ConditionJoueurDansVisionDecorateur(
                roiSquelette,
                new PatternComposite(patterns),
                6
        );
    }

    private void initPatternAttaque(PatternInvocationSquelette patternInvocation) {
        ArrayList<Pattern> patterns = new ArrayList<>();

        patterns.add(
                new ConditionJoueurDansRayonAttaqueDecorateur(roiSquelette, new PatternAttaque(roiSquelette)));
        patterns.add(new ConditionDelaieRespecter(patternInvocation::invoqueSquelette, 1000));
        patterns.add(new ConditionDelaieRespecter(() -> roiSquelette.soigner(3), 500));
        patterns.add(new PatternSeDirigerVersCible(roiSquelette, Environnement.getInstance().getJoueur()));

        ArrayList<Pattern> patterns2 = new ArrayList<>();

        patterns2.add(new PatternSeDirigerVersCible(roiSquelette,Environnement.getInstance().getJoueur()));
        patterns2.add(patternInvocation::invoqueSquelette);
        patterns2.add(() -> patternAct = pattern1);

        this.pattern2 = new PatternCompositeStrategie(
                new ConditionJoueurDansVisionDecorateur(roiSquelette, new PatternComposite(patterns), 8),
                new PatternComposite(patterns2)
        );

    }

    @Override
    public void effectue() {
        if (patternAct != null) {
            patternAct.effectue();
        }
    }

    // Détecte si le joueur est dans un certain rayon autour du Roi Squelette
    private boolean detecteJoueurDansRayon(double rayon) {
        Position positionJoueur = Environnement.getInstance().getJoueur().getPosition();
        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - this.roiSquelette.getPosition().getX(), 2) +
                Math.pow(positionJoueur.getY() - this.roiSquelette.getPosition().getY(), 2));
        return distance <= rayon;
    }

}
