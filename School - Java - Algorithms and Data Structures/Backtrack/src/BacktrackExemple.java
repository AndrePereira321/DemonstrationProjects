
import backtrack.Exercices;


public class BacktrackExemple {

    static int[][] M = new int[3][3];
    
    public static void main(String[] args) {
        carréMagique3fois3(0, 0);
    }

    public static void carréMagique3fois3(int i, int j) {
        boolean valide;

        if (i == 3) {
            /* Une fois qu'on a donné une valeur 
            à toutes les cases: on vérifie qu'il s'agit bien
            d'un carré magique, et on affiche*/
            afficherCarré();
        } else {
            /* Pour la case (i,j) du tableau: on essaye de trouver
            une solution avec  M[i][j] = 1, 2, 3, ..., 9*/
            for (int k = 1; k <= 9; k++) {
                valide = true;

                /* On vérifie que k n'a pas déjà été utilisé dans une case
               précédente du tableau*/
                for (int l = 0; l < i && valide; l++) {
                    for (int m = 0; m < M.length && valide; m++) {
                        if (M[l][m] == k) {
                            valide = false;
                        }
                    }
                }

                for (int m = 0; m < j; m++) {
                    if (M[i][m] == k) {
                        valide = false;
                    }
                }

                /* Si k n'a pas été utilisé (k est valide), on
               essaye avec k */
                if (valide) {
                    M[i][j] = k;

                    /* Si on est en en colonne 2, on passe à la ligne
                   suivante, sinon on passe à la colonne suivante
                   sur la mêel ligne. Lorsqu'on arrive à i=3,
                   on a terminé -> on vérifie qu'on a bien un carré
                   magique et on l'affiche le cas échécant (voir
                   ci-dessus) */
                    if (j == 2) {
                        carréMagique3fois3(i + 1, 0);
                    } else {
                        carréMagique3fois3(i, j + 1);
                    }
                }

            }
        }
    }

    public static void afficherCarré() {
        // 1ère ligne
        int somme = M[0][0] + M[0][1] + M[0][2];

        boolean valide
                = //2ième ligne
                (somme == (M[1][0] + M[1][1] + M[1][2]))
                && // 3 ième ligne
                (somme == (M[2][0] + M[2][1] + M[2][2]))
                && // 1 ère colonne
                (somme == (M[0][0] + M[1][0] + M[2][0])
                && // 2ième colonne
                (somme == (M[0][1] + M[1][1] + M[2][1]))
                && // 3ième colonne
                (somme == (M[0][2] + M[1][2] + M[2][2]))

                && // 1ère diagonale
                (somme == (M[0][0] + M[1][1] + M[2][2]))

                && // 2ième diagonale        
                (somme == (M[0][2] + M[1][1] + M[2][0])));

        /* On n'affiche que si c'est bien un carré magique !*/
        if (!valide) {
            return;
        }

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println(" ");
    }
}
