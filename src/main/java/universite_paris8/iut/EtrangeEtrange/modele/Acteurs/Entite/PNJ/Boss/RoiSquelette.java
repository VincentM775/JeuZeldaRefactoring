package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.EntiteOffensive;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternBoss.PatternInvocationSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ParametreMonstre;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class RoiSquelette extends EntiteOffensive {
    private final int nbrPotion = 6;
    private final int nbrEpee = 3;
    private Sac sac;

    public RoiSquelette(double x, double y, Direction direction) {
        super(  x,y,direction,
                ParametreMonstre.PV_ROI_SQUELETTE,ParametreMonstre.ATTAQUE_ROI_SQUELETTE,
                ParametreMonstre.DEFENSE_ROI_SQUELETTE,ParametreMonstre.ATTAQUE_SPECIALE_ROI_SQUELETTE,
                ParametreMonstre.DEFENSE_SPECIALE_ROI_SQUELETTE, ParametreMonstre.VITESSE_ROI_SQUELETTE,
                new Hitbox(1,1)
        );

        initInventaire();
    }

    private void initInventaire() {
        this.sac = new Sac();

        for (int i = 0; i < nbrEpee; i++)
            this.sac.ajoutItem(new Epee());

        for (int i = 0; i < nbrPotion; i++)
            this.sac.ajoutItem(new Potion());
    }


    @Override
    public String typeActeur() {
        return "roisquelette";
    }

    @Override
    public void derniereAction() {TypeCompetence.COURIR.getCompetence().monterDeNiveau(Environnement.getInstance().getJoueur());}

    @Override
    public boolean estUnEnemie() {
        return true;
    }

    @Override
    public void faitUneAttaque() {
        Epee epee;

        if ((epee = sac.trouveObjet(Epee.class)) != null) {
            epee.utilise(this);
        }
    }

    @Override
    protected Pattern initPattern() {
        return new PatternInvocationSquelette(this);
    }
}