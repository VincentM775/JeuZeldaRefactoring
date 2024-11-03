package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import org.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GestionAffichageMap {
    private ArrayList<TilePane> TilePaneCouchesMonde;
    private Monde monde;
    private HashMap<String , Image> imagesMap;

    public GestionAffichageMap(Monde monde, TilePane sol, TilePane traversable, TilePane nontraversable){
        this.TilePaneCouchesMonde = new ArrayList<>();
        this.TilePaneCouchesMonde.add(sol);
        this.TilePaneCouchesMonde.add(traversable);
        this.TilePaneCouchesMonde.add(nontraversable);
        this.monde = monde;
        this.imagesMap = new HashMap<>();
    }


    /**
     * Permet d'afficher la map depuis les fichiers JSON des jeux de tuiles crée avec le logiciel Tiled
     */
    public void afficherMondeJSON(){
        String[] fichiers = {"sol", "traversable", "nontraversable"};
        ArrayList<int[][]> couchesMap = monde.getToutesLesCouches();
        for(TilePane tilePaneCouchesMonde : TilePaneCouchesMonde)
            tilePaneCouchesMonde.getChildren().clear();

        for(int i = 0 ; i < 3 ; i++){
            System.out.println(i);
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
            TilePane tilePane = TilePaneCouchesMonde.get(i);

            for(int h = 0 ; h < Monde.getSizeMondeHauteur() ; h++){
                for(int l = 0 ; l < Monde.getSizeMondeLargeur() ; l++){
                    if(couchesMap.get(i)[h][l]!=-1) {
                        String chemin = jsonArray.getJSONObject(couchesMap.get(i)[h][l]).getString("image");
                        Image image = imagesMap.get(fichiers[i] + "/" + chemin);
                        if (image == null) {
                            image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/" + fichiers[i] + "/" + chemin);
                            imagesMap.put(fichiers[i] + "/" + chemin, image);
                        }
                        tilePane.getChildren().add(new ImageView(image));
                    }
                    else{
                        tilePane.getChildren().add(new ImageView());
                    }

                }
            }
        }
    }
}
