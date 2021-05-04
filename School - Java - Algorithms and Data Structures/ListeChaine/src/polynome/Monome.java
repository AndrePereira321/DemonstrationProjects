/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynome;

/**
 *
 * @author Andre Pereira
 */
public class Monome {

    private final double coefficient;
    private final double exposant;

    public Monome(double coefficient, double exposant) {
        this.coefficient = coefficient;
        this.exposant = exposant;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public double getExposant() {
        return exposant;
    }

    public Monome add(Monome autreMonome) {
        if (autreMonome.exposant != this.exposant) {
            throw new IllegalArgumentException("Les exposants diferent!");
        }
        return new Monome(this.coefficient + autreMonome.getCoefficient(), this.exposant);
    }

    public Monome derive() {
        return new Monome(this.coefficient * this.exposant, this.exposant - 1);
    }

    @Override
    public String toString() {
        return ("( " + this.coefficient + "x^" + this.exposant + " )");
    }

    public static void main(String[] args) {
        Monome m1 = new Monome(4, 3);
        Monome m2 = new Monome(9, 3);
        System.out.println(m1);
        System.out.println(m1.derive());
        System.out.println(m1.add(m2));
    }

    public int compareTo(Monome b) {
        if (this.exposant > b.getExposant()) {
            return 1;
        } else if ( this.exposant < b.getExposant()) {
            return -1;
        } else {
            return 0;
        }
    }

}
