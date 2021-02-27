package uqam.inf5153.poker;

import java.util.Optional;
/**
 *Énuméré de Valeur.
 */
public enum Valeur {
    UN('1'),
    DEUX('2'),
    TROIS('3'),
    QUATRE('4'),
    CINQ('5'),
    SIX('6'),
    SEPT('7'),
    HUIT('8'),
    NEUF('9'),

    TEN('T'),
    VALET('J'),
    REINE ('Q'),
    ROI('K');


    private char val;

    /**
     *Constructeur
     * @param val:la valeur à créer.
     */
    private Valeur(char val){

        this.val=val;
    }


    /**
     *Retourne la Valeur. .
     * @return  val.
     */
     protected char getValeur( ){
        return val;
    }

    /**
     *Méthode faisant l'analogie en valeur .
     * @param valeurCarte: la valeur comparé.
     */
     public static Optional<Valeur> analogieValeur(char valeurCarte){
        Optional<Valeur> retourValeur=Optional.empty();
        for(Valeur v:Valeur.values()){
            if(v.getValeur()==valeurCarte){
                retourValeur= Optional.of(v);
            }
        }
        return retourValeur;

    }

    /**
     *Méthode vérifiant si une valeur est comprise dans l'énuméré.
     * @param  valeurCarte:valeur à vérifier.
     * @return true si compris ,false le cas contraire.
     */
    public static boolean compris(char valeurCarte){
        for(Valeur v: Valeur.values()){

            if(valeurCarte==v.getValeur()){
                return true;
            }


        }
        return false;
    }
}
