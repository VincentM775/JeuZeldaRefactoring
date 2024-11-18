package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternEntiteOffensive.PatternSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ParametreMonstre;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Squelette extends EntiteOffensive {
    private Epee epee ;

    public Squelette(double x, double y, Direction direction, Hitbox hitbox) {
        super( x, y, direction,
                ParametreMonstre.PV_SQUELETTE,
                ParametreMonstre.ATTAQUE_SQUELETTE,
                ParametreMonstre.DEFENSE_SQUELETTE,
                ParametreMonstre.ATTAQUE_SPECIALE_SQUELETTE,
                ParametreMonstre.DEFENSE_SPECIALE_SQUELETTE,
                ParametreMonstre.VITESSE_SQUELETTE,
                hitbox);
        epee = new Epee();
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
