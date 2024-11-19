package universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;

public class Potion implements ObjetUtilisable
{

    public static final int DURABILITE_POTION = 2;
    public static final int PRIX_ACHAT_POTION = 12;
    public static final int STACK_MAX_POTION = 5;
    public static final int RESTORATION = 20;
    private int durabilitee;

    public Potion(){this.durabilitee = DURABILITE_POTION;}

    @Override
    public boolean utilise(Entite entite)
    {
        if (durabilitee > 0) {
            entite.soigner(RESTORATION);
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
        return STACK_MAX_POTION;
    }
    @Override
    public double durabilitee() {
        return durabilitee;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT_POTION;
    }

}
