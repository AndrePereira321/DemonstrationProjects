/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class ExempleCheminMin {
    
    public static void main(String[] args) {
        int[][] vals = {
            {1, 3, 2, 2, 4, 7, 6},
            {8, 9, 4, 8, 3, 3, 3},
            {6, 1, 2, 6, 10, 8, 5},
            {8, 2, 7, 5, 7, 9, 3},
            {5, 2, 1, 2, 6, 5, 2},
            {4, 3, 4, 3, 2, 3, 2},
            {2, 2, 5, 6, 7, 2, 1}
        };
        List<Case> p = cheminMin(vals);
        for(Case c : p) {
            System.out.println(vals[c.row][c.col] + " - ");
        }
    }

    public static List<Case> cheminMin(int[][] vals) {
        List<Case> parcoursOptimal = new LinkedList();
        List<Case> parcoursCourant = new LinkedList();
        int[] somme = new int[1];
        int[] minSomme = new int[1];
        init(vals, parcoursOptimal, minSomme);
        parcoursCourant.add(new Case(0, 0));
        somme[0] = vals[0][0];
        backtracking(vals, parcoursCourant, parcoursOptimal, somme, minSomme);
        return parcoursOptimal;
    }

    private static void init(int[][] vals, List<Case> parcoursOptimal, int[] minSomme) {
        minSomme[0] = 0;
        for (int i = 0; i < vals[0].length; i++) {
            Case caseC = new Case(0, i);
            parcoursOptimal.add(caseC);
            minSomme[0] += vals[0][i];
        }
        for (int i = 1; i < vals.length; i++) {
            Case caseC = new Case(i, vals[i].length - 1);
            parcoursOptimal.add(caseC);
            minSomme[0] += vals[i][vals[i].length - 1];
        }

    }

    private static void backtracking(int[][] vals, List<Case> parcoursCourant,
            List<Case> parcoursOptimal, int[] somme, int[] minSomme) {
        Case derniereCase = parcoursCourant.get(parcoursCourant.size() - 1);
        Case caseC = new Case();
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    caseC.col = derniereCase.col + 1;
                    caseC.row = derniereCase.row;
                    break;
                case 1:
                    caseC.col = derniereCase.col;
                    caseC.row = derniereCase.row + 1;
                    break;
                case 2:
                    caseC.col = derniereCase.col - 1;
                    caseC.row = derniereCase.row;
                    break;
                case 3:
                    caseC.col = derniereCase.col;
                    caseC.row = derniereCase.row - 1;
                    break;
            }
            if (estAcceptable(vals, parcoursCourant, somme, minSomme, caseC)) {
                parcoursCourant.add(caseC);
                somme[0] += vals[caseC.row][caseC.col];
                if (caseC.row == vals.length - 1 && caseC.col == vals[vals.length - 1].length) {
                    if (somme[0] < minSomme[0]) {
                        parcoursOptimal.clear();
                        parcoursOptimal.addAll(parcoursCourant);
                        minSomme[0] = somme[0];
                    }
                } else {
                    backtracking(vals, parcoursCourant, parcoursOptimal, somme, minSomme);
                }
                parcoursCourant.remove(parcoursCourant.size() - 1);
                somme[0] -= vals[caseC.row][caseC.col];
            }
        }
    }

    private static boolean estAcceptable(int[][] vals, List<Case> parcoursCourant,
            int[] somme, int[] minSomme, Case caseC) {
        boolean ok = caseC.col >= 0 && caseC.col <= vals[0].length - 1 && caseC.row >= 0 && caseC.row <= vals.length - 1;
        int i = 0;
        while (ok && i < parcoursCourant.size()) {
            ok = caseC.col != parcoursCourant.get(i).col || caseC.row != parcoursCourant.get(i).row;
            ++i;
        }
        return ok && somme[0] + vals[caseC.row][caseC.col] < minSomme[0];
    }
}

class Case {

    int row;
    int col;

    public Case() {
        super();
    }

    public Case(int r, int c) {
        row = r;
        col = c;
    }
}
