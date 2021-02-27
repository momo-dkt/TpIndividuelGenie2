package uqam.inf5153.poker;


/**
 *Classe créant une carte.
 */
public class Carte {
    private Valeur valeurCarte;
    private Couleur couleurCarte;

    /**
     * Constructeur de la classe.
     * @param valeurCarte:la valeur de la carte.
     * @param couleurCarte: la couleur de la carte.
     */
    public Carte(Valeur valeurCarte,Couleur couleurCarte){
        this.couleurCarte=couleurCarte;
        this.valeurCarte=valeurCarte;
    }

    /**
     * Méthode retournant la valeur de la carte.
     *   @return la valeur de la carte.
     */
    protected Valeur getValeurCarte(){
        return valeurCarte;
    }

    /**
     * Méthode retournant la couleur de la carte.
     *   @return la couleur de la carte.
     */
    protected Couleur getCouleurCarte(){
        return couleurCarte;
    }
}
