package uqam.inf5153.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 *Classe implémentant la l'interface RegleDuJeu.
 */
public class Arbitre implements RegleDuJeu {

    /**
     * Méthode trouvant le gagnant après la vérification des combinaisons.
     * @param joueurs:la liste des joueurs.
     * @return le joueur gagnant.
     */
    public ArrayList<Optional<Joueur>> trouverGagnant(ArrayList<Joueur>joueurs){
        Optional<Valeur> valeurMax=Optional.empty();
        Optional<Valeur> v=Optional.empty();
        Optional<Joueur>gagnant=Optional.empty();
        ArrayList<Joueur>joueurAvecFlush=new ArrayList<>();
        ArrayList<Joueur>joueurAvecPair=new ArrayList<>();
        ArrayList<Joueur>joueurAvecPlusHauteCarte=new ArrayList<>();
        ArrayList<Optional<Joueur>>vainqueur=new ArrayList();
        for (Joueur joueur: joueurs){
            if (joueur.getCartes().getCombinaison().startsWith("Couleur")){
                joueurAvecFlush.add(joueur);
            }else if (joueur.getCartes().getCombinaison().startsWith("Paire")){
                joueurAvecPair.add(joueur);
            }else if (joueur.getCartes().getCombinaison().startsWith("Plus haute carte")){
                joueurAvecPlusHauteCarte.add(joueur);
            }

        }

        if(!joueurAvecFlush.isEmpty()){
            vainqueur=maxValeurGagnant(joueurAvecFlush);
        }else if (!joueurAvecPair.isEmpty()){
            vainqueur=maxValeurGagnant(joueurAvecPair);
        }else if(!joueurAvecPlusHauteCarte.isEmpty()){
            vainqueur=maxValeurGagnant(joueurAvecPlusHauteCarte);
        }
        return vainqueur;
    }


    /**
     * Trouve le gagnant dans joueur ayant la plus haute valeur.
     * @param joueur:la liste des joueurs ayant soit un flush , une paire ou une plus hauteCarte.
     * @return le joueur gagnant.
     */
    public ArrayList<Optional<Joueur>> maxValeurGagnant(ArrayList<Joueur>joueur){
        Optional<Valeur> valeurMax=Optional.empty();
        Optional<Valeur> v=Optional.empty();
        ArrayList<Optional<Joueur>>vainqueur=new ArrayList();
        Optional<Joueur>gagnant=Optional.empty();
        if(joueur.size()==1){
            vainqueur.add(Optional.of(joueur.get(0)));
            return vainqueur;
        }
        valeurMax=maxVal(joueur.get(0).getCartes());
        gagnant=Optional.of(joueur.get(0));
        vainqueur.add(gagnant);
        for(int i=1;i<joueur.size(); ++i){
            v=maxVal(joueur.get(i).getCartes());

            if(getTable().get(v.get().getValeur())>getTable().get(valeurMax.get().getValeur())){
                vainqueur.clear();
                gagnant=Optional.of(joueur.get(i));
                vainqueur.add(gagnant);
                valeurMax=maxVal(joueur.get(i).getCartes());
            }else if(getTable().get(v.get().getValeur())==getTable().get(valeurMax.get().getValeur())) {
                gagnant = Optional.of(joueur.get(i));
                vainqueur.add(gagnant);
            }
        }

        if (vainqueur.size()==1){
            if(vainqueur.get(0).isPresent())
                vainqueur.get(0).get().victoires++;
        }

        return vainqueur;
    }


    /**
     * Trouve la combinaison des joueurs
     * @param joueurs:la liste des joueurs.
     */
    public  void trouverCombinaison(ArrayList<Joueur>joueurs){


        for(Joueur joueur: joueurs) {

            Optional<Couleur>flush=detectionFlush(joueur);
            Optional<Valeur>pair=detectionPair(joueur);
            Optional<Valeur>plusHauteCarte=maxVal(joueur.getCartes());
            joueur.getCartes().setValeurMaximale(plusHauteCarte.get());

            if (flush.isPresent()) {
                joueur.getCartes().setCombinaison("Couleur à " + joueur.getCartes().getMain().get(0).getCouleurCarte());
            }else if (pair.isPresent()) {
                joueur.getCartes().setCombinaison("Paire de  "+ pair.get().name());
            }else if (plusHauteCarte.isPresent()){
                joueur.getCartes().setCombinaison("Plus haute carte " + plusHauteCarte.get().name() );
            }



        }



    }

    /**
     * Detecte un flush.
     * @param joueur:un joueur.
     * @return la couleur si c'est un flush ou rien si c'est le cas contraire.
     */
     public Optional<Couleur> detectionFlush(Joueur joueur) {
        Optional<Couleur>c=Optional.empty();
        boolean combinaisonCouleur=true;

        c = Optional.of(joueur.getCartes().getMain().get(0).getCouleurCarte());
        for (int i = 1; i < joueur.getCartes().getMain().size(); ++i) {
            Couleur comparante = joueur.getCartes().getMain().get(i).getCouleurCarte();
            if (c.get().getCouleur() != comparante.getCouleur()) {
                combinaisonCouleur = false;
                c=Optional.empty();

            }
            if (!combinaisonCouleur) {
                break;
            }


        }
        return c;
    }


    /**
     * Detecte une paire .
     * @param joueur:un joueur.
     * @return la valeur de la paire si c'est une paire ou rien si c'est le cas contraire.
     */
    public   Optional<Valeur> detectionPair(Joueur joueur){
        Optional<Valeur> retourValeur=Optional.empty();
        boolean pairValeur=false;
        for(int i = 0; i < joueur.getCartes().getMain().size(); i++) {
            for(int j = i+1; j < joueur.getCartes().getMain().size() ; j++) {
                Valeur val1=joueur.getCartes().getMain().get(i).getValeurCarte();
                Valeur val2=joueur.getCartes().getMain().get(j).getValeurCarte();
                if (val1.getValeur()==val2.getValeur()){
                    retourValeur=Optional.of(val1);
                    pairValeur=true;

                }
                if (pairValeur){
                    break;
                }

            }
            if (pairValeur) {
                break;
            }
        }

        return retourValeur;
    }


    /**
     * Detecte une valeur maximale .
     * @param m:la main d'un joueur.
     * @return la valeur de la main ou rien au cas contraire.
     */
    public  Optional<Valeur> maxVal(MainJoueur m){
        Optional<Valeur>v=Optional.empty();
        int max=getTable().get(m.getMain().get(0).getValeurCarte().getValeur());
        v=Optional.of(m.getMain().get(0).getValeurCarte());
        for(Carte c: m.getMain()){
            if(getTable().get(c.getValeurCarte().getValeur())>max){
                max=getTable().get(c.getValeurCarte().getValeur());
                v=Optional.of(c.getValeurCarte());
            }
        }
        return v;
    }


    /**
     * Vérifie l'ensemble des mains du jeu  .
     * @param carteJoueurs:l'ensemble des mains du jeu .
     * @return true si les mains sont valides , false le cas contraire.
     */
    public  boolean verificationMain(String [] carteJoueurs) {
        boolean resultat=false;
        String[] main;
        String mainActuelle;

        for (String carteJoueur : carteJoueurs) {
            mainActuelle = carteJoueur.trim().toUpperCase();
            main = mainActuelle.split(" ");
            if (main.length != 5) {
                return true;
            }

            for (String d : main) {
                if (d.length() != 2) {
                    resultat = true;
                    break;
                }

            }

        }



        return resultat;
    }

    /**
     * Affiche le vainqueur.
     * @param gagnant:le gagnant de la partie.
     * @param listeJoueurs: la liste des joueurs de la partie.
     * @return l'affichage du vainqueur.
     */
     public String afficherVainqueur(ArrayList<Optional<Joueur>> gagnant,ArrayList<Joueur>listeJoueurs){
        StringBuilder sortie= new StringBuilder(" ");
        if(gagnant.size()==1) {

            listeJoueurs.remove(gagnant.get(0).get());
            sortie = new StringBuilder(gagnant.get(0).get().getCartes().getCombinaison() + " pour ");
            sortie.append(gagnant.get(0).get().nom).append(" bat ");

            for (Joueur j : listeJoueurs) {
                sortie.append(j.getCartes().getCombinaison()).append(" pour ").append(j.nom).append(", ");

            }
            listeJoueurs.add(gagnant.get(0).get());

        }else if (gagnant.size()>1){
            sortie = new StringBuilder("Tie entre ");
            for(Optional<Joueur> j1:gagnant){
                Joueur unDesVainqueurs= j1.get();

                sortie.append(unDesVainqueurs.nom).append(" ").append(unDesVainqueurs.getCartes().getCombinaison()).append(",");
                System.out.println();


            }

        }

        return sortie.toString();
    }

    /**
     * Construisez le tableau de comparaison utilisé pour comparer les cartes ( deux est 2, ..., la reine est 12 et le roi est 13,l'as est 14).
     * @return la valeur
     */
    private Map<Character, Integer> getTable() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('2', 2);  map.put('3', 3);
        map.put('4', 4);  map.put('5', 5);  map.put('6', 6);
        map.put('7', 7);  map.put('8', 8);  map.put('9', 9);
        map.put('T', 10); map.put('J', 11); map.put('Q', 12);
        map.put('K', 13);map.put('1', 14);
        return map;
    }

}
