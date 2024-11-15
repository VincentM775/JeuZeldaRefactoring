package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Sommet;

public class AlgoChemin {

    protected Monde monde;
    protected Sommet[][] graphe;

    protected void construireGraphe() {
        int hauteur = Monde.getSizeMondeHauteur();
        int largeur = Monde.getSizeMondeLargeur();
        this.graphe = new Sommet[hauteur][largeur];

        for (int y = 0; y < hauteur; y++)
            for (int x = 0; x < largeur; x++)
                this.graphe[y][x] = new Sommet(new Position(x, y), monde.getNontraversable()[y][x] == -1);


        for (int y = 0; y < hauteur; y++)
            for (int x = 0; x < largeur; x++)
                if (graphe[y][x].isTraversable())
                    ajouterVoisins(graphe[y][x], x, y);
    }

    private void ajouterVoisins(Sommet sommet, int x, int y)
    {
        for (Direction dir : Direction.values())
        {
            int nx = x + dir.getX();
            int ny = y + dir.getY();

            if (nx >= 0 && ny >= 0 && nx < graphe[0].length && ny < graphe.length && graphe[ny][nx].isTraversable())
                sommet.addVoisin(graphe[ny][nx]);
        }
    }
}
