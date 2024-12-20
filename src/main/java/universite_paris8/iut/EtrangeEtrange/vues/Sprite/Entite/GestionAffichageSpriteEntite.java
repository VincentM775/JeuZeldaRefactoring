package universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Archer;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.ComparePositionSprite;

import java.util.ArrayList;

public class GestionAffichageSpriteEntite implements ListChangeListener<Acteur>
{
    /**
     * On stock toutes les images des sprites :
     * dans chaque tableau d'image 2D, la première ligne correspond au haut, la 2eme haut bas, 3eme gauche, 4eme droite
     *
     * Dans la liste, le premier est le chevalier, pnjtest, roiSquelette, slime, squelette
     */
    private static ArrayList<Image[][]> imagesSprite;
    private Pane paneEntite;
    private ArrayList<SpriteEntite> animationSprites;
    private long derniereApelle = 0;
    public GestionAffichageSpriteEntite(Pane paneEntite){
        this.paneEntite = paneEntite;
        this.animationSprites = new ArrayList<>();
        initialisationSprites();
        SpriteEntite.setGestionAffichageSpriteEntite(this);
    }

    public void initialisationSprites(){
        imagesSprite = new ArrayList<>();
        String[] directions = {"haut","bas","gauche","droite"};
        String[] skins = {"chevalier","pnjtest","roiSquelette","slime","squelette","archer"};
        for(int s = 0 ; s < skins.length ; s++){
            String skin = skins[s];
            imagesSprite.add(new Image[directions.length][6]);
            for(int i = 0 ; i < directions.length ; i++){
                String direction = directions[i];
                for(int j = 0 ; j < 6 ; j++){
                    imagesSprite.get(s)[i][j] = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+ skin +"/"+direction+(j+1)+".png");
                }
            }
        }
    }
    @Override
    public void onChanged(Change<? extends Acteur> change) {
        while(change.next()){
            for (Acteur entite : change.getAddedSubList()) {
                if(entite instanceof Entite)
                    creeSprite(entite);
            }
            for(Acteur entite : change.getRemoved()) {
                if(entite instanceof Entite)
                    suprimmerSprite(entite);
            }
        }
    }
    /**
     * Choisi le skin de sprite adéquat en fonction de la class de l'entité, et crée son sprite animé qui est directement ajouté à la vue
     * @param entite
     */
    public void creeSprite(Acteur entite)
    {
        int skin;
        int vitesse;
        double colorAdjust = 0;

        if (entite.getClass().equals(Guerrier.class)) {
            skin = 0;
            vitesse = 1;
        } else if (entite.getClass().equals(Archer.class)) {
            skin = 5;
            vitesse = 2;
        } else if (entite.getClass().equals(Squelette.class)) {
            skin = 4;
            vitesse = 1;
        } else if (entite.getClass().equals(Slime.class)) {
            skin = 3;
            colorAdjust = Math.random() * 2 - 1;
            vitesse = 2;
        } else if (entite.getClass().equals(RoiSquelette.class)) {
            skin = 2;
            vitesse = 1;
        } else {
            skin = 0;
            vitesse = 0;
        }

        SpriteEntite animationSprite = new SpriteEntite(entite, skin, vitesse, colorAdjust);

        animationSprites.add(animationSprite);

        paneEntite.getChildren().add(animationSprite.getSpriteEntite()); // Ajouter le sprite de l'entité

        if (!(entite instanceof Joueur))
            paneEntite.getChildren().add(animationSprite.ajoutBarrePv());
        listenerPosition(entite);
    }

    /**
     * Ajoute un listenner sur la position de l'entité, permettant de superposer correctement les entités entre elle dans l'affichage
     * @param entite
     */
    public void listenerPosition(Acteur entite)
    {
        entite.getPosition().getYProperty().addListener((old, obs, nouv)->
                animationSprites.sort(new ComparePositionSprite())
        );
        entite.getPosition().getXProperty().addListener((old, obs, nouv)->
                animationSprites.sort(new ComparePositionSprite())
        );
    }

    /**
     * Utiliser pour ajouter le joueur dans les sprites dans l'ajouter aux entités du monde
     * @param joueur
     */
    public void ajouterJoueur(Joueur joueur){
        creeSprite(joueur);
        joueur.getStatsPv().getPvActuelleProperty().addListener((obs, old, nouv)->{
            if(joueur.getStatsPv().getPv()<=0)
                suprimmerSprite(joueur);
        });
        listenerPosition(joueur);
    }

    /**
     * Suprimme le sprite de la vue dès qu'une entité est retiré de la liste des entités du monde
     * @param entite
     */
    public void suprimmerSprite(Acteur entite){
        SpriteEntite animationSprite = null;
        for(int i = 0 ; i < animationSprites.size() ; i++){
            if(animationSprites.get(i).getId() == entite.getID()){
                animationSprite = animationSprites.get(i);
            }
        }
        if(animationSprite!=null) {
            paneEntite.getChildren().remove(animationSprite.getSpriteVie());
            paneEntite.getChildren().remove(animationSprite.getSpriteEntite());
            animationSprites.remove(animationSprite);
        }
    }

    public void miseAjour()
    {
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= 100 )
        {
            for(SpriteEntite animationSprite : animationSprites){
                if(animationSprite.getEntite().isSeDeplace())
                    animationSprite.miseAJourAnimation();
                else
                    animationSprite.finAnimationMarche();
                if(animationSprite.getAppliquerEffet())
                    animationSprite.arreterEffet();
            }
            derniereApelle = apelle;
        }
    }
    public ArrayList<Image[][]> getImagesSprite() {
        return imagesSprite;
    }
}
