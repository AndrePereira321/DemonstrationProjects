/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pile;

import java.util.Arrays;
import listechaine.ListeChaine;

/**
 *
 * @author Andre Pereira
 */
public class Pile<E> {

    private ListeChaine<E> data;

    public Pile() {
        this.data = new ListeChaine();
    }

    public void empiler(E val) {
        this.data.insererTete(val);
    }

    public E sommet() {
        return this.data.getPremier().getValeur();
    }
    
    public ListeChaine getData() {
        return this.data;
    }

    public E depiler() {
        E val = this.data.getPremier().getValeur();
        this.data.supprimerTete();
        return val;
    }

    public boolean estVide() {
        return this.data.estVide();
    }

    public static int notationPolInverse(String operation) {
        String[] data = operation.split(" ");
        Pile<Integer> pile = new Pile();
        int op1, op2;
        for (String elm : data) {
            if (estNombre(elm)) {
                pile.empiler(Integer.parseInt(elm));
            } else {
                op1 = pile.depiler();
                op2 = pile.depiler();
                switch (elm) {
                    case "+":
                        pile.empiler(op2 + op1);
                        break;
                    case "-":
                        pile.empiler(op2 - op1);
                        break;
                    case "*":
                        pile.empiler(op2 * op1);
                        break;
                    case "/":
                        pile.empiler(op2 / op1);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown Token");
                }
            }
        }
        return pile.depiler();
    }
    

    private static boolean estNombre(char c) {
        return Character.isDigit(c);
    }

    private static boolean estNombre(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static int nombre(String s) {
        return Integer.parseInt(s);
    }
}
