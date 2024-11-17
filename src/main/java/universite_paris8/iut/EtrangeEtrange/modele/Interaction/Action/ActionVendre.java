package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;

import java.util.ArrayList;

public class ActionVendre extends Action
{
    private Marchand marchand;

    public ActionVendre(Marchand marchand)
    {
        this.marchand = marchand;
    }

    @Override
    public Prompt execute()
    {
        Prompt racine = new Prompt("Voici ce que j'ai",null);

        for (Emplacement<ElementStockable> objets : marchand.getMarchandise().getInventaire())
        {
            ArrayList<ElementStockable> obs = objets.enleverToutLesObjets();

            for (ElementStockable objet : obs)
            {
                racine.ajoutPrompt(new Prompt("Ta fais une bonne affaire !",new ActionAchat(marchand,objet, Environnement.getInstance().getJoueur(),this)),objet.getNom() +"     ["+objet.prixAchat()+"]");
                System.out.println("nv objet");
            }
        }



        return racine;
    }
}
