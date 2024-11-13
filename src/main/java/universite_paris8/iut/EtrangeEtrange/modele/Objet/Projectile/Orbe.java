package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteDefensive;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.*;

public class Orbe extends Projectile
{

    private static final double PV = ConstanteObjet.PV_ORBE;
    private static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_ORBE;
    private static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_ORBE;
    private static final double VITESSE = ConstanteObjet.VITESSE_ORBE;
    private static final Hitbox HITBOX = ConstanteObjet.HITBOX_ORBE;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ORBE;
    private static final int STACK_MAX  = ConstanteObjet.STACK_MAX_ORBE;
    private final BFS bfs;
    private Position positionAsuivre;
    private Acteur acteurAsuivre;
    private Cooldown cooldownRechercheChemin;

    public Orbe(EntiteDefensive utilisateur)
    {
        super(utilisateur, PV,VITESSE,HITBOX);
        this.positionAsuivre = null;
        this.bfs = new BFS();
        this.acteurAsuivre = null;
        this.cooldownRechercheChemin = new Cooldown(BFS.DELAIE_RECHERCHE);
    }

    @Override
    public void agir()
    {
        chercheChemin();

        if (this.positionAsuivre != null)
        {
            double deltaX = this.positionAsuivre.getX() - getPosition().getX();
            double deltaY = this.positionAsuivre.getY() - getPosition().getY();

            if (Math.abs(deltaX) > Math.abs(deltaY))
                setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
            else
                setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);

            setSeDeplace(true);

            if (peutSeDeplacer())
                seDeplace(1);
            else
                enleveToutPv();

            if (positionAtteinte(this.positionAsuivre))
                this.positionAsuivre = this.bfs.prochainePosition();
        }
    }

    private boolean positionAtteinte(Position position)
    {
        return  this.position != null
                && Math.abs(getPosition().getX() - position.getX()) < 0.1
                && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    public void chercheChemin()
    {
        if(this.cooldownRechercheChemin.delaieEcoule() &&  this.acteurAsuivre != null) {
            this.bfs.chercherChemin(getPosition(), acteurAsuivre.getPosition());
            this.positionAsuivre = bfs.prochainePosition();
            this.cooldownRechercheChemin.reset();
        }
    }

    @Override
    public String typeActeur() { return "orbe"; }
    @Override
    public void derniereAction() {

    }
    @Override
    public boolean estUnEnemie() { return false; }
    @Override
    public double degatPhysique() { return DEGAT_PHYSIQUE; }
    @Override
    public double degatSpecial() { return DEGAT_SPECIAL; }
    @Override
    public String getNom() {return "orbe";}
    @Override
    public int stackMax() { return STACK_MAX; }
    @Override
    public double durabilitee() {
        return ConstantesSortilege.DURABILITEE_ORBE;
    }
    @Override
    public int prixAchat() { return PRIX_ACHAT; }
    @Override
    public boolean peutSeDeplacer() {return !Environnement.getInstance().getMonde().estHorsMap(this);}
}
