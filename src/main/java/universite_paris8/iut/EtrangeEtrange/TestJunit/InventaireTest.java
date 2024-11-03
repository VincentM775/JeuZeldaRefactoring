package universite_paris8.iut.EtrangeEtrange.TestJunit;




import org.junit.Test;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.ElementStockable;

import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class InventaireTest {

    private Inventaire<ElementStockable> inventaire;
    private ElementStockable objet1;
    private ElementStockable objet2;
    private ElementStockable objet3;
    private ElementStockable objet4;


    @Test
    public void testAjoutItem()
    {
        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();



        assertTrue(inventaire.ajoutItem(objet1));
        assertTrue(inventaire.ajoutItem(objet2));
        assertTrue(inventaire.ajoutItem(objet4));
        assertFalse(inventaire.ajoutItem(objet3));
    }

    @Test
    public void testChercheEmplacementStackable()
    {
        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();

        inventaire.ajoutItem(objet1);
        inventaire.ajoutItem(objet4);
        Emplacement<ElementStockable> emplacement = inventaire.chercheEmplacementStackable(objet1);
        assertNotNull(emplacement);


        Emplacement<ElementStockable> emplacement2 = inventaire.chercheEmplacementStackable(objet2);
        assertNull(emplacement2);
    }

    @Test
    public void testChercheEmplacementVide()
    {

        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();



        inventaire.ajoutItem(objet1);
        Emplacement<ElementStockable> emplacement = inventaire.chercheEmplacementVide();
        assertNotNull(emplacement);
        assertTrue(emplacement.estVide());

        inventaire.ajoutItem(objet3);

        emplacement = inventaire.chercheEmplacementVide();
        assertNull(emplacement);



    }

    @Test
    public void testVider() {

        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();



        inventaire.ajoutItem(objet1);
        inventaire.ajoutItem(objet2);
        inventaire.vider();
        assertTrue(inventaire.estVide());
    }

    @Test
    public void testEstPlein()
    {
        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();

        assertFalse(inventaire.estPlein());
        inventaire.ajoutItem(objet1);
        inventaire.ajoutItem(objet2);
        assertTrue(inventaire.estPlein());
    }

    @Test
    public void testEstVide()
    {

        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();

        assertTrue(inventaire.estVide());
        inventaire.ajoutItem(objet1);
        assertFalse(inventaire.estVide());
    }

    @Test
    public void testNombresObjets()
    {
        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();


        assertEquals(0, inventaire.nombresObjets());
        inventaire.ajoutItem(objet1);
        assertEquals(1, inventaire.nombresObjets());
        inventaire.ajoutItem(objet2);
        assertEquals(2, inventaire.nombresObjets());
        inventaire.ajoutItem(objet4);
        assertEquals(3, inventaire.nombresObjets());


    }

    @Test
    public void testGetTailleMax() {

        inventaire = new Inventaire<>(2);
        assertEquals(2, inventaire.getTailleMax());
    }

    @Test
    public void testRetourneObjets()
    {

        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();

        inventaire.ajoutItem(objet1);
        inventaire.ajoutItem(objet2);
        inventaire.ajoutItem(objet4);


        ArrayList<ElementStockable> objets = inventaire.enleverObjet(0);
        assertEquals(2, objets.size());


        objets = inventaire.enleverObjet(1);
        assertEquals(1, objets.size());

    }

    @Test
    public void testRetourneObjet()
    {


        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();


        inventaire.ajoutItem(objet1);
        inventaire.ajoutItem(objet2);
        inventaire.ajoutItem(objet4);

        ElementStockable objet = inventaire.retourneObjet(1);
        assertNotNull(objet);
        assertEquals(objet2.getNom(), objet.getNom());

        objet = inventaire.retourneObjet(0);
        assertNotNull(objet);
        assertEquals(objet1.getNom(), objet.getNom());
    }

    @Test
    public void testTrouveObjet()
    {
        inventaire = new Inventaire<>(2);
        objet1 = new Potion();
        objet2 = new Arc();
        objet3 = new Arc();
        objet4 = new Potion();

        inventaire.ajoutItem(objet1);
        inventaire.ajoutItem(objet2);

        ElementStockable objet = inventaire.trouveObjet(Potion.class);
        assertNotNull(objet);
        assertEquals(objet.getNom(), objet1.getNom());


    }
}
