package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;

public class SortilegeOrbe extends Sortilege
{
    public static final long DELAIE_ORBE = 600;
    public SortilegeOrbe() {
        super(DELAIE_ORBE);
    }

    @Override
    public boolean utilise(Entite entite)
    {
        Orbe orbe = new Orbe(entite);

        if (getCooldown().delaieEcoule())
        {
            Monde.getInstance().ajoutActeur(orbe);
            getCooldown().reset();
            return true;
        }
        return false;
    }
}
