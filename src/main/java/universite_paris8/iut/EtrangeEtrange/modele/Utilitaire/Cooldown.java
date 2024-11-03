package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

public class Cooldown {

    private long tempsDernierAppel;
    private long delaieEntreAppels;

    public Cooldown(long delaieEntreAppels){
        this.delaieEntreAppels = delaieEntreAppels;
        this.tempsDernierAppel = 0;
    }

    public boolean delaieEcoule(){
        return System.currentTimeMillis() - this.tempsDernierAppel >= this.delaieEntreAppels;
    }

    public void reset(){
        this.tempsDernierAppel = System.currentTimeMillis();
    }
}
