package arbres;

/**
 *
 * @author Andre Pereira
 */
public class Noeud<T> {

    private T valeur;
    private ListeChaine<Noeud<T>> listeFils;

    public Noeud(T valeur) {
        this.listeFils = new ListeChaine();
        this.valeur = valeur;
    }

    public T getValeur() {
        return this.valeur;
    }

    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    public ListeChaine<Noeud<T>> getListeFils() {
        return this.listeFils;
    }

    public int getNbFils() {
        ElementListe<Noeud<T>> i = this.listeFils.getPremier();
        int cmp = 0;
        while (i != null) {
            cmp++;
            i = i.getSuivant();
        }
        return cmp;
    }

    public Noeud<T> getFils(int i) {
        ElementListe<Noeud<T>> el = this.listeFils.getPremier();
        int cmp = 0;
        while (el != null && cmp < i) {
            el = el.getSuivant();
            cmp++;
        }
        return el.getValeur();
    }

    public void setFils(int i, Noeud<T> noeud) {
        if (i == 0) {
            this.listeFils.insererTete(noeud);
        } else {
            ElementListe<Noeud<T>> el = this.listeFils.getPremier();
            int cmp = 0;
            while (el.getSuivant() != null && cmp < i - 1) {
                ++cmp;
                el = el.getSuivant();
            }
            this.listeFils.insererApres(el, noeud);
        }
    }

    public void ajouterFils(Noeud<T> fils) {
        ElementListe<Noeud<T>> el = this.listeFils.getPremier();
        if (el == null) {
            this.listeFils.insererTete(fils);
        } else {
            while (el.getSuivant() != null) {
                el = el.getSuivant();
            }
            this.listeFils.insererApres(el, fils);
        }
    }

    public void supprimerFils(int i) {
        ElementListe<Noeud<T>> el = this.listeFils.getPremier();
        int cmp = 0;
        if (i == 0) {
            this.listeFils.supprimerTete();
        } else {
            while (el != null && cmp < i - 1) {
                ++cmp;
                el = el.getSuivant();
            }
            this.listeFils.supprimerApres(el);
        }
    }
    
    public String toString() {
        return "" + this.valeur;
    }

    public static void main(String[] args) {
        Noeud<Integer> n1 = new Noeud(0);
        n1.ajouterFils(new Noeud<Integer>(1));
        n1.ajouterFils(new Noeud<Integer>(2));
        n1.ajouterFils(new Noeud<Integer>(3));
        n1.ajouterFils(new Noeud<Integer>(4));
        n1.supprimerFils(4);
        System.out.println(n1.getListeFils());
    }
}
