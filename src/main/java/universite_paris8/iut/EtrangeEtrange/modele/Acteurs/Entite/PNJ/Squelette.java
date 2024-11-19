package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternEntiteOffensive.PatternSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.EpeeLourde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Squelette extends EntiteOffensive {
    private Epee epee ;
    public static final int PV_SQUELETTE = 100;
    public static final int ATTAQUE_SQUELETTE = 10;
    public static final int DEFENSE_SQUELETTE = 10;
    public static final int ATTAQUE_SPECIALE_SQUELETTE = 1;
    public static final int DEFENSE_SPECIALE_SQUELETTE = 10;
    public static final double VITESSE_SQUELETTE = 0.005;

    public Squelette(double x, double y, Direction direction, Hitbox hitbox) {
        super( x, y, direction,
                PV_SQUELETTE,
                ATTAQUE_SQUELETTE,
                DEFENSE_SQUELETTE,
                ATTAQUE_SPECIALE_SQUELETTE,
                DEFENSE_SPECIALE_SQUELETTE,
                VITESSE_SQUELETTE,
                hitbox);
        epee = new EpeeLourde();
    }

    @Override
    protected Pattern initPattern() {
        return new PatternSquelette(this);

    }

    @Override
    public String typeActeur() {
        return "Squelette";
    }

    @Override
    public void derniereAction() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        Environnement.getInstance().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public void faitUneAttaque() {
        epee.utilise(this);
    }
}
