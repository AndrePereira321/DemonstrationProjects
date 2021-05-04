/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listechaine;

/**
 *
 * @author Andre Pereira
 * @param <E>General object type.
 */
public class ElementListeBD<E> {
    
    private E valeur;
    private ElementListeBD<E> suivant;
    private ElementListeBD<E> precedent;
    
    public ElementListeBD() {
    valeur = null;
    suivant = null;
    precedent = null;
    }
    
    public ElementListeBD(E val) {
        this.valeur = val;
        this.suivant = null;
        this.precedent = null;
    }
    
    public ElementListeBD(E val, ElementListeBD<E> suivant) {
        this.valeur = val;
        this.suivant = suivant;
        this.precedent = null;
    }

    public ElementListeBD(E val, ElementListeBD<E> precedent, ElementListeBD<E> suivant) {
        this.valeur = val;
        this.precedent = precedent;
        this.suivant = suivant;
    }

    public E getValeur() {
        return valeur;
    }

    public void setValeur(E valeur) {
        this.valeur = valeur;
    }

    public ElementListeBD<E> getSuivant() {
        return suivant;
    }

    public void setSuivant(ElementListeBD<E> suivant) {
        this.suivant = suivant;
    }

    public ElementListeBD<E> getPrecedent() {
        return precedent;
    }

    public void setPrecedent(ElementListeBD<E> precedent) {
        this.precedent = precedent;
    }
    
    public String toString() {
        return this.getValeur().toString();
    }
    
    public boolean equals(ElementListeBD<E> autreElt) {
        return this.getValeur().equals(autreElt.getValeur());
    }
    
    
}
