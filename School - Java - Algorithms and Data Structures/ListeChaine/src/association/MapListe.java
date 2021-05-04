/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class MapListe<K, V> {

    private Liste<K, V>[] table;
    private static final int MAX = 100;

    public MapListe() {
        this.table = new Liste[MAX];
    }

    public void setElement(K key, V value) {
        int index = MapListe.hash(key);

        if (this.table[index] == null) {
            this.table[index] = new Liste();
            this.table[index].insererTete(key, value);
        } else {
            Element e = this.table[index].getFirst();

            while (e != null && !e.getKey().equals(key)) {
                e = e.getNext();
            }

            if (e == null) {
                this.table[index].insererTete(key, value);
            } else {
                e.setValue(value);
            }
        }
    }
    
    public List<K> getListeCles() {
        List<K> result = new ArrayList();
        for(Liste<K, V> l : this.table) {
            if (l != null) {
                Element<K, V> el = l.getFirst();
                while(el != null) {
                    result.add(el.getKey());
                    el = el.getNext();
                }
            }
        }
        return result;
    }

    public V getValue(K key) {
        int index = MapListe.hash(key);

        if (this.table[index] == null) {
            throw new IllegalStateException("Pas d'element trouve pour cette cle");
        }

        Element<K, V> e = this.table[index].getFirst();

        while (e != null && !e.getKey().equals(key)) {
            e = e.getNext();
        }

        return e.getValue();
    }

    public void supprimer(K key) {
        int index = MapListe.hash(key);

        if (this.table[index] == null) {
            throw new IllegalStateException("Pas d'element trouve");
        }

        Element<K, V> e = this.table[index].getFirst();
        Element prec = e;

        while (e != null && !e.getKey().equals(key)) {
            prec = e;
            e = e.getNext();
        }

        if (e == null) {
            throw new IllegalStateException("Pas d'element trouve!");
        }

        if (e.equals(this.table[index].getFirst())) {
            this.table[index] = null;
        } else {
            this.table[index].suprimerApres(prec);
        }
    }

    public boolean contient(K key) {
        int index = MapListe.hash(key);

        if (this.table[index] == null) {
            return false;
        }

        Element e = this.table[index].getFirst();
        while (e != null) {
            if (e.getKey().equals(key)) {
                return true;
            }
        }
        return false;

    }

    public int taille() {
        int cmp = 0;
        for (Liste liste : table) {
            if (liste != null) {
                Element e = liste.getFirst();
                while (e != null) {
                    cmp++;
                    e = e.getNext();
                }
            }
        }
        return cmp;
    }

    private static int hash(Object key) {
        return (int) Math.abs(key.hashCode() % MAX);
    }

    public void afficher() {
        for (Liste liste : table) {
            System.out.println(liste);
        }
    }

    public static void main(String[] args) {
        MapListe<String, String> fds = new MapListe();
        fds.setElement("OI", "Trinta");
        fds.setElement("OI", "Trina");
        fds.setElement("VINTE", "YH");
        fds.setElement("MEGA", "Trinta");
        fds.setElement("TRII", "Trina");
        fds.setElement("TRATOO", "YH");
        fds.setElement("DDDDD", "Trinta");
        fds.setElement("MGGG", "Trina");
        fds.setElement("GSFFE", "YH");
        fds.setElement("QFSGIE", "Trinta");
        fds.setElement("AAAA", "Trina");
        fds.setElement("FFFF", "YH");
        fds.setElement("OI", "Trinta");
        fds.setElement("DDDD", "Trina");
        fds.setElement("YOUA", "YH");

        fds.supprimer("OI");
        fds.afficher();
        System.out.println();
    }

}
