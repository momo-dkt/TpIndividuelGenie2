package uqam.inf5153.poker;
import java.util.*;

/**
 *     *Classe Main
 */
public class Main {
    static String sortie=" ";


    /**
     *Méthode main
     * @param args
     */
    public static void main(String[] args) {

        if(args.length > 0) {
            ligneDeCommande(args);
        }else{
            entreeManuelle();
        }



    }


    /**
     *Méthode gérant l'entrée du jeu au clavier .
     */
     public static void entreeManuelle(){
        boolean entreeErronee;
        ArrayList<Joueur>listeJoueurs=new ArrayList<>();
        ArrayList<Joueur>joueurTotal=new ArrayList();
        ArrayList<Optional<Joueur>>joueurGagnant=new ArrayList<>();
        String sortie1;
        int nbrJoueurs=0;
        String[]listeMain;
        Arbitre arbitre = new Arbitre();
        TableDeJeu table = new TableDeJeu();
        char nouvellePartie;
        int nbrPartie=0;
        int nbrTotal=0;
        do {
            nbrJoueurs = saisieNbrJoueurs(nbrTotal);
            if(nbrPartie==0) {
                if (nbrJoueurs == 1) {
                    System.out.println("ERREUR:Il faut au moins 2 joueurs pour pouvoir jouer");
                    System.exit(-1);
                }
            }
            listeMain = saisieMain(nbrJoueurs,nbrTotal);
            entreeErronee = arbitre.verificationMain(listeMain);
            if (!entreeErronee) {

                listeJoueurs = table.initialisationDonnees(listeMain,nbrTotal);
                nbrTotal+=nbrJoueurs;
                for(Joueur inter:listeJoueurs){
                    joueurTotal.add(inter);
                }
                arbitre.trouverCombinaison(joueurTotal);
                joueurGagnant = arbitre.trouverGagnant(joueurTotal);
                sortie1 = arbitre.afficherVainqueur(joueurGagnant, joueurTotal);
                System.out.println(sortie1);
                nbrPartie++;
            } else {
                System.out.println("ERREUR: Données invalides");
                System.exit(-1);
            }
            nouvellePartie=validationNouvellePartie();
        }while (nouvellePartie == 'o' || nouvellePartie == 'O' );
    }


    /**
     *Méthode gérant l'entrée du jeu par args .
     * @param args
     */
    public static void ligneDeCommande(String[]args){
        boolean entreeErronee;
        ArrayList<Joueur>listeJoueurs=new ArrayList<>();
        ArrayList<Joueur>joueurTotal=new ArrayList();
        ArrayList<Optional<Joueur>>joueurGagnant=new ArrayList<>();
        String sortie1;
        int nbrJoueurs=0;
        Arbitre arbitre = new Arbitre();
        TableDeJeu table = new TableDeJeu();
        char nouvellePartie;
        int nbrPartie=0;
        int nbrTotal=0;


        do {

            if(nbrPartie==0) {

                if (args.length == 1) {
                    System.out.println("ERREUR:Il faut au moins 2 joueurs pour pouvoir jouer");
                    System.exit(-1);
                }
                nbrJoueurs=args.length;
            }else{
                nbrJoueurs = saisieNbrJoueurs(nbrTotal);
                args=saisieMain(nbrJoueurs,nbrTotal);
            }

            entreeErronee = arbitre.verificationMain(args);
            if (!entreeErronee) {
                listeJoueurs = table.initialisationDonnees(args,nbrTotal);
                nbrTotal+=nbrJoueurs;
                for(Joueur inter:listeJoueurs){
                    joueurTotal.add(inter);
                }

                arbitre.trouverCombinaison(joueurTotal);
                joueurGagnant = arbitre.trouverGagnant(joueurTotal);
                sortie1 = arbitre.afficherVainqueur(joueurGagnant, joueurTotal);
                System.out.println(sortie1);
                nbrPartie++;
            } else {
                System.out.println("ERREUR: Données invalides");
                System.exit(-1);
            }
            nouvellePartie=validationNouvellePartie();

        }while(nouvellePartie == 'o' || nouvellePartie == 'O');

    }


    /**
     *Méthode de la validation d'une nouvelle partie .
     * @return o ou O ou n ou N.
     */
     public static char validationNouvellePartie(){
        char nouvellePartie=' ';
        boolean valide;

        do {
            valide=true;
            Scanner s = new Scanner(System.in);
            System.out.print("Voulez vous recommencez le jeu ?:");
            try{
                nouvellePartie = s.nextLine().charAt(0);
            }catch(InputMismatchException  e){
                System.out.println("ERREUR:saisie non valide\n ");
                valide=false;

            }
            if (nouvellePartie != 'o' && nouvellePartie != 'O' && nouvellePartie != 'n' && nouvellePartie != 'N' && valide) {
                System.out.println("ERREUR:entrée invalide\n");
            }
        } while ((nouvellePartie != 'o' && nouvellePartie != 'O' && nouvellePartie != 'n' && nouvellePartie != 'N') || !valide);


        return nouvellePartie;
    }

    /**
     *Méthode de la validation de la saisie du nombre de joueurs  .
     * @param nbrJoueursActuel:le nombre de joueurs actuel du jeu.
     * @return le nombre de joueurs à ajouter pour la nouvelle partie
     */
     public static int saisieNbrJoueurs(int nbrJoueursActuel){
        int nbrTotalJoueur=0;
        boolean valide;
        int nbrJoueurs=0;
        if(nbrJoueursActuel==0) {
            do {
                valide = true;
                Scanner s = new Scanner(System.in);
                System.out.print("Entrez le nombre de joueurs:");
                try {
                    nbrJoueurs = s.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERREUR:saisie non valide\n ");
                    valide = false;

                }
                if (valide && (nbrJoueurs < 1 || nbrJoueurs > 8)) {
                    System.out.println("ERREUR:nombre de joueurs doit entre compris entre 1 et 8.\n");
                }

            } while ((nbrJoueurs < 1 || nbrJoueurs > 8) || !valide);
        }else{
            do {
                valide = true;
                Scanner s = new Scanner(System.in);
                System.out.print("Entrez le nombre de joueurs que vous voulez rajouter :");
                try {
                    nbrJoueurs = s.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("ERREUR:saisie non valide\n ");
                    valide = false;

                }
                nbrTotalJoueur=nbrJoueursActuel+nbrJoueurs;
                if (valide && (nbrTotalJoueur < 1 || nbrTotalJoueur > 8)) {
                    System.out.println("ERREUR:nombre de joueurs doit entre compris entre 1 et 8.\n");
                }

            } while ((nbrTotalJoueur< 1 || nbrTotalJoueur > 8) || !valide);

        }
        return nbrJoueurs;
    }

    /**
     *Méthode pour la saisie des mains par les joueurs .
     * @param nbrJoueursEnPus: le nombre de joueurs à rajouter.
     * @param nbrTotal :le nombre de joueurs total .
     * @return  les nouvelles mains.
     */
    public static String[] saisieMain(int nbrJoueursEnPus ,int nbrTotal ){
        String[]listeMain=new String[nbrJoueursEnPus];
        String main;
        Scanner s=new Scanner(System.in);
        for(int i=0;i<nbrJoueursEnPus;i++){
            System.out.print("J"+String.valueOf(nbrTotal+1)+" entrez votre main:");
            main=s.nextLine();
            listeMain[i]=main;
            nbrTotal++;
        }

        return listeMain;
    }


    /**
     *Méthode d'invocation du jeu pour les tests .
     * @param args
     */
    public static void ligneDeCommandePourTest(String[]args){
        boolean entreeErronee;
        ArrayList<Joueur>listeJoueurs=new ArrayList<>();
        ArrayList<Joueur>joueurTotal=new ArrayList();
        ArrayList<Optional<Joueur>>joueurGagnant=new ArrayList<>();
        int nbrJoueurs=0;
        Arbitre arbitre = new Arbitre();
        TableDeJeu table = new TableDeJeu();
        char nouvellePartie;
        int nbrPartie=0;
        int nbrTotal=0;




            if(nbrPartie==0) {

                if (args.length == 1) {
                    System.out.println("ERREUR:Il faut au moins 2 joueurs pour pouvoir jouer");
                    System.exit(-1);
                }
                nbrJoueurs=args.length;
            }else{
                nbrJoueurs = saisieNbrJoueurs(nbrTotal);
                args=saisieMain(nbrJoueurs,nbrTotal);
            }

            entreeErronee = arbitre.verificationMain(args);
            if (!entreeErronee) {
                listeJoueurs = table.initialisationDonnees(args,nbrTotal);
                nbrTotal+=nbrJoueurs;
                for(Joueur inter:listeJoueurs){
                    joueurTotal.add(inter);
                }

                arbitre.trouverCombinaison(joueurTotal);
                joueurGagnant = arbitre.trouverGagnant(joueurTotal);
                sortie = arbitre.afficherVainqueur(joueurGagnant, joueurTotal);
                System.out.println(sortie);
                nbrPartie++;
            } else {
                System.out.println("ERREUR: Données invalides");
                System.exit(-1);
            }




    }






}
