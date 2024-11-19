package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternDeplacementAleatoire;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Slime extends ESP {
    /**
     * Crée une nouvelle entité offensif.
     *
     * @param x         La position horizontale de l'entité.
     * @param y         La position verticale de l'entité.
     * @param direction La direction dans laquelle l'entité est orientée.
     * @param hitbox    La hitbox de l'entité.
     */

    public static final int PV_SLIME = 100;
    public static final int ATTAQUE_SLIME = 30;
    public static final int DEFENSE_SLIME = 20;
    public static final int ATTAQUE_SPECIALE_SLIME = 20;
    public static final int DEFENSE_SPECIALE_SLIME = 50;
    public static final double VITESSE_SLIME = 0.005;

    public Slime(double x, double y, Direction direction, Hitbox hitbox) {
        super( x, y, direction,
                PV_SLIME,
                DEFENSE_SLIME,
                DEFENSE_SPECIALE_SLIME,
                VITESSE_SLIME,
                hitbox);
    }


    @Override
    protected Pattern initPattern() {
        return new PatternDeplacementAleatoire(this);
    }


    @Override
    public String typeActeur() {
        return "slime";
    }

    @Override
    public void derniereAction () {
        double x = getPosition().getX();
        double y = getPosition().getY();
        Environnement.getInstance().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public Prompt getPrompt()
    {
        return  null;
    }

}