package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteDefensive;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;


import java.util.ArrayList;

public  class LivreMagique implements ObjetUtilisable
{

    private static final Sortilege SORTILEGE1 = ConstanteObjet.SORTILEGE1_LIVRE_MAGIQUE;
    private static final Sortilege SORTILEGE2 = ConstanteObjet.SORTILEGE2_LIVRE_MAGIQUE;
    private static final Sortilege SORTILEGE3 = ConstanteObjet.SORTILEGE3_LIVRE_SOIN;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_LIVRE_MAGIQUE;
    private static final int STACK_MAX = ConstanteObjet.STACK_MAX_LIVRE_MAGIQUE;
    private static final int DURABILITEE = ConstanteObjet.DURABILITE_LIVRE_MAGIQUE;
    private ArrayList<Sortilege> sortileges;
    private Cooldown cooldown;
    private Sortilege sortilegeCourant;

    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(SORTILEGE1);
        this.sortileges.add(SORTILEGE2);
        this.sortileges.add(SORTILEGE3);
        this.sortilegeCourant = this.sortileges.get(0);
        this.cooldown = new Cooldown(ConstantesSortilege.DELAI_MINIMUM_ENTRE_SORTS);
    }

    @Override
    public boolean utilise(EntiteDefensive entite)
    {
        if (this.cooldown.delaieEcoule() && this.sortilegeCourant != null)
        {
            return sortilegeCourant.utilise(entite);
        }
        return false;
    }

    public void setSortilege(Sortilege sortilege){
        this.sortilegeCourant = sortilege;
    }

    public Sortilege getSortilege(int index)
    {
        Sortilege sortilege = null;

        if (index >= 0 && index < sortileges.size())
            sortilege = sortileges.get(index);

        return sortilege;
    }

    @Override
    public String getNom() {
        return "livremagique";
    }
    @Override
    public int stackMax() {
        return STACK_MAX;
    }
    @Override
    public double durabilitee() { return DURABILITEE; }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }

}
