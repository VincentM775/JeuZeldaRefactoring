package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class Soigner extends Action
{
    private Joueur joueur;

    public Soigner(Joueur joueur)
    {
        this.joueur = joueur;
    }
    @Override
    public Prompt execute() {
        joueur.getStatsPv().ajoutPv(joueur.getStatsPv().getPvMaximum());
        return null;
    }
}
