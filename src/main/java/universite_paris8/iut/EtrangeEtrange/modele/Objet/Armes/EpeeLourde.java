package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

public class EpeeLourde extends Epee{

    public static final long DELAIE_UTILISATION_EPEE_LOURDE = 1000;
    public static final double DEGAT_PHYSIQUE_EPEE_LOURDE = 20;
    public static final double DEGAT_SPECIAL_EPEE_LOURDE = 0;

    public EpeeLourde() {
        super(DELAIE_UTILISATION_EPEE_LOURDE,
                DEGAT_PHYSIQUE_EPEE_LOURDE,
                DEGAT_SPECIAL_EPEE_LOURDE);
    }
}
