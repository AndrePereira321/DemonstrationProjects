/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class Revisions {

    public static void main(String[] args) {
        int[] nbs = {10, 20, 50, 50, 5, 20, 2, 10, 10, 2};
        //  afficherBonComptes(nbs, 120);
        System.out.println(chevalChess(0, 1));
        System.out.println("");
        //trouverMouttons();
        //dérangements(5);
        // System.out.println("=========== SODUKU ==============");
        // System.out.println(soduku());
//        for (int[] l : sodoku) {
//            for (int n : l) {
//                System.out.print(n + " ");
//            }
//            System.out.println("");
//        }
    }

    public static void afficherBonComptes(int[] nbs, int total) {
        bonCompteBacktrack(nbs, new boolean[nbs.length], total, 0, 0);
    }

    private static void bonCompteBacktrack(int[] nbs, boolean[] utilise, int total, int current, int index) {
        if (current == total) {
            for (int i = 0; i < nbs.length; i++) {
                if (utilise[i]) {
                    System.out.print(nbs[i] + " ");
                    if (i != nbs.length - 1) {
                        System.out.print("+ ");
                    }
                }
            }
            System.out.print("= " + current);
            System.out.println("");
        } else {
            for (int i = index; i < nbs.length; i++) {
                if (!utilise[i]) {
                    if (current + nbs[i] <= total) {
                        utilise[i] = true;
                        bonCompteBacktrack(nbs, utilise, total, current + nbs[i], i + 1);
                        utilise[i] = false;
                    }
                }
            }
        }
    }

    private static int[] dX = {-2, -2, -1, 1, 2, 2, 1, -1};
    private static int[] dY = {-1, 1, 2, 2, 1, -1, -2, -2};
    private static int[][] passages = new int[8][8];

    public static boolean chevalChess(int x, int y) {
        boolean ok = chevalChessBacktrack(x, y, 1);
        System.out.println(ok);
        return ok;
    }

    private static boolean chevalChessBacktrack(int x, int y, int cmp) {
        cpp++;
        if (cmp == 64) {
            System.out.println(cpp);
            return true;
        } else {
            if (chesssEstAcceptable(x, y)) {
                boolean aResolu = false;
                passages[x][y]++;
                for (int i = 0; i < dX.length && !aResolu; i++) {
                    int nX = x + dX[i];
                    int nY = y + dY[i];
                    aResolu = chevalChessBacktrack(nX, nY, cmp + 1);
                }
                if (!aResolu) {
                    passages[x][y] = 0;
                }
                return aResolu;
            } else {
                return false;
            }
        }
    }

    static long cpp = 0;

    private static boolean chesssEstAcceptable(int x, int y) {
        return x >= 0 && x < passages.length && y >= 0 && y < passages[0].length && passages[x][y] == 0;
    }

    private static boolean[][] champ = {
        {false, false, true, false, false, false, false, false, true},
        {false, true, false, false, false, false, true, false, false},
        {false, false, false, true, false, false, false, false, false},
        {false, false, false, false, true, false, true, false, false},
        {true, false, false, false, true, false, false, false, false},
        {false, false, true, false, false, true, false, false, false},
        {false, false, false, false, false, false, true, true, false},
        {false, false, false, false, false, false, true, false, false},
        {false, false, false, true, false, false, false, false, true}
    };

    private static void trouverMouttons() {
        List<String> directions = new ArrayList();
        int nbMouttons;
        if ((nbMouttons = trouverMouttons(0, 0, 0, directions)) != -1) {
            System.out.println("Vous avez trouve: " + nbMouttons + "mouttons!");
            directions.forEach(System.out::println);

        } else {
            System.out.println("Auccun moutton trouvée!");
        }
        System.out.println(directions.size());

    }

    private static int trouverMouttons(int x, int y, int count, List<String> directions) {
        if (estAcceptable(x, y)) {
            if (champ[x][y]) {
                count++;
            }
            if (x == 8 && y == 8) {
                return count;
            } else {
                List<String> d1 = new ArrayList();
                List<String> d2 = new ArrayList();
                int count2 = trouverMouttons(x, y + 1, count, d2);
                int count1 = trouverMouttons(x + 1, y, count, d1);
                if (count1 > count2) {
                    directions.addAll(d1);
                    directions.add("Bas");
                } else {
                    directions.addAll(d2);
                    directions.add("Droite");
                }
                return (count1 > count2) ? count1 : count2;
            }
        } else {
            return -1;
        }
    }

    private static boolean estAcceptable(int x, int y) {
        return x >= 0 && y >= 0 && x < champ.length && y < champ[0].length;
    }

    private static int[] deran;
    private static boolean[] isUsed;

    public static void dérangements(int n) {
        deran = new int[n];
        isUsed = new boolean[n];
        derangementBacktrack(0);
    }

    private static void derangementBacktrack(int index) {
        if (index < deran.length) {
            for (int i = 0; i < deran.length; i++) {
                if (i != index && !isUsed[i]) {
                    deran[index] = i;
                    isUsed[i] = true;
                    derangementBacktrack(index + 1);
                    isUsed[i] = false;
                }
            }
        } else {
            System.out.print("[");
            for (int i = 0; i < index; i++) {
                if (i < index - 1) {
                    System.out.print(deran[i] + " - ");
                } else {
                    System.out.print(deran[i] + "]");
                }
            }
            System.out.println("");
        }
    }

    private static final int[][] sodoku = new int[9][9];

    public static boolean soduku() {
        return soduku(0, 0);
    }

    private static boolean soduku(int x, int y) {
        boolean estValide = false;
        for (int i = 1; i <= 9; i++) {
            if (sodokuEstAcceptable(x, y, i)) {
                sodoku[x][y] = i;
                if (x == sodoku.length - 1 && y == sodoku[x].length - 1) {
                    return true;
                } else if (y < sodoku.length - 1) {
                    estValide = soduku(x, y + 1);
                } else if (y == sodoku.length - 1) {
                    estValide = soduku(x + 1, 0);
                }
            }
        }
        if (!estValide) {
            sodoku[x][y] = 0;
        }
        return estValide;
    }

    private static boolean sodokuEstAcceptable(int x, int y, int nb) {
        boolean estValide = true;
        int sX = x - (x % 3);
        int sY = y - (y % 3);
        for (int i = 0; i < sodoku.length && estValide; i++) {
            estValide = sodoku[i][y] != nb && sodoku[x][i] != nb;
        }
        for (int i = 0; i <= 2 && estValide; i++) {
            for (int j = 0; j <= 2 && estValide; j++) {
                estValide = sodoku[sX + i][sY + j] != nb;
            }
        }
        return estValide;
    }

}

class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        Position pos = (Position) obj;
        return x == pos.x && y == pos.y;
    }

    public String toString() {
        return "(" + x + " , " + y + " )";
    }
}
