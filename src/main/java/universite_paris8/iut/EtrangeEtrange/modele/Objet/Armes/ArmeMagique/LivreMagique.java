package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegeOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support.SortilegeDeSoins;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;


import java.util.ArrayList;

public  class LivreMagique implements ObjetUtilisable
{
    public static final int DELAI_MINIMUM_ENTRE_SORTS = 300;


    public static Sortilege SORTILEGE1_LIVRE_MAGIQUE = new SortilegeOrbe();
    public static Sortilege SORTILEGE2_LIVRE_MAGIQUE = new SortilegePluitDeFleche();
    public static Sortilege SORTILEGE3_LIVRE_SOIN = new SortilegeDeSoins();
    public static int SORT_MAXIMUM_LIVRE_MAGIQUE = 3;
    public static int STACK_MAX_LIVRE_MAGIQUE = 1;
    public static int PRIX_ACHAT_LIVRE_MAGIQUE = 120;
    public static int DURABILITE_LIVRE_MAGIQUE = -1;

    private ArrayList<Sortilege> sortileges;
    private Cooldown cooldown;
    private Sortilege sortilegeCourant;

    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(SORTILEGE1_LIVRE_MAGIQUE);
        this.sortileges.add(SORTILEGE2_LIVRE_MAGIQUE);
        this.sortileges.add(SORTILEGE3_LIVRE_SOIN);
        this.sortilegeCourant = this.sortileges.get(0);
        this.cooldown = new Cooldown(DELAI_MINIMUM_ENTRE_SORTS);
    }

    @Override
    public boolean utilise(Entite entite)
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
        return STACK_MAX_LIVRE_MAGIQUE;
    }
    @Override
    public double durabilitee() { return DURABILITE_LIVRE_MAGIQUE; }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT_LIVRE_MAGIQUE;
    }

}
