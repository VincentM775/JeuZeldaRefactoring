package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;

public class SortilegeDeSoins extends Sortilege
{
    public static final double PV_RESTORER_GUERISON = 20;
    public static final long DELAIE_GUERISON = 100;

    public SortilegeDeSoins() {
        super(DELAIE_GUERISON);
    }

    @Override
    public boolean utilise(Entite entite)
    {
        if (getCooldown().delaieEcoule())
        {
            entite.soigner(PV_RESTORER_GUERISON);
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
