package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.Optional;
/**
 *Classe qui initialise les données du jeu.
 */
public class TableDeJeu {

    /**
     *Méthode qui initialise les données .
     * @param args: les mains du jeu.
     * @param nbrJoueurs: le nombre de joueurs.
     * @return  un arraylist de joueurs.
     */
    public ArrayList<Joueur> initialisationDonnees(String[]args, int nbrJoueurs){
        String mainActuelle;
        String [] tMainActuelle;
        String carteActuelle;

        boolean valeurVerif=false;
        boolean couleurVerif=false;
        ArrayList<Joueur>joueurs=new ArrayList();
        for (int i = 0; i < args.length; i++) {
            ArrayList<Carte>carteJoueur=new ArrayList<Carte>();
            mainActuelle = args[i];
            tMainActuelle=mainActuelle.split(" ");

            for (int j = 0; j < tMainActuelle.length; j++) {
                carteActuelle=tMainActuelle[j];
                valeurVerif = Valeur.compris(carteActuelle.charAt(0));
                couleurVerif = Couleur.inclus(carteActuelle.charAt(1));
                if (couleurVerif && valeurVerif) {
                    Optional<Valeur> retourValeur = Valeur.analogieValeur(carteActuelle.charAt(0));
                    Optional<Couleur> retourCouleur = Couleur.analogieCouleur(carteActuelle.charAt(1));
                    if (retourValeur.isPresent() && retourCouleur.isPresent()) {
                        Carte intermediaire = new Carte(retourValeur.get(), retourCouleur.get());
                        carteJoueur.add(intermediaire);
                    }
                }else{
                    System.out.println("ERREUR:Combinaison de cartes invalide");
                    System.exit(-1);
                }

            }


            MainJoueur inter = new MainJoueur(carteJoueur);

            String numJoueur=String.valueOf(nbrJoueurs+1);
            String nomJoueur= "J"+numJoueur;
            Joueur in = new Joueur(inter,0,nomJoueur);

            joueurs.add(in);
            nbrJoueurs++;

        }
        return joueurs;
    }


}
