package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternEntiteOffensive.PatternBoss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class PatternInvocationSquelette implements Pattern
{
    private RoiSquelette roiSquelette;

    public PatternInvocationSquelette(RoiSquelette roiSquelette) {
        this.roiSquelette = roiSquelette;
    }

    @Override
    public void effectue() {
        invoqueSquelette();
    }

    public void invoqueSquelette() {

        Position positionHaut = new Position(this.roiSquelette.getPosition().getX()+nombrePourPositionAleatoire(), this.roiSquelette.getPosition().getY()+nombrePourPositionAleatoire());
        Position positionBas = new Position(this.roiSquelette.getPosition().getX()+nombrePourPositionAleatoire(), this.roiSquelette.getPosition().getY()+nombrePourPositionAleatoire());
        Squelette squeletteGauche = new Squelette(positionHaut.getX(), positionHaut.getY(), Direction.BAS, new Hitbox(0.5, 0.5));
        Squelette squeletteDroite = new Squelette(positionBas.getX(), positionBas.getY(), Direction.BAS, new Hitbox(0.5, 0.5));
        Environnement.getInstance().ajoutActeur(squeletteGauche);
        Environnement.getInstance().ajoutActeur(squeletteDroite);
        new Potion().utilise(this.roiSquelette);
        new Potion().utilise(this.roiSquelette);
        new Potion().utilise(this.roiSquelette);
    }

    public int nombrePourPositionAleatoire(){
        double randomChoice = Math.random(); // Génère un nombre entre 0 et 1
        int randomNumber;

        if (randomChoice < 0.5) {
            // 50% de chance pour la plage -10 à -2
            randomNumber = (int) (Math.random() * (-2 - (-6) + 1)) + (-6);
        } else {
            // 50% de chance pour la plage 2 à 10
            randomNumber = (int) (Math.random() * (6 - 2 + 1)) + 2;
        }
        return randomNumber;
    }
}
