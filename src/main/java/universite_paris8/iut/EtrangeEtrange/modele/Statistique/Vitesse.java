package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.StatistiqueInvalideExeption;

public class Vitesse
{
    private static final int vitesseMaximum = 1;
    private DoubleProperty vitesse;

    public Vitesse(double vitesse)
    {
        this.vitesse = new SimpleDoubleProperty();

        setVitesse(vitesse);
    }

    public void setVitesse(double vitesse)
    {
        if (vitesse < 0)
            throw new StatistiqueInvalideExeption("vitesse négatif interdis");

        if (vitesse < vitesseMaximum)
            this.vitesse.set(vitesse);
    }

    public double getVitesse() {
        return this.vitesse.get();
    }

}