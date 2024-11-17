module com.example.essaie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires javafx.media;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.desktop;

    opens universite_paris8.iut.EtrangeEtrange to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange;
    exports universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;
    opens universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.controller;
    opens universite_paris8.iut.EtrangeEtrange.controller to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Parametres;
    opens universite_paris8.iut.EtrangeEtrange.modele.Parametres to javafx.fxml;

    opens universite_paris8.iut.EtrangeEtrange.TestJunit to org.junit.platform.commons; // Ouvre le package des tests pour JUnit
    exports universite_paris8.iut.EtrangeEtrange.TestJunit;
    exports universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;
    opens universite_paris8.iut.EtrangeEtrange.modele.Utilitaire to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos;
    opens universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Algos to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;
    opens universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;
    opens universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss;
    opens universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss to javafx.fxml;
    exports universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;
    opens universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns to javafx.fxml;

}