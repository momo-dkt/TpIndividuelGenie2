package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.Optional;

/**
 *Interface de la règle du jeu.
 */
public  interface RegleDuJeu {
    boolean verificationMain(String [] carteJoueurs);
    Optional<Valeur> detectionPair(Joueur joueur);
    Optional<Couleur> detectionFlush(Joueur joueur);
    Optional<Valeur> maxVal(MainJoueur m);
    ArrayList<Optional<Joueur>> maxValeurGagnant(ArrayList<Joueur>joueur);
    ArrayList<Optional<Joueur>>  trouverGagnant(ArrayList<Joueur>joueurs);


}
