/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listechaine;

/**
 *
 * @author Andre Pereira
 */
public class ListeBD<E> {

    private ElementListeBD<E> premier;
    private ElementListeBD<E> dernier;

    public ListeBD() {
        premier = null;
        dernier = null;
    }

    public ElementListeBD<E> getPremier() {
        return premier;
    }

    public ElementListeBD<E> getDernier() {
        return dernier;
    }

    public boolean estVide() {
        return this.premier == null && this.dernier == null;
    }

    public void insererTete(E val) {
        ElementListeBD<E> nouveauElt = new ElementListeBD(val, this.premier);
        if (this.dernier == null && this.premier != null) {
            this.dernier = this.premier;
            this.dernier.setPrecedent(nouveauElt);
        }
        this.premier = nouveauElt;
        if (this.dernier != null) {
            this.premier.getSuivant().setPrecedent(this.premier);
        }
    }

    public void insererFin(E val) {
        ElementListeBD<E> nouveauElt;
        if (this.dernier == null) {
            if (this.premier == null) {
                this.premier = new ElementListeBD(val, null, null);
            } else {
                this.dernier = new ElementListeBD(val, this.premier, null);
                this.premier.setSuivant(this.dernier);
            }
        } else {
            nouveauElt = new ElementListeBD(val, this.dernier, null);
            this.dernier = nouveauElt;
            this.dernier.getPrecedent().setSuivant(this.dernier);
        }
    }

    public void insererApres(ElementListeBD<E> elt, E valeur) {
        ElementListeBD<E> tmp;
        tmp = elt.getSuivant();
        elt.setSuivant(new ElementListeBD(valeur, elt, tmp));
        tmp.setPrecedent(elt.getSuivant());
    }

    public void insererAvant(ElementListeBD<E> elt, E valeur) {
        ElementListeBD<E> tmp;
        tmp = elt.getPrecedent();
        elt.setPrecedent(new ElementListeBD(valeur, tmp, elt));
        tmp.setSuivant(elt.getPrecedent());
    }

    public ElementListeBD<E> suprimerTete() {
        if (this.estVide()) {
            throw new IllegalStateException("La liste est vide!");
        }
        ElementListeBD<E> tete = this.getPremier();
        this.premier = this.premier.getSuivant();
        if (this.premier != null) {
            this.premier.setPrecedent(null);
        }
        return tete;
    }

    public ElementListeBD<E> suprimerFin() {
        if (this.estVide()) {
            throw new IllegalStateException("La liste est vide");
        }
        ElementListeBD<E> fin = this.getDernier();
        if (fin != null) {
            this.dernier = this.dernier.getPrecedent();
            if (this.dernier != null) {
                this.dernier.setSuivant(null);
            }
        }
        return fin;
    }

    public static void main(String[] args) {
        ListeBD<Integer> list = new ListeBD();
        System.out.println(list);
        list.insererFin(2);
        System.out.println(list);
        System.out.println("Premier: " + list.getPremier() + "  Dernier: " + list.getDernier());
        list.insererFin(3);
        System.out.println(list);
        System.out.println("Premier: " + list.getPremier() + "  Dernier: " + list.getDernier());
        list.insererFin(4);
        System.out.println(list);
        System.out.println("Premier: " + list.getPremier() + "  Dernier: " + list.getDernier());
        list.insererFin(5);
        System.out.println(list);
        System.out.println("Premier: " + list.getPremier() + "  Dernier: " + list.getDernier());
        list.insererAvant(list.getDernier().getPrecedent().getPrecedent(), 5);
        System.out.println(list);
        System.out.println("Premier: " + list.getPremier() + "  Dernier: " + list.getDernier());
        list.suprimerFin();
        list.suprimerTete();
        System.out.println(list);
        System.out.println("Premier: " + list.getPremier() + "  Dernier: " + list.getDernier());
    }

    public String toString() {
        ElementListeBD<E> courant = this.getPremier();
        String result = "[";
        while (courant != null) {
            result += courant.getValeur();
            courant = courant.getSuivant();
            if (courant != null) {
                result += " - ";
            }
        }
        result += "]";
        return result;
    }
}
