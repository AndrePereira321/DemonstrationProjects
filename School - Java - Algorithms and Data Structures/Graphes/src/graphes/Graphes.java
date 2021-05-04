package graphes;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Andre Pereira
 */
public class Graphes {

    public static int[][] sum(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalStateException("Different sizes");
        }
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static int[][] produit(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalStateException("Different sizes");
        }
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int t = 0; t < a.length; t++) {
                    result[i][j] += a[i][t] * b[t][j];
                }

            }
        }
        return result;

    }

    public static boolean[][] add(boolean[][] A, boolean[][] B) {
        boolean[][] C = new boolean[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                C[i][j] = A[i][j] || B[i][j];
            }
        }
        return C;
    }

    public static boolean[][] produit(boolean[][] A, boolean[][] B) {

        /* La matrice C = A*B aura le même nombre de ligne que
        A (-> A.lenght) et le même nombre de colonnes
        que B (B[0].length) */
        boolean[][] C = new boolean[A.length][B[0].length];

        /* L'élément C[i][j] est obtenu en multipliant
            (= produit scalaire) la i-ième ligne de A
            avec la j-ème colonne de B*/
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) /* La boucle ci-dessous effectue le produit
                    entre la i-ième ligne de A et la
                    j-ème colonne de B, qui doivent donc avoir
                    la même longueur !*/ {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] = C[i][j] || (A[i][k] && B[k][j]);
                }
            }
        }

        return C;
    }

    public static int[][] puissance(int[][] a, int n) {
        int[][] result = new int[a.length][a[0].length];
        copy(result, a);
        for (int i = 1; i < n; i++) {
            produit(result, a);
        }
        return result;
    }

    private static void copy(int[][] dest, int[][] src) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[0].length; i++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    public static boolean[][] puissance(boolean[][] a, int n) {
        boolean[][] result = new boolean[a.length][a[0].length];
        copy(result, a);
        for (int i = 1; i < n; i++) {
            result = produit(result, a);
        }
        return result;
    }

    private static void copy(boolean[][] dest, boolean[][] src) {
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[0].length; j++) {
                dest[i][j] = src[i][j];
            }
        }
    }

    public static boolean[][] royWarshall(boolean[][] a) {
        boolean[][] result = new boolean[a.length][a[0].length];
        copy(result, a);
        for (int n = 0; n < a.length; n++) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    result[i][j] = result[i][j] || (result[i][n] && result[n][j]);
                }
            }
            print(result);
        }
        return result;
    }

    public static boolean[][] accessibilite(boolean[][] A) {
        // Le nombre de sommets
        int n = A.length;
        boolean[][] somme = A;
        boolean[][] puissance = A;

        for (int i = 1; i < n; i++) {
            // Je caclule A^i
            puissance = produit(puissance, A);
            // Je l'additionne aux puissances précédances.
            somme = add(somme, puissance);
        }

        return somme;

    }

    public static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("[");
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j]);
                if (j == a[0].length - 1) {
                    System.out.print("]");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void print(boolean[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("[");
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j]);
                if (j == a[0].length - 1) {
                    System.out.print("]");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static boolean[][] ngome(int n) {
        boolean[][] result = new boolean[n][n];
        for (int l = 0; l < n; l++) {
            for (int i = 1; i <= 2; i++) {
                if (l - i < 0) {
                    result[l][n + l - i] = true;
                } else {
                    result[l][l - i] = true;
                }
                if (l + i >= n) {
                    result[l][n - l + i - 2] = true;
                } else {
                    result[l][l + i] = true;
                }
            }
        }
        return result;
    }

    public static void supprimerArretes(int[] vals, boolean[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (vals[i] % 2 == 0 && vals[j] % 2 == 0) {
                    a[i][j] = false;
                }
            }
        }
    }

    public static LinkedList<Integer>[] toArrayList(boolean[][] a) {
        LinkedList<Integer>[] result = new LinkedList[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = new LinkedList();
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j]) {
                    result[i].add(j);
                }
            }
        }
        return result;
    }

    public static boolean[][] toAdjancent(LinkedList<Integer>[] l) {
        boolean[][] a = new boolean[l.length][l.length];
        for (int i = 0; i < l.length; i++) {
            for (Integer nb : l[i]) {
                a[i][nb] = true;
            }
        }
        return a;
    }

    public static void print(LinkedList<Integer>[] l) {
        for (int i = 0; i < l.length; i++) {
            System.out.println(l[i]);
        }
    }

    public static LinkedList<Integer>[] accessebilite(LinkedList<Integer>[] acc) {
        for (int i = 0; i < acc.length; i++) {
            acc[i].add(i);
            for (int j = 0; j < acc[i].size(); j++) {
                for (Integer noeud : acc[acc[i].get(j)]) {
                    if (!acc[i].contains(noeud)) {
                        acc[i].add(noeud);
                    }
                }
            }
        }
        return acc;
    }

    public static void parcoursContagion(LinkedList<Integer>[] l, int d) {
        Queue<Integer> file = new LinkedList();
        boolean[] etats = new boolean[l.length];
        file.add(d);
        while (!file.isEmpty()) {
            int n = file.remove();
            etats[n] = true;
            for (Integer noeud : l[n]) {
                if (!etats[noeud] && !file.contains(noeud)) {
                    file.add(noeud);
                }
            }
        }
    }

    public static boolean estRacine(boolean[][] a, int d) {
        LinkedList<Integer> acc = new LinkedList();
        boolean[] etats = new boolean[a.length];
        Queue<Integer> file = new LinkedList();
        file.add(d);
        while (!file.isEmpty()) {
            int n = file.remove();
            acc.add(n);
            etats[n] = true;
            for (int i = 0; i < a.length; i++) {
                if (a[n][i] && !etats[i] && !file.contains(i)) {
                    file.add(i);
                }
            }
        }
        return acc.size() == a.length;
    }

    public static Status[] initStatus(int size) {
        Status[] etats = new Status[size];
        for (int i = 0; i < size; i++) {
            etats[i] = new Status();
        }
        return etats;
    }

    public static LinkedList<Integer> noeudsAtDistance(LinkedList<Integer>[] a, int s, int d) {
        LinkedList<Integer> result = new LinkedList();
        Queue<Integer> file = new LinkedList();
        Status[] etats = initStatus(a.length);
        int courant;
        file.add(s);
        etats[s].dist = 0;
        while (!file.isEmpty()) {
            courant = file.remove();
            if (etats[courant].dist == d) {
                result.add(courant);
            } else if (etats[courant].dist > d) {
                break;
            }
            for (int i = 0; i < a[courant].size(); i++) {
                if (!etats[a[courant].get(i)].traite) {
                    if (etats[a[courant].get(i)].dist == - 1) {
                        etats[a[courant].get(i)].dist = etats[courant].dist + 1;
                    }
                    file.add(a[courant].get(i));
                }
            }
            etats[courant].traite = true;
        }
        return result;
    }

    public static void main(String[] args) {
        boolean[][] a = {
            {false, true, true, false, false, false},
            {false, true, false, true, false, false},
            {true, true, false, false, false, false},
            {false, false, false, false, false, false},
            {true, false, false, true, false, true},
            {false, false, false, false, false, false}
        };
        print(royWarshall(a));
        System.out.println(noeudsAtDistance(toArrayList(a), 2, 2));
    }
}

class Status {

    int dist;
    boolean traite;

    public Status() {
        dist = -1;
        traite = false;
    }

}
