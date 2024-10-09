package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Sommet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends AlgoChemin {

    private ArrayList<Sommet> chemins;
    private HashMap<Sommet, Sommet> predecesseurs;
    private int xArrive, yArrive;

    public BFS() {
        monde = null;
        chemins = new ArrayList<>();
    }

    public void chercherChemin(Monde monde, Position positionDepart, Position positionArrive) {
        this.monde = monde;
        this.xArrive = (int) positionArrive.getX();
        this.yArrive = (int) positionArrive.getY();
        construireGraphe();
        trouverChemin((int) positionDepart.getX(), (int) positionDepart.getY());
    }

    private void trouverChemin(int x, int y) {
        Queue<Sommet> sommetsAvisite = new LinkedList<>();
        predecesseurs = new HashMap<>();
        boolean cheminTrouver = false;

        sommetsAvisite.add(graphe[y][x]);
        predecesseurs.put(graphe[y][x], null);

        while (!sommetsAvisite.isEmpty() && !cheminTrouver)
        {
            Sommet actuelle = sommetsAvisite.poll();

            for (int i = 0;i<actuelle.getVoisins().size() && !cheminTrouver;i++)
            {
                Sommet voisin = actuelle.getVoisins().get(i);

                if (!predecesseurs.containsKey(voisin))
                {
                    predecesseurs.put(voisin, actuelle);
                    sommetsAvisite.add(voisin);

                    if (cheminTrouver(voisin.getPosition().getX(), voisin.getPosition().getY()))
                    {
                        cheminTrouver = true;
                        construireChemin(voisin);
                    }
                }
            }
        }
    }

    private void construireChemin(Sommet sommet)
    {
        this.chemins.clear();
        Sommet actuel = sommet;

        while (actuel != null)
        {
            this.chemins.add(0, actuel);
            actuel = predecesseurs.get(actuel);
        }
    }

    private boolean cheminTrouver(double x, double y) {return x == xArrive && y == yArrive;}

    public Position prochainePosition()
    {
        Position position = null;

        if (chemins != null && !chemins.isEmpty())
            position =  chemins.remove(0).getPosition();


        return position;
    }
}
