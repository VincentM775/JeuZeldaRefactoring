package universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;

public class Potion implements ObjetUtilisable
{

    public static final double PV_RESTORER_GUERISON = 20;


    private int durabilitee;

    public Potion(){this.durabilitee = 1;}

    @Override
    public boolean utilise(Entite entite)
    {
        if (durabilitee > 0) {
            entite.soigner(PV_RESTORER_GUERISON);
            this.durabilitee--;
            return true;
        }
        return false;
    }

    @Override
    public String getNom() {
        return "potion";
    }
    @Override
    public int stackMax() {
        return 1;
    }
    @Override
    public double durabilitee() {
        return durabilitee;
    }
    @Override
    public int prixAchat() {
        return 1;
    }

}
