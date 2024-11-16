package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ObjetUtilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Cooldown;

public class Arc implements ObjetUtilisable
{
    public static final int DURABILITE_ARC = 25;
    public static final int PRIX_ACHAT_ARC = 12;
    public static final long DELAIE_UTILISATION_ARC = 1000;
    public static final int STACK_MAX_ARC = 1;

    private int durabilitee;
    private Fleche fleche;
    private Cooldown cooldown;

    public Arc()
    {
        this.cooldown = new Cooldown(DELAIE_UTILISATION_ARC);
        this.durabilitee = DURABILITE_ARC;
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
            Monde.getInstance().ajoutActeur(fleche);

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
        return STACK_MAX_ARC;
    }
    @Override
    public double durabilitee() {
        return durabilitee;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT_ARC;
    }

}