package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class GestionImages {

    private static final HashMap<String , Image> Images = new HashMap<>();
    private static GestionImages instance = null;

    public GestionImages(String path) {
        generateImages(path);
    }

    public static GestionImages getInstance() {
        if (instance == null) {
            instance = new GestionImages("C:\\Users\\arthu\\IdeaProjects\\JeuZeldaRefactoring\\src\\main\\resources\\universite_paris8\\iut\\EtrangeEtrange\\texture");
        }
        return instance;
    }

    private void generateImages(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Images.put(folder.getName()+"/"+file.getName(), new Image(file.getPath()));
                } else if (file.isDirectory() && !file.getName().equals("sprite")) {
                    generateImages(file.getPath());
                }
            }
        }
    }

    public Image getImages(String name) {
        return Images.get(name);
    }
}
