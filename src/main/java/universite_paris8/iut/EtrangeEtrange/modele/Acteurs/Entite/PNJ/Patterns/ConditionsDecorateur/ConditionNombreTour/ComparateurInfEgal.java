package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour;

public class ComparateurInfEgal implements ComparateurStrategy
{

    @Override
    public boolean comparer(long tour, long tourDeclancheur) {
        return tourDeclancheur <= tour;
    }
}
