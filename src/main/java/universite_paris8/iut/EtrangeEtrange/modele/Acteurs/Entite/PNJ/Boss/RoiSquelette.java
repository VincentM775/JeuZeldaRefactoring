package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternEntiteOffensive.PatternBoss.PatternRoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.EpeeLourde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class RoiSquelette extends EntiteOffensive {
    private final int nbrPotion = 6;
    private Epee epee;
    private Position sauvegardePositionDepart;

    private Sac sac;
    private Pattern pattern;

    public static final int PV_ROI_SQUELETTE = 1000;
    public static final int ATTAQUE_ROI_SQUELETTE = 20;
    public static final int DEFENSE_ROI_SQUELETTE = 20;
    public static final int ATTAQUE_SPECIALE_ROI_SQUELETTE = 20;
    public static final int DEFENSE_SPECIALE_ROI_SQUELETTE = 20;
    public static final double VITESSE_ROI_SQUELETTE = 0.3;

    public RoiSquelette(double x, double y, Direction direction) {
        super(  x,y,direction,
                PV_ROI_SQUELETTE,ATTAQUE_ROI_SQUELETTE,
                DEFENSE_ROI_SQUELETTE,ATTAQUE_SPECIALE_ROI_SQUELETTE,
                DEFENSE_SPECIALE_ROI_SQUELETTE, VITESSE_ROI_SQUELETTE,
                new Hitbox(0.5,0.5)
        );
        this.pattern = null;
        this.epee = new EpeeLourde();
        this.sauvegardePositionDepart = new Position(x, y);
        initInventaire();
    }

    private void initInventaire() {
        this.sac = new Sac();

        for (int i = 0; i < nbrPotion; i++)
            this.sac.ajoutItem(new Potion());
    }

    public void retourPositionDuDepart() {
        setPosition(this.sauvegardePositionDepart);
    }

    @Override
    public String typeActeur() {
        return "roisquelette";
    }

    @Override
    public void derniereAction() {
        TypeCompetence.COURIR.getCompetence().monterDeNiveau(Environnement.getInstance().getJoueur());
    }

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public void faitUneAttaque() {
        this.epee.utilise(this);
    }

    @Override
    protected Pattern initPattern() {
        if (pattern == null) {
            pattern = new PatternRoiSquelette(this);
        }
        return pattern;
    }
}