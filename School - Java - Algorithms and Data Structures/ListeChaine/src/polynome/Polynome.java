/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynome;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class Polynome {

    private final List<Monome> liste;

    public Polynome(List<Monome> liste) {
        this.liste = liste;
        this.liste.sort((Monome a, Monome b) -> a.compareTo(b));
    }

    public List<Monome> getListe() {
        return liste;
    }

    public Polynome add(Polynome b) {
        int i = 0;
        int j = 0;
        List<Monome> result = new ArrayList();

        while (i < this.liste.size() || j < b.getListe().size()) {
            if (i == this.liste.size()) {
                while (j < b.getListe().size()) {
                    result.add(b.getListe().get(j));
                    j++;
                }
            } else if (j == b.getListe().size()) {
                while (i < this.liste.size()) {
                    result.add(this.liste.get(i));
                    i++;
                }
            } else if (this.liste.get(i).compareTo(b.getListe().get(j)) == -1) {
                while (i < this.liste.size() && this.liste.get(i).compareTo(b.getListe().get(j)) == -1) {
                    result.add(this.liste.get(i));
                    i++;
                }
            } else if (this.liste.get(i).compareTo(b.getListe().get(j)) == 1) {
                while (j < b.getListe().size() && this.liste.get(i).compareTo(b.getListe().get(j)) == 1) {
                    result.add(b.getListe().get(j));
                    j++;
                }
            } else {
                result.add(this.liste.get(i).add(b.getListe().get(j)));
                i++;
                j++;
            }
        }

        return new Polynome(result);
    }

    @Override
    public String toString() {
        String toString = "";

        for (Monome monome : this.liste) {
            toString += monome.toString() + " + ";

        }

        return toString;
    }

    public static void main(String[] args
    ) {
        List<Monome> lA = new ArrayList();
        List<Monome> lB = new ArrayList();

        lA.add(new Monome(2, 3));
        lA.add(new Monome(4, 2));
        lA.add(new Monome(1, 5));
        lA.add(new Monome(2, 4));

        lB.add(new Monome(4, 5));
        lB.add(new Monome(6, 6));
        lB.add(new Monome(7, 7));

        Polynome A = new Polynome(lA);
        Polynome B = new Polynome(lB);
        Polynome Y = A.add(B);
        System.out.println(A);
        System.out.println(B);
        System.out.println(Y);
    }
}
