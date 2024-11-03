package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Inventaire;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class GestionnaireInventaire {
    protected ElementStockable objetMainGauche;
    protected ElementStockable objetMainDroite;
    protected Sac sac;

    public GestionnaireInventaire(ElementStockable objetMainGauche, ElementStockable objetMainDroite, Sac sac) {
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
        this.sac = sac;
    }

    public Sac getSac()
    {
        return this.sac;
    }


    public ElementStockable retournerObjetMainDroite()
    {
        ElementStockable objet = this.objetMainDroite;
        this.objetMainDroite = null;
        return objet;
    }
    public ElementStockable retournerObjetMainGauche(){
        ElementStockable objet = this.objetMainGauche;
        this.objetMainGauche=null;
        return objet;
    }

    public void setObjetMainGauche(ElementStockable objet) {
        this.objetMainGauche = objet;
    }

    public ElementStockable getObjetMainDroite(){
        return this.objetMainDroite;
    }
    public ElementStockable getObjetMainGauche(){
        return this.objetMainGauche;
    }

    public void setObjetMainDroite(ElementStockable objet){
        this.objetMainDroite = objet;
    }

    public void ramasserObjet(Position position, Direction direction) {
        ArrayList<DropAuSol> dropAuSols = Monde.getInstance().getDropAuSol();

        for(int i = 0 ; i < dropAuSols.size() ; i++){
            Position position1 = dropAuSols.get(i).getPosition();
            if((Math.abs(position.getX()+direction.getX()-position1.getX())<1)
                    &&(Math.abs(position.getY()+direction.getY()-position1.getY())<1)
                    &&(sac.ajoutItem(dropAuSols.get(i).getObjet()))) {
                Monde.getInstance().enleverDropAuSol(dropAuSols.get(i));
                i++;
            }
        }
    }
}
