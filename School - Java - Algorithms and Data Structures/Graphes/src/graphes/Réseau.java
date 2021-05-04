package graphes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class Réseau {

    private LinkedList<String>[] lignes;

    public Réseau(LinkedList<String>[] lignes) {
        this.lignes = lignes;
    }

    public String[] getStations() {
        String[] stations = new String[500];
        int cmp = 0;
        for (LinkedList<String> l : lignes) {
            for (String station : l) {
                if (!Arrays.stream(stations).anyMatch(station::equals)) {
                    stations[cmp++] = station;
                }
            }
        }
        return stations;
    }

    public boolean[][] getAdjacence() {
        List<String> stations = Arrays.asList(this.getStations());
        boolean[][] a = new boolean[stations.size()][stations.size()];
        for (LinkedList<String> l : lignes) {
            for (int i = 0; i < l.size(); i++) {
                if (i - 1 >= 0) {
                    a[stations.indexOf(l.get(i))][stations.indexOf(l.get(i - 1))] = true;
                }
                if (i + 1 < l.size()) {
                    a[stations.indexOf(l.get(i))][stations.indexOf(l.get(i + 1))] = true;
                }
            }
        }
        return a;
    }

    public int getDistance(String s1, String s2) {
        List<String> stations = Arrays.asList(this.getStations());
        boolean[][] a = this.getAdjacence();
        int[][] p = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j]) {
                    p[i][j] = 1;
                } else {
                    p[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }
        for (int k = 0; k < a.length; k++) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    p[i][j] = min(p[i][j], p[i][k] + p[k][j]);
                }
            }
        }
        Graphes.print(p);
        return p[stations.indexOf(s1)][stations.indexOf(s2)];
    }

    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        LinkedList<String>[] lignes = new LinkedList[2];
        LinkedList<String> l6 = new LinkedList();
        LinkedList<String> l2 = new LinkedList();
        lignes[1] = l6;
        lignes[0] = l2;
        addAll(l6, "Bekkant", "Gare du West", "Decroli", "Clemenceau", "Midi");
        addAll(l2, "Stockel", "Gare du West", "Bekkant", "Erasmus");
        Réseau reseau = new Réseau(lignes);
        System.out.println(Arrays.toString(reseau.getStations()));
        // Graphes.print(Graphes.royWarshall(reseau.getAdjacence()));
        System.out.println("");
        System.out.println(reseau.getDistance("Gare du West", "Bekkant"));

    }

    public static void addAll(LinkedList<String> l, String... args) {
        for (String s : args) {
            l.add(s);
        }
    }
}
