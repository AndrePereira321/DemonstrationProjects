/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revisions;

import association.MapListe;
import java.util.ArrayDeque;
import java.util.Queue;
import listechaine.ElementListe;
import listechaine.ListeChaine;
import pile.Pile;

/**
 *
 * @author Andre Pereira
 */
public class Revisions {

    public static ListeChaine<String> rechercheMin(Queue<String> file) {
        ListeChaine<String> list = new ListeChaine();
        MapListe<String, Integer> map = new MapListe();
        int max = 0;
        while (!file.isEmpty()) {
            String chaine = file.poll();
            if (!map.contient(chaine)) {
                map.setElement(chaine, 1);
            } else {
                map.setElement(chaine, map.getValue(chaine) + 1);
                if (map.getValue(chaine) == max) {
                    list.insererTete(chaine);
                } else if (map.getValue(chaine) > max) {
                    list.setPremier(null);
                    list.insererTete(chaine);
                    max = map.getValue(chaine);
                }
            }
        }
        return list;
    }

    public static ListeChaine<String> Enumeration(char[] tab, int n) {
        ListeChaine<String> list = new ListeChaine();
        combineChars(list, tab, n, 0, "");
        return list;
    }

    private static void combineChars(
            ListeChaine<String> list, char[] tab, int n, int pos, String prec) {
        pos++;
        for (int i = 0; i < tab.length; ++i) {
            if (pos == n) {
                list.insererTete(prec + tab[i]);
            } else {
                combineChars(list, tab, n, pos, prec + tab[i]);
            }
        }
    }

    public static ListeChaine<String> Pallindrome(char[] tab, int n) {
        ListeChaine<String> pallindromes = new ListeChaine();
        if (n == 1) {
            for (char c : tab) {
                pallindromes.insererTete(c+"");
            }
        } else {
            makePallindrome(tab, n, 0, "", pallindromes);
        }
        return pallindromes;
    }

    private static void makePallindrome(char[] tab, int n, int count, String half,
            ListeChaine<String> pallindromes) {
        for (char c : tab) {
            if (count == n / 2) {
                if (n % 2 == 0) {
                    pallindromes.insererTete(addInverse(half, half.length() - 1, half));
                } else {
                    for (char d : tab) {
                        pallindromes.insererTete(addInverse(half, half.length() - 1, half + d));
                    }
                }
            } else {
                makePallindrome(tab, n, count + 1, half + c, pallindromes);
            }
        }
    }

    private static String addInverse(String half, int end, String result) {
        if (end >= 0) {
            return addInverse(half, end - 1, result + half.charAt(end));
        } else {
            //System.out.println(result);
            return result;
        }
    }

    public static void displayCommonStrings(Pile<String> A, Pile<String> B) {
        Pile<String> tmpA = new Pile();
        Pile<String> tmpB = new Pile();
        while (!A.estVide()) {
            while (!B.estVide()) {
                if (B.sommet().equals(A.sommet())) {
                    System.out.println(A.sommet());
                }
                tmpB.empiler(B.depiler());
            }
            while (!tmpB.estVide()) {
                B.empiler(tmpB.depiler());
            }
            tmpA.empiler(A.depiler());
        }
        while (!tmpA.estVide()) {
            A.empiler(tmpA.depiler());
        }
    }

    public static ListeChaine<Integer> extractionListe(ListeChaine<Integer> input, int n) {
        ListeChaine<Integer> list = new ListeChaine();
        ElementListe<Integer> el = input.getPremier();
        ElementListe<Integer> suivant = el;
        ElementListe<Integer> saveEl = null;
        while (suivant != null) {
            suivant = el.getSuivant();
            if (el.getValeur() > n) {
                input.supprimerVal(el.getValeur());
                if (list.getPremier() == null) {
                    saveEl = el;
                    list.setPremier(el);
                    el.setSuivant(null);
                } else {
                    saveEl.setSuivant(el);
                    el.setSuivant(null);
                    saveEl = el;
                }
            }
            el = suivant;
        }
        return list;
    }
    
    public static void ajoutIntercalaire(ListeChaine<Integer> list, int val) {
        ElementListe<Integer> el = list.getPremier();
        while(el != null && el.getSuivant() != null) {
            ElementListe<Integer> tmp = el.getSuivant();
            el.setSuivant(new ElementListe<Integer>(val));
            el.getSuivant().setSuivant(tmp);
            el = el.getSuivant().getSuivant();
        }
    }

    public static void main(String[] args) {
        Queue<String> file = new ArrayDeque();
        file.add("a");
        file.add("b");
        file.add("b");
        file.add("a");
        file.add("c");
        ListeChaine<String> result = rechercheMin(file);
        System.out.println(result);
        System.out.println("=================================");
        result.setPremier(null);
        char[] cTab = {'a', 'b'};
        ListeChaine<String> result2 = new ListeChaine();
        result2 = Enumeration(cTab, 3);
        System.out.println(result2);
        System.out.println("=================================");
        ListeChaine<Integer> extract = new ListeChaine();
        extract.insererTete(-2);
        extract.insererTete(10);
        extract.insererTete(8);
        extract.insererTete(4);
        extract.insererTete(3);
        extract.insererTete(1);
        extract.insererTete(5);
        extract.insererTete(2);
        System.out.println(extract);
        System.out.println("Apres extract: ");
        ListeChaine<Integer> result3 = extractionListe(extract, 6);
        System.out.println("Liste initiale: " + extract);
        System.out.println("Sortie: " + result3);
        System.out.println("=======================================");
        Pile<String> A = new Pile();
        A.empiler("abc");
        A.empiler("cde");
        A.empiler("trinta");
        A.empiler("ab");
        A.empiler("du");
        Pile<String> B = new Pile();
        B.empiler("ab");
        B.empiler("deee");
        B.empiler("vag");
        B.empiler("cde");
        B.empiler("trinta");
        displayCommonStrings(A, B);
        String ola = "olaa";
        test(ola);
        System.out.println(ola);
        System.out.println("=============PALINDROMES=================");
        char[] palli = {'a', 'b', 'c'};
        ListeChaine<String> pallindromes = Pallindrome(palli, 1);
        System.out.println(pallindromes);
        System.out.println("========================================");
        System.out.println(extract);
        ajoutIntercalaire(extract, 2);
        System.out.println(extract);

    }

    public static void test(String tst) {

    }

}
