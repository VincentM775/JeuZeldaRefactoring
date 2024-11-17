package universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;

public class Potion implements ObjetUtilisable
{

    private static final int DURABILITEE = ConstanteObjet.DURABILITE_POTION;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_POTION;
    private static final int STACK_MAX = ConstanteObjet.STACK_MAX_POTION;
    private static final int RESTORATION = ConstanteObjet.RESTORATION;
    private int durabilitee;

    public Potion(){this.durabilitee = DURABILITEE;}

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
        return STACK_MAX;
    }
    @Override
    public double durabilitee() {
        return durabilitee;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }

}
