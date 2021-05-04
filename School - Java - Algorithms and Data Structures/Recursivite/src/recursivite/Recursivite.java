/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursivite;

import java.util.Arrays;
import java.util.HashMap;
import listechaine.ElementListe;
import listechaine.ListeChaine;

/**
 *
 * @author Andre Pereira
 */
public class Recursivite {

    static int i = 0;

    public static int fibonnacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonnacci(n - 1) + fibonnacci(n - 2);
        }
    }

    public static void hanoi(int n, char depart, char arrive, char intermediare) {
        if (n > 0) {
            hanoi(n - 1, depart, intermediare, arrive);
            System.out.println("Deplacer le disque de taille " + n + " du socle "
                    + depart + " vers le scocle " + arrive);
            hanoi(n - 1, intermediare, arrive, depart);
        }
    }

    private static int taille(ElementListe el) {
        if (el.getSuivant() == null) {
            return 1;
        } else {
            return 1 + taille(el.getSuivant());
        }
    }

    public static int taille(ListeChaine liste) {
        if (liste.estVide()) {
            return 0;
        } else {
            return taille(liste.getPremier());
        }
    }

    public static boolean estSymetrique(int[] tab) {
        if (tab.length < 2) {
            return true;
        }
        int[] nextTab = Arrays.copyOfRange(tab, 1, tab.length - 1);
        if (nextTab.length > 1) {
            return tab[0] == tab[tab.length - 1] && estSymetrique(nextTab);
        } else {
            return true;
        }
    }

    public static int divisionEntiere(int a, int b) {
        if (a - b < 0) {
            return 0;
        } else {
            return 1 + divisionEntiere(a - b, b);
        }
    }

    public static boolean rechercheDich(int[] tab, int val, int[] index) {
        if (tab[tab.length / 2] == val) {
            index[0] += tab.length / 2;
            return true;
        } else if (tab.length == 1) {
            return false;
        } else if (tab[tab.length / 2] > val) {
            int[] nextTab = Arrays.copyOfRange(tab, 0, tab.length / 2);
            return rechercheDich(nextTab, val, index);
        } else {
            int[] nextTab = Arrays.copyOfRange(tab, tab.length / 2, tab.length);
            index[0] += tab.length / 2;
            return rechercheDich(nextTab, val, index);
        }
    }

    public static int calculPuissance(int base, int exposent) {
        if (exposent == 1) {
            return base;
        } else if (exposent % 2 == 0) {
            return calculPuissance(base, exposent / 2) * calculPuissance(base, exposent / 2);
        } else {
            --exposent;
            return calculPuissance(base, exposent / 2) * calculPuissance(base, exposent / 2) * base;
        }
    }

    public static int coefficientBinominaux(int n, int p) {
        if (p > n) {
            return 0;
        }
        if (p == 0 || p == n) {
            return 1;
        } else {
            return coefficientBinominaux(n - 1, p - 1) + coefficientBinominaux(n - 1, p);
        }
    }

    public static int chiffreRomain(String chiffreRom) {
        if (chiffreRom.length() == 0) {
            return 0;
        } else if (chiffreRom.length() == 1) {
            return getRomainValue(chiffreRom.charAt(0));
        } else {
            int first = getRomainValue(chiffreRom.charAt(0));
            int second = getRomainValue(chiffreRom.charAt(1));
            if (first >= second) {
                return first + chiffreRomain(chiffreRom.substring(1));
            } else {
                return second - first + chiffreRomain(chiffreRom.substring(2));
            }
        }
    }

    /**
     * Verifie si 2 points snt sur la meme forme.
     *
     * @param a Un point
     * @param b Deux Points
     * @param quadr Un chars representant formes
     * @param threats Pour savoir si on a traite
     * @return
     */
    public static boolean verifierTache(Pos a, Pos b, char[][] quadr, boolean[][] threats) {
        if (a.x >= 0 && a.y >= 0 && threats[a.x][a.y] == false) {
            threats[a.x][a.y] = true;
            if (quadr[a.x][a.y] != quadr[b.x][b.y]) {
                return false;
            } else if (a.x == b.x && a.y == b.y && quadr[a.x][a.y] == quadr[b.x][b.y]) {
                return true;
            } else {
                return verifierTache(new Pos(a.x, a.y - 1), b, quadr, threats)
                        || verifierTache(new Pos(a.x + 1, a.y), b, quadr, threats)
                        || verifierTache(new Pos(a.x, a.y + 1), b, quadr, threats)
                        || verifierTache(new Pos(a.x - 1, a.y), b, quadr, threats);
            }
        }
        return false;
    }

    public static int aireForme(Pos a, char[][] quadr, boolean[][] threats) {
        return appartient(a, a, quadr, threats);
    }

    private static int appartient(Pos a, Pos b, char[][] quadr, boolean[][] threats) {
        if (b.x >= 0 && b.y >= 0) {
            if (threats[b.x][b.y] == false && quadr[a.x][a.y] == quadr[b.x][b.y]) {

                threats[b.x][b.y] = true;
                return 1 + appartient(a, new Pos(b.x, b.y + 1), quadr, threats)
                        + appartient(a, new Pos(b.x, b.y - 1), quadr, threats)
                        + appartient(a, new Pos(b.x - 1, b.y), quadr, threats)
                        + appartient(a, new Pos(b.x + 1, b.y), quadr, threats);
            }
        }
        return 0;
    }

    public static int perimetreForme(Pos a, char[][] quadr, boolean[][] threats) {
        return clcPerimetre(a, a, quadr, threats);
    }

    private static int clcPerimetre(Pos a, Pos b, char[][] quadr, boolean[][] threats) {
        if (b.x >= 0 && b.y >= 0 && b.x < quadr.length && b.y < quadr.length) {
            if (threats[b.x][b.y] == false) {
                if (quadr[a.x][a.y] != quadr[b.x][b.y]) {
                    return 1;
                } else {
                    threats[b.x][b.y] = true;
                    return clcPerimetre(a, new Pos(b.x, b.y + 1), quadr, threats)
                            + clcPerimetre(a, new Pos(b.x, b.y - 1), quadr, threats)
                            + clcPerimetre(a, new Pos(b.x + 1, b.y), quadr, threats)
                            + clcPerimetre(a, new Pos(b.x - 1, b.y), quadr, threats);
                }
            } else {
                return 0;
            }
        }
        return 1;
    }

    public static void plusGrandeTache(char[][] quadr, boolean[][] threats) {

    }

    public static void changerCouleurTache(char[][] quadr, Pos a, char nColor, boolean[][] threats) {
        changeColors(quadr, quadr[a.x][a.y], a, nColor, threats);
    }

    private static void changeColors(char[][] quadr, char shape, Pos end, char nColor, boolean[][] threats) {
        if (end.x >= 0 && end.y >= 0 && end.x < quadr[0].length && end.y < quadr.length
                && shape == quadr[end.x][end.y] && !threats[end.x][end.y]) {
            quadr[end.x][end.y] = nColor;
            threats[end.x][end.y] = true;
            changeColors(quadr, shape, new Pos(end.x - 1, end.y), nColor, threats);
            changeColors(quadr, shape, new Pos(end.x + 1, end.y), nColor, threats);
            changeColors(quadr, shape, new Pos(end.x, end.y + 1), nColor, threats);
            changeColors(quadr, shape, new Pos(end.x, end.y - 1), nColor, threats);
        }
    }

    public static int nbCatalan(int n) {
        int res = 0;
        if (n == 0) {
            return 1;
        } else {
            for (int i = 0; i < n; ++i) {
                res += nbCatalan(i) * nbCatalan(n - i - 1);
            }
        }
        return res;
    }

    private static int getRomainValue(char chiffreRomain) {
        switch (chiffreRomain) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return -1;
        }
    }

    private static HashMap<Character, Integer> compterTaches(char[][] shapes) {
        boolean[][] visited = new boolean[shapes.length][shapes[0].length];
        HashMap<Character, Integer> compteur = new HashMap();
        for (int i = 0; i < shapes.length; i++) {
            for (int j = 0; j < shapes[i].length; j++) {
                if (!visited[i][j]) {
                    if (compteur.get(shapes[i][j]) == null) {
                        compteur.put(shapes[i][j], 1);
                    } else {
                        compteur.put(shapes[i][j], compteur.get(shapes[i][j]) + 1);
                    }
                    cloturerTache(shapes, visited, i, j, shapes[i][j]);
                }
            }
        }
        for (char k : compteur.keySet()) {
            System.out.println(k + ": " + compteur.get(k));
        }
        return compteur;
    }

    private static void cloturerTache(char[][] shapes, boolean[][] visited, int x, int y, char current) {
        if (x >= 0 && x < shapes.length && y >= 0 && y < shapes[0].length) {
            if (!visited[x][y] && shapes[x][y] == current) {
                visited[x][y] = true;
                cloturerTache(shapes, visited, x, y + 1, current);
                cloturerTache(shapes, visited, x, y - 1, current);
                cloturerTache(shapes, visited, x + 1, y, current);
                cloturerTache(shapes, visited, x - 1, y, current);
            }
        }
    }

//////////////////////// EXERCICE 11 //////////////////////////
    public static void main(String[] args
    ) {
        ListeChaine<Integer> liste
                = new ListeChaine();

        for (int i
                = 0; i
                < 1000; i++) {
            liste
                    .insererTete(i
                    );

        }
        System.out
                .println(taille(liste
                ));

        int[] tab
                = {2, 1, 1, 2};
        System.out
                .println(estSymetrique(tab
                ));
        System.out
                .println(divisionEntiere(5, 2));

        int[] tab2
                = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] index
                = new int[1];
        System.out
                .println(rechercheDich(tab2,
                        10, index
                ) + " - " + index[0]);
        System.out
                .println(calculPuissance(8, 3));
        System.out
                .println(coefficientBinominaux(10, 5));
        System.out
                .println(chiffreRomain("MCMXCIX"));
        System.out
                .println(nbCatalan(5));
        System.out
                .println("=====================================");

        char[][] shapes
                = {
                    {'a', 'a', 'b', 'a', 'b', 'b', 'a', 'a', 'c'},
                    {'b', 'b', 'b', 'a', 'b', 'b', 'a', 'a', 'a'},
                    {'b', 'b', 'b', 'a', 'd', 'b', 'a', 'a', 'a'},
                    {'b', 'c', 'a', 'a', 'c', 'd', 'a', 'd', 'c'},
                    {'d', 'c', 'c', 'c', 'a', 'a', 'a', 'd', 'a'},
                    {'d', 'd', 'd', 'c', 'a', 'a', 'd', 'd', 'a'},
                    {'d', 'd', 'd', 'c', 'a', 'd', 'd', 'd', 'a'},
                    {'d', 'd', 'd', 'c', 'a', 'd', 'd', 'd', 'c'},
                    {'a', 'd', 'd', 'c', 'a', 'd', 'a', 'c', 'c'}
                };
        printArr(shapes);
        compterTaches(shapes);

//        boolean[][] threats
//                = new boolean[shapes.length][shapes[0].length];
//        System.out
//                .println("VerifierTache: " + verifierTache(new Pos(0, 2), new Pos(2, 0), shapes,
//                        threats
//                ));
//
//        boolean[][] threats2
//                = new boolean[shapes.length][shapes[0].length];
//        System.out
//                .println("Aire: " + aireForme(new Pos(0, 2), shapes,
//                        threats2
//                ));
//        threats2
//                = new boolean[shapes.length][shapes[0].length];
//        System.out
//                .println("Perim: " + perimetreForme(new Pos(8, 8), shapes,
//                        threats2
//                ));
//        threats
//                = new boolean[shapes.length][shapes[0].length];
//        System.out
//                .println("");
//        printArr(shapes
//        );
//        changerCouleurTache(shapes,
//                new Pos(1, 1), 'h', threats
//        );
//        System.out
//                .println("");
//        System.out
//                .println("");
//        printArr(shapes
//        );
    }

    private static void printArr(char[][] a
    ) {
        for (char[] l
                : a) {
            System.out
                    .println(Arrays
                            .toString(l
                            ));

        }
    }
}

class Pos {

    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    public boolean equals(Object obj) {
        Pos oth = (Pos) obj;
        return oth.x == this.x && oth.y == this.y;
    }

}
