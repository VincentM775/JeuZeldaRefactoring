package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;

public abstract class Sortilege implements ElementUtilisable
{
    private Cooldown cooldown;

    public Sortilege(long delaiUtilisation)
    {
        this.cooldown = new Cooldown(delaiUtilisation);
    }

    public Cooldown getCooldown(){
        return this.cooldown;
    }


}
