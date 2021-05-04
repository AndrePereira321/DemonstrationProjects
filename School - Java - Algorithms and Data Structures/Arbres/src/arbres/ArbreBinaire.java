package arbres;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Andre Pereira
 */
public class ArbreBinaire<T> {

    private NoeudBinaire<T> racine;

    public ArbreBinaire() {
        this.racine = null;
    }

    public NoeudBinaire<T> getRacine() {
        return this.racine;
    }

    public void setRacine(NoeudBinaire<T> racine) {
        this.racine = racine;
    }

    public static int hauteurNoeud(NoeudBinaire<Integer> noeud) {
        return hauteurNoeudAbstract(noeud, 0);
    }

    private static int hauteurNoeudAbstract(NoeudBinaire<Integer> noeud, int h) {
        if (noeud == null) {
            return h - 1;
        }
        NoeudBinaire<Integer> droit = noeud.getDroit();
        NoeudBinaire<Integer> gauche = noeud.getGauche();
        int hd = hauteurNoeudAbstract(droit, h + 1);
        int hg = hauteurNoeudAbstract(gauche, h + 1);
        return (hd > hg) ? hd : hg;
    }

    private static boolean estEquilibre(NoeudBinaire<Integer> noeud) {
        if (noeud == null) {
            return true;
        } else {
            int diff = hauteurNoeud(noeud.getDroit()) - hauteurNoeud(noeud.getGauche());
            if (-1 < diff && diff < 1) {
                return estEquilibre(noeud.getDroit()) && estEquilibre(noeud.getGauche());
            } else {
                return false;
            }
        }
    }

    public static boolean estPaire(ArbreBinaire<Integer> arbre) {
        Queue<NoeudBinaire<Integer>> pourTraiter = new LinkedList();
        NoeudBinaire<Integer> courant = arbre.getRacine();
        pourTraiter.add(courant);
        while (!pourTraiter.isEmpty()) {
            courant = pourTraiter.remove();
            if (courant.getGauche() != null && courant.getDroit() != null) {
                pourTraiter.add(courant.getDroit());
                pourTraiter.add(courant.getGauche());
            } else {
                if (courant.getDroit() != null || courant.getGauche() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean estOrdonne(ArbreBinaire<Integer> arbre) {
        NoeudBinaire<Integer> courant = arbre.getRacine();
        Queue<NoeudBinaire<Integer>> pourTraiter = new LinkedList();
        pourTraiter.add(courant);
        while (!pourTraiter.isEmpty()) {
            courant = pourTraiter.remove();
            if (courant.getDroit() != null) {
                if (courant.getValeur() > courant.getDroit().getValeur()) {
                    return false;
                }
                pourTraiter.add(courant.getDroit());
            }
            if (courant.getGauche() != null) {
                if (courant.getValeur() < courant.getGauche().getValeur()) {
                    return false;
                }
                pourTraiter.add(courant.getGauche());
            }
        }
        return true;
    }

    public static void ajouterValeur(ArbreBinaire<Integer> arbreOrdonne, Integer val) {
        //TODO
    }

    public static void main(String[] args) {

        //Non Ordonne
        ArbreBinaire<Integer> arbreBinaire = new ArbreBinaire();
        NoeudBinaire<Integer> racine = new NoeudBinaire(0);
        arbreBinaire.setRacine(racine);
        NoeudBinaire<Integer> rd = new NoeudBinaire(1);
        NoeudBinaire<Integer> rg = new NoeudBinaire(2);
        racine.setGauche(rg);
        racine.setDroit(rd);
        NoeudBinaire<Integer> rgd = new NoeudBinaire(5);
        NoeudBinaire<Integer> rgg = new NoeudBinaire(6);
        rg.setDroit(rgd);
        rg.setGauche(rgg);
        NoeudBinaire<Integer> rgdd = new NoeudBinaire(10);
        NoeudBinaire<Integer> rgdg = new NoeudBinaire(11);
        rgd.setDroit(rgdd);
        rgd.setGauche(rgdg);
        NoeudBinaire<Integer> rggd = new NoeudBinaire(12);
        NoeudBinaire<Integer> rggg = new NoeudBinaire(13);
        rgg.setDroit(rggd);
        rgg.setGauche(rggg);

        /// Arbre Ordonne
        ArbreBinaire<Integer> arbreOrd = new ArbreBinaire();
        NoeudBinaire<Integer> oracine = new NoeudBinaire(2);
        arbreOrd.setRacine(oracine);
        NoeudBinaire<Integer> ord = new NoeudBinaire(20);
        NoeudBinaire<Integer> org = new NoeudBinaire(1);
        oracine.setGauche(org);
        oracine.setDroit(ord);
        NoeudBinaire<Integer> orgd = new NoeudBinaire(26);
        NoeudBinaire<Integer> orgg = new NoeudBinaire(15);
        ord.setDroit(orgd);
        ord.setGauche(orgg);
        NoeudBinaire<Integer> orgdd = new NoeudBinaire(27);
        NoeudBinaire<Integer> orgdg = new NoeudBinaire(23);
        orgd.setDroit(orgdd);
        orgd.setGauche(orgdg);
        NoeudBinaire<Integer> orggd = new NoeudBinaire(16);
        NoeudBinaire<Integer> orggg = new NoeudBinaire(10);
        orgg.setDroit(orggd);
        orgg.setGauche(orggg);

        //// Algorithmes
        System.out.println(hauteurNoeud(racine));
        System.out.println(estEquilibre(rg));
        System.out.println(estPaire(arbreBinaire));
        System.out.println(estOrdonne(arbreOrd));
    }
}
