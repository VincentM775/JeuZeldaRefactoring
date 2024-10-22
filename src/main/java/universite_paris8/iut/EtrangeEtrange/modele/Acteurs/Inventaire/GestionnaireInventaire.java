package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Inventaire;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class GestionnaireInventaire {
    protected Objet objetMainGauche;
    protected Objet objetMainDroite;
    protected Sac sac;

    public GestionnaireInventaire(Objet objetMainGauche, Objet objetMainDroite, Sac sac) {
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
        this.sac = sac;
    }

    public Sac getSac()
    {
        return this.sac;
    }


    public Objet retournerObjetMainDroite()
    {
        Objet objet = this.objetMainDroite;
        this.objetMainDroite = null;
        return objet;
    }
    public Objet retournerObjetMainGauche(){
        Objet objet = this.objetMainGauche;
        this.objetMainGauche=null;
        return objet;
    }

    public void setObjetMainGauche(Objet objet){
        this.objetMainGauche = objet;
    }


    public Objet getObjetMainDroite(){
        return this.objetMainDroite;
    }
    public Objet getObjetMainGauche(){
        return this.objetMainGauche;
    }

    public void setObjetMainDroite(Objet objet){
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
