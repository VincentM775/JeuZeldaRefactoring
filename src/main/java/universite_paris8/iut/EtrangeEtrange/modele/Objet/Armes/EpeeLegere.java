package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

public class EpeeLegere extends Epee{
    public static final long DELAIE_UTILISATION_EPEE_LEGERE = 100;
    public static final double DEGAT_PHYSIQUE_EPEE_LEGERE = 10;
    public static final double DEGAT_SPECIAL_EPEE_LEGERE = 0;

    public EpeeLegere() {
        super(DELAIE_UTILISATION_EPEE_LEGERE,
                DEGAT_PHYSIQUE_EPEE_LEGERE,
                DEGAT_SPECIAL_EPEE_LEGERE);
    }
}
