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
public class ElementListe<E> implements Comparable<ElementListe<E>>{

    private E valeur;
    private ElementListe<E> suivant;
    
    public ElementListe(E valeur, ElementListe<E> elmnt) {
       this.valeur = valeur;
       this.suivant = elmnt;
    }
    
    public ElementListe(E valeur) {
        this.valeur = valeur;
        this.suivant = null;
    }

    public E getValeur() {
        return valeur;
    }

    public void setValeur(E valeur) {
        this.valeur = valeur;
    }

    public ElementListe<E> getSuivant() {
        return suivant;
    }

    public void setSuivant(ElementListe<E> suivant) {
        this.suivant = suivant;
    }
    
    public boolean equals(ElementListe<E> autreEl) {
        return this.valeur.equals(autreEl.getValeur());
    }

    @Override
    public int compareTo(ElementListe<E> autreElement) {
        int val = (int)this.valeur;
        int autreVal = (int)autreElement.getValeur();
        if(val > autreVal) {
            return 1;
        } else if (val < autreVal) {
            return -1;
        } else {
            return 0;
        }
    }
}
