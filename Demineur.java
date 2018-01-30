
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

class Demineur {

    static void Matrice(int ligne, int colonne, String[][] grilleCachee) {

        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (i == ligne - 1 && colonne > 0) {
                    System.out.print(grilleCachee[i][j] + " ");
                } else if (i > 0 && j > 0 || j == (colonne - 1)) {
                    System.out.print(grilleCachee[i][j] + "  ");
                } else {
                    System.out.print(grilleCachee[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static void AfficherMatriceEtCoord(int ligne, int colonne, String[][] grilleCachee) {

        NumberFormat nbre = NumberFormat.getNumberInstance();
        nbre.setMinimumIntegerDigits(2);

        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                grilleCachee[i][j] = "•";
            }
        }

        for (int i = 0; i < ligne; i++) {
            grilleCachee[i][0] = nbre.format(i) + "";
            grilleCachee[i][colonne - 1] = nbre.format(i) + "";

            for (int j = 0; j < colonne; j++) {
                grilleCachee[0][j] = nbre.format(j) + "";
                grilleCachee[ligne - 1][j] = nbre.format(j) + "";
            }

        }


        grilleCachee[ligne - 1][0] = "  ";
        grilleCachee[0][0] = "  ";
        grilleCachee[0][colonne - 1] = "  ";
        grilleCachee[ligne - 1][colonne - 1] = "  ";
    }

    static void PlacerBombes(int nbMine, int randLigne, int randColonne, int[][] grille, int ligne, int colonne) {
        for (int i = 0; i < nbMine; i++) {
            randLigne = (int) (Math.random() * (ligne - 2) + 1);
            randColonne = (int) (Math.random() * (colonne - 2) + 1);

            if (grille[randLigne][randColonne] != 9) {
                grille[randLigne][randColonne] = 9;
            } else {
                i = i - 1;
            }
        }
    }

    static void NombreDeBombesProximite(int ligne, int colonne, int[][] grille) {
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                if (grille[i][j] == 9) {
                    for (int k = (i - 1); k < (i + 2); k++) {
                        for (int l = (j - 1); l < (j + 2); l++) {
                            if (grille[k][l] != 9) {
                                grille[k][l] = grille[k][l] + 1;
                            }
                        }
                    }
                }

            }
        }
    }

    static void InteractionUtilisateur(int ligneDevoilee, int colonneDevoilee, int ligne, int colonne, int[][] grille, String[][] grilleCachee, int compteur, int stop, int nbMine) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        do {

            do {
                System.out.println("\nEntrer numéro de ligne");
                ligneDevoilee = Integer.parseInt(in.readLine()) - 1;
            } while (ligneDevoilee < 0 || ligneDevoilee > (ligne - 3));

            do {
                System.out.println("\nEntrer numéro de colonne");
                colonneDevoilee = Integer.parseInt(in.readLine()) - 1;
            } while (colonneDevoilee < 0 || colonneDevoilee > (colonne - 3));

            if ("☼".equals(grilleCachee[(ligneDevoilee + 1)][(colonneDevoilee + 1)])) {
                compteur = compteur + 1;
            }

            switch (grille[(ligneDevoilee + 1)][(colonneDevoilee + 1)]) {
                case 9:
                    grilleCachee[(ligneDevoilee + 1)][(colonneDevoilee + 1)] = "☼";
                    break;
                case 0:
                    grilleCachee[(ligneDevoilee + 1)][(colonneDevoilee + 1)] = "0";
                    break;
                default:
                    grilleCachee[(ligneDevoilee + 1)][(colonneDevoilee + 1)] = grille[(ligneDevoilee + 1)][(colonneDevoilee + 1)] + "";
                    break;
            }

            Matrice(ligne, colonne, grilleCachee);

            System.out.println();
            if (grille[(ligneDevoilee + 1)][(colonneDevoilee + 1)] == 9) {
                stop = 1;
                System.out.println("\033[31;1m--------------------------------------------");
                System.out.println("\033[31;1m--------------------------------------------");
                System.out.println("\033[31;1mC'EST PERDU ! RETENTEZ VOTRE CHANCE !");
                System.out.println("\033[31;1m--------------------------------------------");
                System.out.println("\033[31;1m--------------------------------------------");
            }

            if (compteur == ((ligne - 2) * (colonne - 2) - nbMine) && stop != 1) {
                stop = 1;
                System.out.println("0");
                System.out.println("C'est");
                System.out.println("GAGNE");
            }

        } while (stop != 1);

        System.out.println();
        Matrice(ligne, colonne, grilleCachee);
        System.out.println("\nMerci d'avoir joue\n");

    }

}
