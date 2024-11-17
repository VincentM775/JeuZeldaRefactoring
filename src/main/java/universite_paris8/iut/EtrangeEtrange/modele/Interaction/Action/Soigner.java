package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;

public class Soigner extends Action
{
    private Joueur joueur;

    public Soigner() {
        this.joueur = Environnement.getInstance().getJoueur();
    }
    @Override
    public Prompt execute() {
        joueur.getStatsPv().ajoutPv(joueur.getStatsPv().getPvMaximum());
        return null;
    }
}
