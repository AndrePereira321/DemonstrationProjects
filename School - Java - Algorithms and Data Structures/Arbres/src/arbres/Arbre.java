package arbres;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Andre Pereira
 */
public class Arbre<T> {

    private Noeud<T> racine;

    public Arbre() {
        racine = null;
    }

    public Noeud<T> getRacine() {
        return this.racine;
    }

    public void setRacine(Noeud<T> racine) {
        this.racine = racine;
    }

    public static int compterNoeuds(Arbre<Integer> arbre) {
        if (arbre.getRacine() == null) {
            return 0;
        }
        Queue<Noeud<Integer>> file = new LinkedList();
        Noeud<Integer> courant = arbre.getRacine();
        file.add(courant);
        int i = 1;
        while (!file.isEmpty()) {
            courant = file.remove();
            for (int j = 0; j < courant.getNbFils(); j++) {
                file.add(courant.getFils(j));
                i++;
            }
        }
        return i;
    }

    public static int compterFueilles(Arbre<Integer> arbre) {
        return compterFueillesAbstract(arbre.getRacine());
    }

    private static int compterFueillesAbstract(Noeud<Integer> noeud) {
        if (noeud.getNbFils() == 0) {
            return 1;
        } else {
            int cmp = 0;
            for (int i = 0; i < noeud.getNbFils(); i++) {
                cmp += compterFueillesAbstract(noeud.getFils(i));
            }
            return cmp;
        }
    }

    public static int hauteurArbre(Arbre<Integer> arbre) {
        return hauteurArbreAbstract(arbre.getRacine(), 0);
    }

    private static int hauteurArbreAbstract(Noeud<Integer> noeud, int prof) {
        if (noeud.getNbFils() == 0) {
            return prof;
        } else {
            int max = 0;
            for (int i = 0; i < noeud.getNbFils(); i++) {
                if (hauteurArbreAbstract(noeud.getFils(i), prof + 1) > max) {
                    max = hauteurArbreAbstract(noeud.getFils(i), prof + 1);
                }
            }
            return max;
        }
    }

    public static int noeudsDansNiveau(Arbre<Integer> arbre, int niveau) {
        return noeudsDansNiveauAbstract(arbre.getRacine(), niveau, 0);
    }

    private static int noeudsDansNiveauAbstract(Noeud<Integer> noeud, int niveau, int prof) {
        if (prof == niveau) {
            return 1;
        } else {
            int cmp = 0;
            for (int i = 0; i < noeud.getNbFils(); i++) {
                cmp += noeudsDansNiveauAbstract(noeud.getFils(i), niveau, prof + 1);
            }
            return cmp;
        }
    }

    //CHEMIN DU PLUS GRAND
    public static List<Integer> cheminDuPlusGrand(Arbre<Integer> arbre) {
        List<Integer> result = new ArrayList();
        System.out.println(cheminDuPlusGrand(arbre.getRacine(), result, Integer.MIN_VALUE));
        return result;
    }

    private static int cheminDuPlusGrand(Noeud<Integer> noeud, List<Integer> chemin, int max) {
        if (noeud.getListeFils().estVide()) {
            if (noeud.getValeur() > max) {
                chemin.clear();
                return noeud.getValeur();
            }
            return max;
        } else {
            int iMax = -1;
            for (int i = 0; i < noeud.getNbFils(); i++) {
                int result = cheminDuPlusGrand(noeud.getFils(i), chemin, max);
                if (result > max) {
                    max = result;
                    iMax = i;
                }
            }
            if (iMax != -1) {
                chemin.add(iMax);
            }
            return max;

        }
    }

    public static int sommeFueilles(Arbre<Integer> arbre) {
        return sommeFueilles(arbre.getRacine());
    }

    public static int sommeFueilles(Noeud<Integer> noeud) {
        int size = noeud.getNbFils();
        if (size == 0) {
            return noeud.getValeur();
        } else {
            int somme = 0;
            for (int i = 0; i < size; i++) {
                somme += sommeFueilles(noeud.getFils(i));
            }
            noeud.setValeur(somme);
            return somme;
        }
    }

    public static int compterDifferents(Arbre<Integer> arbre) {
        LinkedList<Integer> vals = new LinkedList();
        Queue<Noeud<Integer>> pourTraiter = new LinkedList();
        Noeud<Integer> courant = arbre.getRacine();
        pourTraiter.add(courant);
        while (!pourTraiter.isEmpty()) {
            courant = pourTraiter.remove();
            if (!vals.contains(courant.getValeur())) {
                vals.add(courant.getValeur());
            }
            for (int i = 0; i < courant.getNbFils(); i++) {
                pourTraiter.add(courant.getFils(i));
            }
        }
        return vals.size();
    }

    public static void dividerHeritage(Arbre<Integer> arbre) {
        Queue<Noeud<Integer>> file = new LinkedList();
        Noeud<Integer> courant;
        for (int i = 0; i < arbre.getRacine().getNbFils(); i++) {
            courant = arbre.getRacine().getFils(i);
            courant.setValeur(arbre.getRacine().getValeur() / arbre.getRacine().getNbFils());
            file.add(courant);
        }
        while (!file.isEmpty()) {
            courant = file.remove();
            int size = courant.getNbFils();
            if (size > 1) {
                courant.setValeur(courant.getValeur() / 2);
                for (int i = 0; i < size; i++) {
                    Noeud<Integer> nNoeud = courant.getFils(i);
                    nNoeud.setValeur(courant.getValeur() / size);
                    file.add(nNoeud);
                }
            }
        }
    }

    public static Stack<Integer> cheminMax(Arbre<Integer> arbre) {
        Stack<Integer> chemin = new Stack();
        cheminMax(arbre.getRacine(), chemin);
        return chemin;
    }

    public static int cheminMax(Noeud<Integer> noeud, Stack<Integer> chemin) {
        int max = Integer.MIN_VALUE;
        int iMax = -1;
        int size = noeud.getNbFils();
        if (size == 0) {
            return noeud.getValeur();
        } else {
            for (int i = 0; i < size; i++) {
                Stack<Integer> nChemin = new Stack();
                int val = cheminMax(noeud.getFils(i), nChemin);
                if (val >= max) {
                    max = val;
                    iMax = i;
                    chemin.clear();
                    chemin.addAll(nChemin);
                }
            }
            chemin.add(iMax);
            return max;
        }
    }

    public static void main(String[] args) {
        Arbre<Integer> arbre = new Arbre();
        Noeud<Integer> racine = new Noeud(0);
        Noeud<Integer> n1 = new Noeud(1);
        Noeud<Integer> nn1 = new Noeud(8);
        n1.ajouterFils(new Noeud(6));
        n1.ajouterFils(new Noeud(7));
        n1.ajouterFils(nn1);
        nn1.ajouterFils(new Noeud(9));
        nn1.ajouterFils(new Noeud(10));
        arbre.setRacine(racine);
        racine.ajouterFils(n1);
        racine.ajouterFils(new Noeud(2));
        racine.ajouterFils(new Noeud(3));
        racine.ajouterFils(new Noeud(4));
        racine.ajouterFils(new Noeud(5));
        System.out.println(cheminMax(arbre));
        System.out.println(sommeFueilles(arbre));
        System.out.println(arbre.getRacine().getListeFils());
        System.out.println(compterNoeuds(arbre));
        System.out.println(compterFueilles(arbre));
        System.out.println(hauteurArbre(arbre));
        System.out.println(noeudsDansNiveau(arbre, 3));
        System.out.println(compterDifferents(arbre));
        System.out.println(cheminDuPlusGrand(arbre));
        dividerHeritage(arbre);
    }
}
