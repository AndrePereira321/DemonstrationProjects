/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

class Data {

    public String nom;
    public String adre;
    public double taux;

    public Data(String nom, String adr) {
        this.nom = nom;
        this.adre = adre;
        this.taux = 1;
    }

}

class Personne {

    public int id;
    public String nom;
    public String adre;
}

/**
 *
 * @author Andre Pereira
 */
public class CactusHotel {

    public List<Data> calculateTaux(List<Personne> A, List<Personne> B, List<Personne> C) {
        List<Data> list = new ArrayList();
        HashMap<Integer, Data> map = new HashMap();
        updateList(list, map, A);
        updateList(list, map, B);
        updateList(list, map, C);
        return list;
    }

    public static void updateList(List<Data> result, HashMap<Integer, Data> map, List<Personne> A) {
        for (Personne personne : A) {
            if (map.containsKey(personne.id)) {
                map.get(personne.id).taux += 0.25;
            } else {
                Data dat = new Data(personne.nom, personne.adre);
                map.put(personne.id, dat);
                result.add(dat);
            }
        }
    }

    public static void main(String[] args) {
        List<Personne> A = new ArrayList();
        List<Personne> B = new ArrayList();
        List<Personne> C = new ArrayList();

    }

}
