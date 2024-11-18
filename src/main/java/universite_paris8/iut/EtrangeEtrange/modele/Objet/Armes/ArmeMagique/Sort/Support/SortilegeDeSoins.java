package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;

public class SortilegeDeSoins extends Sortilege
{
    public SortilegeDeSoins(long delaiUtilisation) {
        super(delaiUtilisation);
    }

    @Override
    public boolean utilise(Entite entite)
    {
        if (getCooldown().delaieEcoule())
        {
            entite.soigner(ConstantesSortilege.PV_RESTORER_GUERISON);
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
