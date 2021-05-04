/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import listechaine.ElementListe;
import listechaine.ListeChaine;

/**
 *
 * @author Andre Pereira
 */
public class Association {

    public static String meilleurCote(ListeChaine<Point> liste) {
        ElementListe<Point> courant = liste.getPremier();
        MapListe<String, ListeChaine<Point>> pointsEleves = new MapListe();
        MapListe<String, Double> totalPoints = new MapListe();
        while (courant != null) {
            String cours = courant.getValeur().nomCours;
            String eleve = courant.getValeur().matricule;
            if (totalPoints.contient(cours)) {
                totalPoints.setElement(cours, totalPoints.getValue(cours) + courant.getValeur().points);
            } else {
                totalPoints.setElement(cours, courant.getValeur().points);
            }
            if (pointsEleves.contient(eleve)) {
                pointsEleves.getValue(eleve).insererTete(courant.getValeur());
            } else {
                pointsEleves.setElement(eleve, new ListeChaine<Point>());
                pointsEleves.getValue(eleve).insererTete(courant.getValeur());
            }
            courant = courant.getSuivant();
        }
        return null;
    }

    public static void main(String[] args) {
        MapListe<String, Integer> map = new MapListe();
        map.setElement("Les Saules", 13);
        map.setElement("Les Chenes", 8);
        map.setElement("Les Frenes", 4);
        formationGouvernement(map);
    }

    public static void formationGouvernement(MapListe<String, Integer> map) {
        formationGouv(map, new ArrayList(), 0, 25);
    }

    public static void formationGouv(MapListe<String, Integer> map, List<String> used, int p, int nSieges) {
        if (used.size() != 3) {
            for (String par : map.getListeCles()) {
                if (!used.contains(par)) {
                    used.add(par);
                    formationGouv(map, used, p + map.getValue(par), nSieges);
                    used.remove(par);
                }
            }
        }
        if (p > nSieges / 2) {
            System.out.println(Arrays.toString(used.toArray()));
        }
    }

}

class Point {

    String matricule;
    String nom;
    String prenom;
    String nomCours;
    double points;

    public Point(String matricule, String nom, String prenom, String nomCours, double points) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.nomCours = nomCours;
        this.points = points;
    }

}
