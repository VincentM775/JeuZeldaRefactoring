package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;

public class SortilegeOrbe extends Sortilege
{
    public SortilegeOrbe(long delaiUtilisation) {
        super(delaiUtilisation);
    }

    @Override
    public boolean utilise(Entite entite)
    {
        Orbe orbe = new Orbe(entite);

        if (getCooldown().delaieEcoule())
        {
            entite.getMonde().ajoutActeur(orbe);
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
