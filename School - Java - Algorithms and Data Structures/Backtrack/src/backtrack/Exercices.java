package backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class Exercices {

    private static final char[] chaine = new char[100];

    private static final Integer[][] magic = new Integer[3][3];
    private static final List<Integer[][]> l = new LinkedList();

    private static final int[][] cTete = new int[3][4];

    public static void main(String[] args) {
        System.out.println(chaine());
        carreMagique();
        System.out.println(l.size());
        System.out.println(n + " " + t + " " + u);
        casseTete();
    }

    ////////////////////////////////////////////////////////
    public static String chaine() {
        chaine(0);
        return String.valueOf(chaine);
    }

    private static boolean chaine(int index) {
        if (index < 100) {
            chaine[index] = 'A';
            if (chaineEstValide(index) && chaine(index + 1)) {
                return true;
            }

            chaine[index] = 'B';
            if (chaineEstValide(index) && chaine(index + 1)) {
                return true;
            }

            chaine[index] = 'C';
            if (chaineEstValide(index) && chaine(index + 1)) {
                return true;
            }
            return false;
        } else {
            return true;

        }
    }

    private static boolean chaineEstValide(int indice) {
        boolean continuer = true;
        int j;
        for (int i = 1; i <= (indice + 1) / 2 && continuer; i++) {
            for (j = indice - i; j > indice - 2 * i && (chaine[j] == chaine[j + i]); j--);
            continuer = (j != (indice - 2 * i));
        }
        return continuer;
    }

    public static List<Integer[][]> carreMagique() {
        carreMagique(0, 0);
        return l;

    }
    ////////////////////////////////////////////////////
    private static int n = 0;
    private static int t = 0;
    private static int u = 0;
    private static int v = 0;

    private static void carreMagique(int x, int y) {
        for (int i = 1; i <= 9; i++) {
            n++;
            for (int r = 0; r <= x; r++) {
                for (int c = 0; c < y; c++) {
                    if (magic[r][c] == i) {
                        t++;
                        return;
                    }
                }
            }
            magic[x][y] = i;
            if (y == 2 && x == 2) {
                if (carreEstValide()) {
                    Integer[][] n = copy(magic);
                    l.add(n);
                }
            } else if (y < 2) {
                carreMagique(x, y + 1);
            } else if (y == 2) {
                carreMagique(x + 1, 0);
            }
        }

    }

    private static Integer[][] copy(Integer[][] a) {
        Integer[][] b = new Integer[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[i][j] = a[i][j];
            }
        }
        return b;
    }

    private static boolean carreEstValide() {
        int sommeL = 0;
        int sommeC = 0;
        int prec = 0;
        for (int i = 0; i < magic.length; i++) {
            sommeL = 0;
            sommeC = 0;
            for (int j = 0; j < magic.length; j++) {
                sommeL += magic[i][j];
                sommeC += magic[j][i];
            }
            if (prec == 0) {
                prec = sommeL;
            }
            if (sommeL != sommeC || sommeC != prec) {
                return false;
            } else {
                prec = sommeL;
            }
        }
        int sommeD1 = 0;
        int sommeD2 = 0;
        for (int i = 0; i < magic.length; i++) {
            sommeD1 += magic[i][i];
            sommeD2 += magic[i][magic.length - i - 1];
        }
        return sommeD1 == sommeD2 && sommeD2 == prec;
    }

    ////////////////////////////////////////////////////////::
    public static void casseTete() {
        ctBacktrack(0, 0);

    }

    private static void ctBacktrack(int x, int y) {
        if (x == 2 && y == 3) {
            for (int[] l : cTete) {
                for (int n : l) {
                    System.out.print(n + " ");
                }
                System.out.println("");
            }
            System.out.println("");
        } else {
            if (ctIsInside(x, y)) {
                for (int i = 1; i <= 8; i++) {
                    if (ctIsValid(i, x, y)) {
                        cTete[x][y] = i;
                        if (y == 3) {
                            ctBacktrack(x + 1, 0);
                        } else {
                            ctBacktrack(x, y + 1);
                        }
                    }
                }
                cTete[x][y] = 0;
            } else {
                if (y == 3) {
                    ctBacktrack(x + 1, 0);
                } else {
                    ctBacktrack(x, y + 1);
                }
            }
        }
    }

    private static boolean ctIsInside(int x, int y) {
        if ((x == 0 && y == 0) || (x == 2 && y == 0) || (x == 0 && y == 3) || (x == 2 && y == 3)) {
            return false;
        }
        return x >= 0 && x < cTete.length && y >= 0 && y < cTete[0].length;
    }

    private static boolean ctIsValid(int nb, int x, int y) {
        boolean ok = true;
        boolean[] nbs = new boolean[10];
        for (int[] l : cTete) {
            for (int n : l) {
                nbs[n] = true;
            }
        }
        ok = ok && !nbs[nb];
        if (ctIsInside(x - 1, y)) {
            ok = ok && (Math.abs(cTete[x - 1][y] - nb) != 1 || cTete[x - 1][y] == 0);
        }
        if (ctIsInside(x - 1, y + 1)) {
            ok = ok && (Math.abs(cTete[x - 1][y + 1] - nb) != 1 || cTete[x - 1][y + 1] == 0);
        }
        if (ctIsInside(x, y + 1)) {
            ok = ok && (Math.abs(cTete[x][y + 1] - nb) != 1 || cTete[x][y + 1] == 0);
        }
        if (ctIsInside(x + 1, y + 1)) {
            ok = ok && (Math.abs(cTete[x + 1][y + 1] - nb) != 1 || cTete[x + 1][y + 1] == 0);
        }
        if (ctIsInside(x + 1, y)) {
            ok = ok && (Math.abs(cTete[x + 1][y] - nb) != 1 || cTete[x + 1][y] == 0);
        }
        if (ctIsInside(x + 1, y - 1)) {
            ok = ok && (Math.abs(cTete[x + 1][y - 1] - nb) != 1 || cTete[x + 1][y - 1] == 0);
        }
        if (ctIsInside(x, y - 1)) {
            ok = ok && (Math.abs(cTete[x][y - 1] - nb) != 1 || cTete[x][y - 1] == 0);
        }
        if (ctIsInside(x - 1, y - 1)) {
            ok = ok && (Math.abs(cTete[x - 1][y - 1] - nb) != 1 || cTete[x - 1][y - 1] == 0);
        }
        return ok;
    }
}
