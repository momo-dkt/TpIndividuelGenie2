package uqam.inf5153.poker;
/**
 * Classe créant un joueur
 */
public class Joueur {
    String nom;
    private MainJoueur cartes;
    int victoires;

    /**
     * Constructeur de la classe
     *   @param  cartes: la main du joueur
     * @param nom :le nom du joueur
     */
    public Joueur(MainJoueur cartes,int victoires,String nom){
        this.cartes=cartes;
        this.victoires=victoires;
        this.nom=nom;

    }

    /**
     * Méthode retournant la main du joueur .
     *   @return la main du joueur.
     */
    protected MainJoueur getCartes(){
        return cartes;
    }
}
