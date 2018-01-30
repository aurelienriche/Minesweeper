import java.io.*;
import java.text.NumberFormat;
import java.io.BufferedReader;

public class GameDemineur {
    public static void main(String[] args) throws IOException {

        NumberFormat nbre = NumberFormat.getNumberInstance();
        nbre.setMinimumIntegerDigits(2);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Demineur f = new Demineur();

        int compteur = 0;
        int stop = 0;
        int colonne = 10; // Ici vous pourrez changer le nb de colonne à votre convenance
        int ligne = 10; // Ici vous pourrez changer le nb de ligne à votre convenance
        int randLigne = 0;
        int randColonne = 0;
        int nbMine = 2; // Ici vous pourrez changer le nb de bombe à votre convenance
        int ligneDevoilee = 0;
        int colonneDevoilee = 0;
        int grille[][] = new int[ligne][colonne];
        String grilleCachee[][] = new String[ligne][colonne];

        Demineur.PlacerBombes(nbMine, randLigne, randColonne, grille, ligne, colonne); //Place les bombes
        Demineur.NombreDeBombesProximite(ligne, colonne, grille); //Place les numéros qui indiquent le nombre de bombes à proximité
        Demineur.AfficherMatriceEtCoord(ligne, ligne, grilleCachee); //Créer la matrice et affiche les numéros autour de la matrice
        Demineur.Matrice(ligne, colonne, grilleCachee); //Affiche la matrice
        Demineur.InteractionUtilisateur(ligneDevoilee, colonneDevoilee, ligne, colonne, grille, grilleCachee, compteur, stop, nbMine); //Affiche l'interface pour l'utilisateur


    }
}
