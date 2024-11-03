package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Inventaire;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class GestionnaireInventaire {
    protected ObjetUtilisable objetMainGauche;
    protected ObjetUtilisable objetMainDroite;
    protected Sac sac;

    public GestionnaireInventaire(ObjetUtilisable objetMainGauche, ObjetUtilisable objetMainDroite, Sac sac) {
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
        this.sac = sac;
    }

    public Sac getSac()
    {
        return this.sac;
    }


    public ObjetUtilisable retournerObjetMainDroite()
    {
        ObjetUtilisable objet = this.objetMainDroite;
        this.objetMainDroite = null;
        return objet;
    }
    public ObjetUtilisable retournerObjetMainGauche(){
        ObjetUtilisable objet = this.objetMainGauche;
        this.objetMainGauche=null;
        return objet;
    }

    public void setObjetMainGauche(ObjetUtilisable objet){
        this.objetMainGauche = objet;
    }

    public ObjetUtilisable getObjetMainDroite(){
        return this.objetMainDroite;
    }
    public ObjetUtilisable getObjetMainGauche(){
        return this.objetMainGauche;
    }

    public void setObjetMainDroite(ObjetUtilisable objet){
        this.objetMainDroite = objet;
    }

    public void ramasserObjet(Monde monde, Position position, Direction direction) {
        ArrayList<DropAuSol> dropAuSols = monde.getDropAuSol();

        for(int i = 0 ; i < dropAuSols.size() ; i++){
            Position position1 = dropAuSols.get(i).getPosition();
            if((Math.abs(position.getX()+direction.getX()-position1.getX())<1)
                    &&(Math.abs(position.getY()+direction.getY()-position1.getY())<1)
                    &&(sac.ajoutItem(dropAuSols.get(i).getObjet()))) {
                monde.enleverDropAuSol(dropAuSols.get(i));
                i++;
            }
        }
    }
}
