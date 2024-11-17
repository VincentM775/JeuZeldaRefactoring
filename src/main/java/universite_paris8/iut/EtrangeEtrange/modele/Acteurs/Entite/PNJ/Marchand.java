package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionDelaieRespecter;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.ActionVendre;

import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action.Soigner;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.*;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Environnement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class Marchand extends ESP implements Dropable
{

    private int cycle;
    private Sac sac;

    private Prompt prompt;

    public Marchand(double x, double y, Direction direction) {
        super(x, y, direction, 10, 10 , 10, 0.5, new Hitbox(0.5,0.5));
        this.cycle = 0;
        this.sac = new Sac();

        initPrompt();

    }


    public void genereMarchandises()
    {
        sac.ajoutItem(new Epee());
        sac.ajoutItem(new Arc());
        sac.ajoutItem(new Potion());
    }

    @Override
    protected Pattern initPattern() {
        return new ConditionDelaieRespecter(this::genereMarchandises,5000);
    }

    @Override
    public String typeActeur() {
        return "marchand";
    }

    @Override
    public void derniereAction() {

    }

    @Override
    public boolean estUnEnemie() {return false;}

    @Override
    public void subitAttaque(ElementDommageable causeDegat, EntiteOffensive entiteOffensif){

    }

    private void initPrompt()
    {
        Prompt racine = new Prompt("Bonjour ! Que vous ramene ici ?",null);

        Prompt reponseRacine1 = new Prompt("Voici ce que je propose.",new ActionVendre(this));



        Prompt reponseRacine2 = new Prompt("Vous avez entendu parlé du monstre qui rôde dans dans les coins",null);


        racine.ajoutPrompt(reponseRacine1,"J'aimerais marchander un peu avec avec vous");
        racine.ajoutPrompt(reponseRacine2,"J'aimerais parler un peu..");

        Prompt reponseReponceRacine2 = new Prompt("Faite attention...   D'ailleur, attendez je vais vous soigner !",new Soigner());
        reponseRacine2.ajoutPrompt(reponseReponceRacine2,"");

        prompt = racine;
    }


    public void drop() {
        for (Emplacement<ElementStockable> objets : sac.getInventaire())
        {
            ArrayList<ElementStockable> obs = objets.enleverToutLesObjets();

            for (ElementStockable objet : obs) {
                Environnement.getInstance().ajouterDropAuSol(new DropAuSol(objet, obs.size(), new Position(position.getX(), position.getY())));
            }
        }
    }

    public Sac getMarchandise()
    {
        return sac;
    }

    public Prompt getPrompt(){
        return prompt;
    }


}