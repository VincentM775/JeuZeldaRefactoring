package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

public class Carquois extends ObjetConteneur<Fleche>
{
    public static final int DURABILITE_CARQUOIS = -1;
    public static final int PRIX_ACHAT_CARQUOIS = 50;
    public static final int STACK_MAX_CARQUOIS = 1;
    public Carquois() {
        super(1);
    }

    public Fleche retourneUneFleche()
    {
        return retourneObjet(0);
    }
    @Override
    public String getNom() {
        return "Carquois";
    }
    @Override
    public int stackMax() {
        return STACK_MAX_CARQUOIS;
    }
    @Override
    public double durabilitee() {
        return DURABILITE_CARQUOIS;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT_CARQUOIS;
    }
    @Override
    public Fleche objetALemplacement(int emplacement) {
        return super.objetALemplacement(emplacement);
    }
}
