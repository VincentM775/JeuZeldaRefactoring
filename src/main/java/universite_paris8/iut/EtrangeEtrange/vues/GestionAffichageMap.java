package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import org.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GestionAffichageMap {
    private ArrayList<TilePane> TilePaneCouchesMonde;

    public GestionAffichageMap(TilePane sol, TilePane traversable, TilePane nontraversable){
        this.TilePaneCouchesMonde = new ArrayList<>();
        this.TilePaneCouchesMonde.add(sol);
        this.TilePaneCouchesMonde.add(traversable);
        this.TilePaneCouchesMonde.add(nontraversable);
    }


    /**
     * Permet d'afficher la map depuis les fichiers JSON des jeux de tuiles crée avec le logiciel Tiled
     */
    public void afficherMondeJSON(){
        GestionImages gestionImages = GestionImages.getInstance();
        String[] fichiers = {"sol", "traversable", "nontraversable"};
        ArrayList<int[][]> couchesMap = Environnement.getInstance().getMonde().getToutesLesCouches();
        for(TilePane tilePaneCouchesMonde : TilePaneCouchesMonde)
            tilePaneCouchesMonde.getChildren().clear();

        for(int i = 0 ; i < 3 ; i++){
            // Lecture du fichier JSON
            StringBuilder json = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/"+fichiers[i]+"/"+fichiers[i]+".tsj"));
                String ligne;

                while ((ligne = reader.readLine()) != null) {
                    json.append(ligne);
                }

            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Erreur de format dans le fichier : " + e.getMessage());
            }

            JSONObject jsonObject = new JSONObject(json.toString());

            // On récupère les id de tuiles avec les images associées.
            JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("tiles"));
            ObservableList<Node> nodes = TilePaneCouchesMonde.get(i).getChildren();

            for(int h = 0 ; h < Monde.getSizeMondeHauteur() ; h++){
                for(int l = 0 ; l < Monde.getSizeMondeLargeur() ; l++){
                    if(couchesMap.get(i)[h][l]!=-1) {
                        String chemin = jsonArray.getJSONObject(couchesMap.get(i)[h][l]).getString("image");
                        Image image = gestionImages.getImages(fichiers[i]+"/"+chemin);
                        nodes.add(new ImageView(image));
                    }
                    else{
                        nodes.add(new ImageView());
                    }

                }
            }
        }
    }
}
