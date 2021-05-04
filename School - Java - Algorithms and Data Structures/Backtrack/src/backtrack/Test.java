/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class Test {
    
    public static void main(String[] args) {
        sequenceAbstract(0, 0);
    }

    private static final int[] a = {1, 7, 9};
    private static final int[] b = {3, 5, 11};
    private static final List<Integer> solutionEnCours = new ArrayList();

    private static void sequenceAbstract(int iA, int iB) {
        int j;
        while (iA < a.length) {
            j = iB;
            while (j < b.length && a[iA] > b[j]) {
                j++;
            }
            int dernierAjoute = solutionEnCours.isEmpty() ? Integer.MIN_VALUE : solutionEnCours.get(solutionEnCours.size() - 1);
            if (j != b.length && a[iA] > dernierAjoute) {
                solutionEnCours.add(a[iA]);
                solutionEnCours.add(b[j]);
                afficher(solutionEnCours);
                sequenceAbstract(iA + 1, j);
                solutionEnCours.remove(solutionEnCours.size() - 1);
                solutionEnCours.remove(solutionEnCours.size() - 1);
                sequenceAbstract(iA, j + 1);
            }
            iA++;
        }
    }

    private static void afficher(List<Integer> solutionEnCours) {
        System.out.println(Arrays.toString(solutionEnCours.toArray()));
    }

}
