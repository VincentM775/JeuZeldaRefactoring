package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

public class Sac extends ObjetConteneur<ElementStockable> {
    public static final int TAILLE_SAC = 15;
    public static final int DURABILITE_SAC = -1;
    public static final int PRIX_ACHAT_SAC = 50;
    public static final int STACK_MAX_SAC = 1;

    public Sac() {
        super(TAILLE_SAC);
    }
    @Override
    public int stackMax() { return STACK_MAX_SAC; }
    @Override
    public String getNom() {
        return "sac";
    }
    @Override
    public double durabilitee() {
        return DURABILITE_SAC;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT_SAC;
    }

}
