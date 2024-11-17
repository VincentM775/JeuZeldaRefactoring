package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class ESP extends Entite {
    /**
     * Crée une nouvelle instance d'Entite avec les paramètres spécifiés.
     *
     * @param monde          Le monde dans lequel l'entité évolue.
     * @param x              La position horizontale de l'entité dans le monde.
     * @param y              La position verticale de l'entité dans le monde.
     * @param direction      La direction initiale de l'entité.
     * @param pv             Les points de vie de l'entité.
     * @param defense        La défense de l'entité.
     * @param defenseSpecial La défense spéciale de l'entité.
     * @param vitesse        La vitesse de déplacement de l'entité.
     * @param hitbox         La hitbox de l'entité.
     */



    private Pattern pattern;

    public ESP(double x, double y, Direction direction, double pv, double defense, double defenseSpecial, double vitesse, Hitbox hitbox) {
        super(x, y, direction, pv, defense, defenseSpecial, vitesse, hitbox);
        this.pattern = initPattern();
    }

    @Override
    public void agir() {
        pattern.effectue();
    }

    protected abstract Pattern initPattern();
}