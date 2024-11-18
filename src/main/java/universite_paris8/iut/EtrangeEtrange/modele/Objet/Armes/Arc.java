package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;

public class Arc implements ObjetUtilisable
{
    private final static int DURABILITE = ConstanteObjet.DURABILITE_ARC;
    private final static int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ARC;
    private final static int STACK_MAX = ConstanteObjet.STACK_MAX_ARC;

    private int durabilitee;
    private Fleche fleche;
    private Cooldown cooldown;

    public Arc()
    {
        this.cooldown = new Cooldown(ConstanteObjet.DELAIE_UTILISATION_ARC);
        this.durabilitee = DURABILITE;
        this.fleche = null;
    }

    @Override
    public boolean utilise(Entite entite)
    {
        if (this.cooldown.delaieEcoule() && fleche != null)
        {
            // Définition des paramètres pour la flèche
            this.fleche.setNewPosition(entite.getPosition().getX(),entite.getPosition().getY());
            this.fleche.setDirection(entite.getDirection());
            this.fleche.setUtilisateur(entite);

            // Ajout de la flèche et l'arc dans l'environnement
            Environnement.getInstance().ajoutActeur(fleche);

            this.fleche = null;
            this.durabilitee--;

            this.cooldown.reset();
            return true;
        }
        return false;
    }

    public void setFleche(Fleche fleche){ this.fleche = fleche; }
    @Override
    public String getNom() {
        return "arc";
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