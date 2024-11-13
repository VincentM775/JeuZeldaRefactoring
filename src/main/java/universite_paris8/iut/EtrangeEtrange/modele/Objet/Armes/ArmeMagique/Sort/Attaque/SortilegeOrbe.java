package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteDefensive;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;

public class SortilegeOrbe extends Sortilege
{
    public SortilegeOrbe(long delaiUtilisation) {
        super(delaiUtilisation);
    }

    @Override
    public boolean utilise(EntiteDefensive entite)
    {
        Orbe orbe = new Orbe(entite);

        if (getCooldown().delaieEcoule())
        {
            Environnement.getInstance().ajoutActeur(orbe);
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
