package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Sommet;

import java.util.*;

public class Aetoile extends AlgoChemin {

    private ArrayList<Position> chemin;

    public Aetoile(Monde monde) {
        this.monde = monde;
        this.chemin = new ArrayList<>();
        construireGraphe(); // Construire le graphe lors de l'initialisation
    }

    // Mettre à jour le graphe pour refléter les changements dans le monde
    public void mettreAJourGraphe() {
        // Réinitialiser les sommets
        for (int y = 0; y < graphe.length; y++) {
            for (int x = 0; x < graphe[0].length; x++) {
                graphe[y][x].setTraversable(monde.getNontraversable()[y][x] == -1);
            }
        }

        // Marquer les positions des entités comme non traversables

    }

    // Trouver le chemin entre deux positions en utilisant l'algorithme A*
    public List<Position> trouverChemin(Position depart, Position arrivee) {
        mettreAJourGraphe();  // Mettre à jour le graphe avant de trouver le chemin

        Sommet sommetDepart = positionToSommet(depart);
        Sommet sommetArrivee = positionToSommet(arrivee);

        if (sommetDepart == null || sommetArrivee == null) {
            return Collections.emptyList();
        }

        PriorityQueue<Noeud> openList = new PriorityQueue<>(Comparator.comparingDouble(Noeud::getF));
        Map<Sommet, Noeud> allNodes = new HashMap<>();

        Noeud startNode = new Noeud(sommetDepart, null, 0, sommetDepart.distance(sommetArrivee));
        openList.add(startNode);
        allNodes.put(sommetDepart, startNode);

        while (!openList.isEmpty()) {
            Noeud currentNode = openList.poll();

            // Si le sommet actuel est le sommet de destination, reconstruire le chemin
            if (currentNode.getSommet().equals(sommetArrivee)) {
                return construireChemin(currentNode);
            }

            // Explorer les voisins du sommet actuel
            for (Sommet voisin : currentNode.getSommet().getVoisins()) {
                double tentativeG = currentNode.getG() + currentNode.getSommet().distance(voisin);

                Noeud voisinNode = allNodes.getOrDefault(voisin, new Noeud(voisin));
                if (tentativeG < voisinNode.getG()) {
                    voisinNode.setParent(currentNode);
                    voisinNode.setG(tentativeG);
                    voisinNode.setH(voisin.distance(sommetArrivee));
                    allNodes.put(voisin, voisinNode);
                    if (!openList.contains(voisinNode)) {
                        openList.add(voisinNode);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    // Reconstruire le chemin en partant du nœud de destination
    private ArrayList<Position> construireChemin(Noeud noeud) {
        ArrayList<Position> chemin = new ArrayList<>();
        while (noeud != null) {
            chemin.add(getCentreSommet(noeud.getSommet()));
            noeud = noeud.getParent();
        }
        Collections.reverse(chemin);
        this.chemin = chemin; // Mettre à jour le chemin
        return chemin;
    }

    // Convertir une position en sommet
    public Sommet positionToSommet(Position position) {
        int x = (int) Math.floor(position.getX());
        int y = (int) Math.floor(position.getY());
        if (x >= 0 && y >= 0 && x < graphe[0].length && y < graphe.length) {
            return graphe[y][x];
        }
        return null;
    }

    // Obtenir le centre d'un sommet pour le chemin final
    public Position getCentreSommet(Sommet sommet) {
        int x = (int) sommet.getPosition().getX();
        int y = (int) sommet.getPosition().getY();
        return new Position(x + 0.5, y + 0.5);
    }

    // Obtenir le chemin trouvé
    public List<Position> getChemin() {
        return chemin;
    }
}
