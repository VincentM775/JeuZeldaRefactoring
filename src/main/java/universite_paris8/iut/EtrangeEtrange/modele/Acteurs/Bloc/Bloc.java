package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementDommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.PieceOr;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;


public class Bloc extends Acteur {
    public Bloc(double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) {
        super(x, y, direction, pv, vitesse, hitbox);
    }

    @Override
    public void seFaitPousser(Acteur acteur)
    {
        setDirection(acteur.getDirection());
        setVitesse(acteur.getVitesse());

        if (peutSeDeplacer())
            seDeplace(1);
    }

    @Override
    public void subitCollision(Acteur acteur) {acteur.causeCollision(this);}

    @Override
    public String typeActeur() { return "bloc";}

    @Override
    public void derniereAction() {
        double x = getPosition().getX();
        double y = getPosition().getY();
        Environnement.getInstance().ajouterDropAuSol(new DropAuSol(new PieceOr(), 1, new Position(x, y)));
    }

    @Override
    public boolean estUnEnemie() { return false; }

    @Override
    public void agir() {/*N'agit pas*/}
    @Override
    public void causeCollision(Acteur acteur) {/*ne réagit pas à la cause de la collision*/}

    @Override
    public void subitAttaque(ElementDommageable causeDegat, EntiteOffensive entiteOffensif) {enleveToutPv();}
//    @Override
//    public void drop() {monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(position.getX(), position.getY()))); }

}
