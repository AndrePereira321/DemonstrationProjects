/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbres;


import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Andre Pereira
 * @param <E>
 */
public class ListeChaine<E> /*implements Iterable*/ {

    private ElementListe<E> premier;

    public ElementListe<E> getPremier() {
        return premier;
    }

    public void setPremier(ElementListe<E> premier) {
        this.premier = premier;
    }

    public ListeChaine() {
        this.premier = null;
    }

    public boolean estVide() {
        return this.premier == null;
    }

    public void insererTete(E val) {
        ElementListe<E> nouveauEl = new ElementListe<>(val, this.premier);
        this.premier = nouveauEl;
    }

    public void insererFin(E val) {
        ElementListe<E> i = this.premier;
        if (this.premier == null) {
            this.premier = new ElementListe<E>(val, null);
        }
        while (i != null) {
            if (i.getSuivant() == null) {
                i.setSuivant(new ElementListe<E>(val, null));
                break;
            }
            i = i.getSuivant();
        }
    }

    public void supprimerTete() {
        if (this.premier == null) {
            throw new NullPointerException("La Liste est Vide!");
        }
        this.premier = this.premier.getSuivant();
    }

    public void insererApres(ElementListe<E> el, E val) {
        ElementListe<E> eltAInserer;
        eltAInserer = new ElementListe<>(val, el.getSuivant());
        el.setSuivant(eltAInserer);
    }

    public void supprimerApres(ElementListe<E> elt) {
        if (elt == null) {
            throw new NullPointerException("Element est null!");
        } else {
            if (elt.getSuivant() == null) {
                throw new NullPointerException("Il n y a pas de suivant!");
            }
        }
        elt.setSuivant(elt.getSuivant().getSuivant());
    }

    public ElementListe<E> rechercher(E val) {
        ElementListe<E> elt = this.getPremier();
        while (!elt.getValeur().equals(val)) {
            elt = elt.getSuivant();
        }
        return elt;
    }

    public boolean contient(E val) {
        return rechercher(val) != null;
    }

    public boolean supprimerVal(E val) {
        ElementListe<E> courant = this.getPremier();
        ElementListe<E> precedent = null;
        while (courant != null && !courant.getValeur().equals(val)) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        if (courant == null) {
            return false;
        }
        if (precedent == null) {
            this.setPremier(courant.getSuivant());
            return true;
        } else {
            precedent.setSuivant(courant.getSuivant());
            return true;
        }
    }

    public void supprimerToutVal(E val) {
        ElementListe<E> courant = this.getPremier();
        while (courant != null) {
            if (courant.getValeur().equals(val)) {
                supprimerVal(courant.getValeur());
            }
            courant = courant.getSuivant();
        }
    }

    /**
     * Nettoie la valeur la plus fréquente;
     */
    public void nettoyage() {
        ElementListe<E> courant = this.getPremier();
        int[] cmp = new int[10];
        while (courant != null) {
            cmp[(int) courant.getValeur()]++;
            courant = courant.getSuivant();
        }
        int max = 0;
        Integer maxIndex = 0;
        for (int i = 0; i < 10; i++) {
            if (cmp[i] >= max) {
                max = cmp[i];
                maxIndex = i;
            }
        }
        this.supprimerToutVal((E) maxIndex);
    }

    public void tassement() {
        ElementListe<E> courant = this.getPremier();
        while (courant != null) {
            if (courant.getSuivant() != null && courant.equals(courant.getSuivant())) {
                this.supprimerApres(courant);
            } else {
                courant = courant.getSuivant();
            }
        }
    }

    public ListeChaine<E> fusionSorted(ListeChaine<E> b) {
        ListeChaine<E> result = new ListeChaine();
        ElementListe<E> elA = this.getPremier();
        ElementListe<E> elB = b.getPremier();
        while (elA != null || elB != null) {
            if (elA == null) {
                result.insererFin(elB.getValeur());
                elB = elB.getSuivant();
            } else if (elB == null) {
                result.insererFin(elA.getValeur());
                elA = elA.getSuivant();
            } else if (elA.compareTo(elB) > 0) {
                result.insererFin(elB.getValeur());
                elB = elB.getSuivant();
            } else if (elA.compareTo(elB) < 0) {
                result.insererFin(elA.getValeur());
                elA = elA.getSuivant();
            } else {
                result.insererFin(elA.getValeur());
                result.insererFin(elB.getValeur());
                elB = elB.getSuivant();
                elA = elA.getSuivant();
            }
        }
        return result;
    }

    public String toString() {
        ElementListe<E> courant = this.getPremier();
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

    public static ListeChaine<Integer> differenceSym(ListeChaine<Integer> A, ListeChaine<Integer> B) {
        ListeChaine<Integer> result = new ListeChaine();
        ElementListe<Integer> el1 = A.getPremier();
        ElementListe<Integer> el2 = B.getPremier();
        ElementListe<Integer>[] save = new ElementListe[1];
        return ajouterElement(el1, el2, save, result);

    }

    private static ListeChaine<Integer> ajouterElement(
            ElementListe<Integer> el1, ElementListe<Integer> el2, ElementListe<Integer> save[], ListeChaine<Integer> result) {
        if (el1 == null || el2 == null) {
            return result;
        }
        if (el1.getValeur() < el2.getValeur()) {
            ajouterEl(el1, save, result);
            el1 = el1.getSuivant();
            return ajouterElement(el1, el2, save, result);
        } else if (el1.getValeur() > el2.getValeur()) {
            ajouterEl(el2, save, result);
            el2 = el2.getSuivant();
            return ajouterElement(el2, el1, save, result);
        } else {
            el1 = el1.getSuivant();
            el2 = el2.getSuivant();
            return ajouterElement(el1, el2, save, result);
        }
    }

    private static void ajouterEl(
            ElementListe<Integer> el1, ElementListe<Integer>[] save, ListeChaine<Integer> result) {
        if (result.getPremier() == null) {
            result.insererTete(el1.getValeur());
            save[0] = result.getPremier();
        } else {
            result.insererApres(save[0], el1.getValeur());
            save[0] = save[0].getSuivant();
        }
    }

    //TODO
    @Deprecated
    public ListeChaine<E> sortedList() {
        if (this.getPremier() == null) {
            return null;
        }
        List<ElementListe<E>> tmpList = new ArrayList();
        ListeChaine<E> sortedList = new ListeChaine<E>();
        ElementListe<E> elt = this.getPremier();
        while (elt != null) {
            tmpList.add(elt);
            elt = elt.getSuivant();
        }
        tmpList.sort((ElementListe<E> a, ElementListe<E> b) -> a.compareTo(b));
        int i = tmpList.size() - 1;
        while (i >= 0) {
            sortedList.insererTete(tmpList.get(i).getValeur());
            --i;
        }
        return sortedList;
    }

    public ListeChaine<E> interTassement(ListeChaine<E> b) {
        ListeChaine<E> resultat = new ListeChaine();
        ElementListe<E> elA = this.getPremier();
        ElementListe<E> elB = b.getPremier();
        while (elA != null || elB != null) {
            if (elA != null) {
                resultat.insererFin(elA.getValeur());
                elA = elA.getSuivant();
            }
            if (elB != null) {
                resultat.insererFin(elB.getValeur());
                elB = elB.getSuivant();
            }
        }
        return resultat;
    }
}