package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public class ActionAchat extends Action
{
    private Marchand marchand;
    private Objet objet;
    private Joueur joueur;
    private ActionVendre marchander;
    public ActionAchat(Marchand marchand, Objet objet, Joueur joueur, ActionVendre marchander)
    {
        this.joueur = joueur;
        this.objet = objet;
        this.marchand = marchand;
        this.marchander = marchander;
    }



    @Override
    public Prompt execute()
    {
        if (!joueur.getGestionnaireInventaire().getSac().estPlein())
        {
            if (joueur.getPiece() >= objet.prixAchat())
            {
                joueur.getGestionnaireInventaire().getSac().ajoutItem(objet);
                int resteAPayer = objet.prixAchat();

                for(int i = 0 ; i < joueur.getGestionnaireInventaire().getSac().getTailleMax() && resteAPayer!=0 ; i++){
                    if(joueur.getGestionnaireInventaire().getSac().getEmplacement(i).nomObjet().equals("pieceor")) {
                        int quantite = joueur.getGestionnaireInventaire().getSac().getEmplacement(i).quantiteObjet();
                        for(int j = 0 ; j <  quantite &&  resteAPayer!=0; j++){
                            joueur.getGestionnaireInventaire().getSac().getEmplacement(i).enleveObjet();
                            resteAPayer--;
                        }
                    }
                }

            }
        }

        return this.marchander.execute();
    }
}
