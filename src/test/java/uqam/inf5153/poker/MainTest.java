package uqam.inf5153.poker;


import static org.junit.Assert.*;



import org.junit.BeforeClass;
import org.junit.Test;

/**
 *Classe de test.
 */
public  class MainTest {
    private static Arbitre arbitre;
    private static TableDeJeu table;


    @BeforeClass
    public static void setUp() throws Exception {
         arbitre=new Arbitre();
         table=new TableDeJeu();

    }



    /**
     *Méthode testant les cartes excessives
     */
     @Test public void tropDeCartes() {
        String J1 = "3H 8H JH KH 7H 7C";
        String J2 = "2D 5D QD KD 7D";
        String J3 =  "3H 5D JH KD 7D";
        String J4=   "1S 4C 1H TD 3S  ";
        String [] carteJoueurs=new String[4];
        carteJoueurs[0]=J1;
        carteJoueurs[1]=J2;
        carteJoueurs[2]=J3;
        carteJoueurs[3]=J4;
        boolean nonValide=arbitre.verificationMain(carteJoueurs);
        assertTrue(nonValide);
    }

    /**
     *Méthode testant les cartes non complètes
     */
     @Test public void pasAssezDeCartes() {
        String J1 = "3H 8H JH KH 7H ";
        String J2 = "2D 5D QD KD ";
        String J3 =  "3H 5D JH KD ";
        String J4=   "1S 4C 1H   ";
        String [] carteJoueurs=new String[4];
        carteJoueurs[0]=J1;
        carteJoueurs[1]=J2;
        carteJoueurs[2]=J3;
        carteJoueurs[3]=J4;
        boolean nonValide=arbitre.verificationMain(carteJoueurs);
        assertTrue(nonValide);
    }

    /**
     *Méthode trouvant le gagnant "Flush"
     */
     @Test public void trouverGagnantFlush() {
        String J1 = "2D 5D QD KD 7D";
        String J2 = "1S 1C KH TD 3S";
        String J3 = "1S 2C 3H 4D 5D";
        Main.ligneDeCommandePourTest(new String[] {J1,J2,J3});
        assertEquals("Couleur à CARREAU pour J1 bat Paire de  UN pour J2, Plus haute carte UN pour J3, ", Main.sortie);
    }

    /**
     *Méthode trouvant le gagnant "Paire"
     */
    @Test public void trouverGagnantPair() {
        String J1 = "2D 7H QD KD 7D";
        String J2 = "1S 2C KH TD 3S";
        String J3 = "TS 2C 3H 4D 5D";
        Main.ligneDeCommandePourTest(new String[] {J1, J2,J3});
        assertEquals("Paire de  SEPT pour J1 bat Plus haute carte UN pour J2, Plus haute carte TEN pour J3, ", Main.sortie);
    }

    /**
     *Méthode trouvant le gagnant "PlusHauteCarte"
     */
     @Test public void trouverGagnantPlusHauteCarte() {
        String J1 = "2D 8H QD KD 7D";
        String J2 = "4S 2C KH TD 3S";
        String J3 = "1S 2C 3H 4D 5D";
        Main.ligneDeCommandePourTest(new String[] {J1,J2,J3});
        assertEquals("Plus haute carte UN pour J3 bat Plus haute carte ROI pour J1, Plus haute carte ROI pour J2, ", Main.sortie);
    }

    /**
     *Méthode testant l'égalité de plusieurs cartes avec la combinaison "Flush"
     */
    @Test public void tieFlush() {
        String J1 = "1D 8D QD KD 7D";
        String J2 = "1S 2S KS TS 3S";
        String J3 = "1H 2H 3H 4H 5H";
        String J4=  "3H 4D 5S 6C 7S";
        Main.ligneDeCommandePourTest(new String[] {J1,J2,J3,J4});

        assertEquals("Tie entre J1 Couleur à CARREAU,J2 Couleur à PIQUE,J3 Couleur à HEARTH,",Main.sortie );
    }

    /**
     *Méthode testant l'égalité de plusieurs cartes avec la combinaison "Paire"
     */
    @Test public void tiePaire() {
        String J1 = "1H 8D 8D KD 7D";
        String J2 = "1D 2S 2S TS 3S";
        String J3 = "1S 4H 3H 4H 5H";
        String J4=  "3H 4D 5S 6C 7S";
        Main.ligneDeCommandePourTest(new String[] {J1,J2,J3,J4});

        assertEquals("Tie entre J1 Paire de  HUIT,J2 Paire de  DEUX,J3 Paire de  QUATRE,",Main.sortie );
    }

    /**
     *Méthode testant l'égalité de plusieurs cartes avec la combinaison "PlusHauteValeur"
     */
    @Test public void tiePlusHauteValeur() {
        String J1 = "1H 8D 6D KD 7D";
        String J2 = "1D 2S 9S TS 3S";
        String J3 = "1S 4H 3H 8H 5H";

        Main.ligneDeCommandePourTest(new String[] {J1,J2,J3});

        assertEquals("Tie entre J1 Plus haute carte UN,J2 Plus haute carte UN,J3 Plus haute carte UN,",Main.sortie);
    }











}
