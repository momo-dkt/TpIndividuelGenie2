package uqam.inf5153.poker;

import java.util.Optional;

/**
 * Énumération couleur
 */
public enum Couleur {
    TREFLE('C'),
    HEARTH('H'),
    CARREAU('D'),
    PIQUE('S');
    private char couleur;

    /**
     * Constructeur de la classe .
     *   @param couleur
     */
    private Couleur(char couleur){
        this.couleur=couleur;
    }


    /**
     * Méthode retournant la couleur de la carte.
     *   @return la couleur de la carte.
     */
    protected   char getCouleur(){

        return couleur ;
    }


    /**
     * Méthode qui vérifie si une couleur est inclus dans l'énumération.
     * @param couleurCarte
     *   @return true si inclus , false au cas contraire.
     */
    public static boolean inclus(char couleurCarte){
        for(Couleur c: Couleur.values()){
            if(c.getCouleur()==couleurCarte){
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode prenant un char et retournant l'analogie en Couleur.
     * @param couleurCarte
     *   @return l'analogie.
     */
     public static Optional<Couleur> analogieCouleur(char couleurCarte){
        Optional<Couleur> retourCouleur=Optional.empty();
        for(Couleur c:Couleur.values()){
            if(c.getCouleur()==couleurCarte){
                retourCouleur= Optional.of(c);
            }
        }
        return retourCouleur;
    }
}
