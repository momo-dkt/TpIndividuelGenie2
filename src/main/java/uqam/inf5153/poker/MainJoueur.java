package uqam.inf5153.poker;

import java.util.ArrayList;


/**
 *Classe créant la main d'un joueur
 */
public class MainJoueur {
    private ArrayList<Carte> main;
    private String combinaison;
    private Valeur valeurMaximale;

    /**
     *Constructeur de la classe
     * @param main: main du joueur.
     */
    public MainJoueur(ArrayList<Carte>main){
        this.main=main;

    }

    /**
     *Méthode retournant la main du joueur .
     * @return  la main du joueur.
     */
    protected ArrayList<Carte> getMain(){
        return main;
    }

    /**
     *Méthode retournant la combinaison du joueur .
     * @return  la combinaison du joueur.
     */
    protected String getCombinaison(){
        return combinaison;
    }

    /**
     *Méthode modifiant la combinaison du joueur .
     * @param comp: la combinaison remplaçante
     */
    protected void setCombinaison(String comp){
        this.combinaison=comp;
    }

    /**
     *Méthode modifiant la valeur maximale de la main du joueur .
     * @param  valeur:la valeur remplaçante
     */
    protected void setValeurMaximale(Valeur valeur){
        this.valeurMaximale=valeur;
    }

    /**
     *Méthode retournant la valeur maximale de  la main du joueur .
     * @return  la valeur maximale.
     */
    protected Valeur getValeurMaximale(){
        return valeurMaximale;
    }
}
