/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association;

/**
 *
 * @author Andre Pereira
 */
public class Liste<K, V> {

    private Element<K, V> first;

    public Liste() {
        this.first = null;
    }

    public Element<K, V> getFirst() {
        return first;
    }

    public void setFirst(Element<K, V> first) {
        this.first = first;
    }

    public boolean estVide() {
        return this.first == null;
    }

    public void insererTete(K key, V value) {
        Element e = new Element(key, value, this.first);
        this.first = e;
    }

    public Element suprimerTete() {
        if (this.estVide()) {
            throw new IllegalStateException("Cette liste est vide");
        }
        Element first = this.first;
        this.first = this.first.getNext();
        return first;
    }

    public void insererApres(Element e, K key, V value) {
        Element tmp = new Element(key, value, e.getNext());
        e.setNext(tmp);
    }

    public void suprimerApres(Element e) {
        if (e == null || e.getNext() == null) {
            throw new IllegalStateException("Cv pas ce truc");
        }
        e.setNext(e.getNext().getNext());
    }

    public String toString() {
        Element courant = this.getFirst();
        String result = "[";
        while (courant != null) {
            result += courant.getValue();
            courant = courant.getNext();
            if (courant != null) {
                result += " - ";
            }
        }
        result += "]";
        return result;

    }

}
