package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Sommet;

public class Noeud {
    private Sommet sommet;
    private Noeud parent;
    private double g; // Coût du chemin depuis le début
    private double h; // Heuristique (estimation du coût restant)

    public Noeud(Sommet sommet) {
        this.sommet = sommet;
        this.g = Double.MAX_VALUE;
    }

    public Noeud(Sommet sommet, Noeud parent, double g, double h) {
        this.sommet = sommet;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    public Sommet getSommet() {
        return sommet;
    }

    public Noeud getParent() {
        return parent;
    }

    public void setParent(Noeud parent) {
        this.parent = parent;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getF() {
        return g + h; // f = g + h, utilisé pour la priorité dans la file
    }
}