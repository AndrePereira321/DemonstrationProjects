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
public class Ensemble<E> {

    private ListeChaine<E> valeurs;

    public Ensemble() {
        this.valeurs = new ListeChaine();
    }

    public void ajouter(E val) {
        if (!this.contient(val)) {
            this.valeurs.insererTete(val);
        }
    }

    public Ensemble<E> union(Ensemble<E> autreEnsemble) {
        Ensemble<E> resultat = new Ensemble();
        ElementListe<E> i = this.getValeurs().getPremier();
        while (i != null) {
            resultat.ajouter(i.getValeur());
            i = i.getSuivant();
        }
        i = autreEnsemble.getValeurs().getPremier();
        while (i != null) {
            resultat.ajouter(i.getValeur());
            i = i.getSuivant();
        }
        return resultat;

    }

    public Ensemble<E> intersection(Ensemble<E> autreEnsemble) {
        Ensemble<E> resultat = new Ensemble();
        ElementListe<E> i = this.getValeurs().getPremier();
        while (i != null) {
            if (autreEnsemble.contient(i.getValeur())) {
                resultat.ajouter(i.getValeur());
            }
            i = i.getSuivant();
        }
        return resultat;
    }

    public Ensemble<E> difference(Ensemble<E> autreEnsemble) {
        Ensemble<E> resultat = new Ensemble();
        ElementListe<E> i = this.getValeurs().getPremier();
        while (i != null) {
            if (!autreEnsemble.contient(i.getValeur())) {
                resultat.ajouter(i.getValeur());
            }
            i = i.getSuivant();
        }
        return resultat;
    }

    public ListeChaine<E> getValeurs() {
        return this.valeurs;
    }

    public boolean contient(E val) {
        ElementListe<E> courant = this.valeurs.getPremier();
        while (courant != null) {
            if (courant.getValeur().equals(val)) {
                return true;
            }
            courant = courant.getSuivant();
        }
        return false;
    }

    public String toString() {
        return this.valeurs.toString();
    }

    public static void main(String[] args) {
        Ensemble<Integer> a = new Ensemble();
        a.ajouter(7);
        a.ajouter(9);
        a.ajouter(14);
        a.ajouter(9);
        a.ajouter(30);
        a.ajouter(20);
        Ensemble<Integer> b = new Ensemble();
        b.ajouter(14);
        b.ajouter(-3);
        b.ajouter(30);
        b.ajouter(1);
        b.ajouter(10);
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        Ensemble<Integer> union = a.union(b);
        System.out.println("Union: " + union);
        Ensemble<Integer> intersection = a.intersection(b);
        System.out.println("Intersection: " + intersection);
        Ensemble<Integer> difference = a.difference(b);
        System.out.println("Difference: " + difference);
    }
}
