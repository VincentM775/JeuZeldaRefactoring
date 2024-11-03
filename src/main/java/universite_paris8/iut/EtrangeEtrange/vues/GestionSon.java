package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.media.AudioClip;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

import java.io.File;

public class GestionSon
{

    private AudioClip musiqueFond;
    private AudioClip musiqueGameOver;

    public GestionSon()
    {
        //musiqueFond = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/musiqueGame.mp3").toURI().toString());
        //musiqueGameOver = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/musiqueGameOver.mp3").toURI().toString());
        //musiqueFond.setCycleCount(AudioClip.INDEFINITE);
        //musiqueFond.play(0.1);
    }

    public void gameOver(){
        musiqueFond.stop();
        musiqueGameOver.setCycleCount(AudioClip.INDEFINITE);
        musiqueGameOver.play(0.1);
    }

    public void stopMusique(){
        musiqueFond.stop();
        musiqueGameOver.stop();
    }
    public void lanceSong(ElementStockable objet)
    {
        if(objet.getNom()!=null && !objet.getNom().equals("livremagique") && !objet.getNom().equals("arc")  ) {
            AudioClip mediaPlayer = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/" + objet.getNom() + ".mp3").toURI().toString());
            mediaPlayer.play();
        }
    }


}