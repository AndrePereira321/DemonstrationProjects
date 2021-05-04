/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Andre Pereira
 */
public class File {

    private int[] tab;
    private int premier;
    private int dernier;
    private int nbMaxElement;

    public File(int max) {
        this.tab = new int[max + 1];

        this.premier = 0;
        this.dernier = max;
        this.nbMaxElement = max;
    }

    public void enfiler(int val) {
        this.dernier++;
        if (this.dernier == this.premier - 1
                || (this.dernier == this.nbMaxElement && this.premier == 0)) {
            throw new IllegalStateException("Liste pleine");
        }
        if (this.dernier > this.nbMaxElement) {
            this.dernier = 0;
        }
        this.tab[this.dernier] = val;
    }

    public boolean estVide() {
        return this.dernier == this.premier - 1
                || (this.dernier == this.nbMaxElement && this.premier == 0);
    }

    public int tete() {
        if (estVide()) {
            throw new IllegalStateException("Liste vide");
        }
        return this.tab[this.premier];
    }

    public int defiler() {
        if (estVide()) {
            throw new IllegalStateException("Liste vide");
        }
        int x;
        x = this.tab[this.premier];
        this.tab[this.premier] = 0;
        this.premier++;
        if (this.premier > this.nbMaxElement) {
            this.premier = 0;
        }
        return x;
    }

    public String toString() {
        return Arrays.toString(this.tab);
    }

    public static void main(String[] args) {
        File file = new File(5);
        file.enfiler(1);
        System.out.println(file);
        file.enfiler(2);
        System.out.println(file);
        file.enfiler(3);
        System.out.println(file);
        file.enfiler(4);
        System.out.println(file);
        file.enfiler(5);
        System.out.println(file);
        file.defiler();
        System.out.println(file);
        file.enfiler(6);
        System.out.println(file);
    }

}
