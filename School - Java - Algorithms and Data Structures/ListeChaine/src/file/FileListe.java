/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import listechaine.ListeBD;

/**
 * 
 * @author Andre Pereira
 */
public class FileListe<E> {
    
    private final ListeBD<E> liste;
    
    public FileListe() {
        this.liste = new ListeBD();
    }
    
    public void enfiler(E val) {
        this.liste.insererTete(val);
    }
    
    public E tete() {
        if (this.liste.getDernier() != null) {
            return this.liste.getDernier().getValeur();
        } else {
            return this.liste.getPremier().getValeur();
        }
    }
    
    public E defiler() {
        if (this.liste.getDernier() != null) {
            return this.liste.suprimerFin().getValeur();
        } else {
            return this.liste.suprimerTete().getValeur();
        }
    }
    
    public boolean estVide() {
        return this.liste.estVide();
    }
}
