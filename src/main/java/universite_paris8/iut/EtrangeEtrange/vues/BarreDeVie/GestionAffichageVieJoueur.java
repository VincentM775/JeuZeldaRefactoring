package universite_paris8.iut.EtrangeEtrange.vues.BarreDeVie;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;
import universite_paris8.iut.EtrangeEtrange.vues.GestionImages;

public class GestionAffichageVieJoueur {
    @FXML
    private HBox hboxCoeurs;
    private Pv pv;

    public GestionAffichageVieJoueur(Pv pv) {
        this.pv = pv;
    }

    public void setHboxCoeurs(HBox hbox) {
        this.hboxCoeurs = hbox;
        initialize();
    }

    public void initialize() {

        pv.getPvActuelleProperty().addListener((obs, oldVal, newVal) -> actualiseCoeurs());
        actualiseCoeurs(); // Initialisation de l'affichage des cœurs
    }

    private void actualiseCoeurs() {
        if (hboxCoeurs != null) {
            hboxCoeurs.getChildren().clear();

            // Déterminer le nombre total de cœurs
            int nombreCoeursMax = (int) Math.ceil(pv.getPvMaximum() / 20.0);
            double pointsDeVieActuels = pv.getPv();

            for (int i = 0; i < nombreCoeursMax; i++) {
                ImageView coeur;

                if (pointsDeVieActuels >= 20) {
                    coeur = new ImageView(GestionImages.getInstance().getImages("BarreDeVie/0.png"));
                    pointsDeVieActuels -= 20;
                } else if (pointsDeVieActuels >= 10) {
                    coeur = new ImageView(GestionImages.getInstance().getImages("BarreDeVie/1.png"));
                    pointsDeVieActuels = 0;
                } else {
                    coeur = new ImageView(GestionImages.getInstance().getImages("BarreDeVie/2.png"));
                }

                coeur.setFitWidth(30);
                coeur.setFitHeight(30);
                hboxCoeurs.getChildren().add(coeur);
            }
        }
    }
}

