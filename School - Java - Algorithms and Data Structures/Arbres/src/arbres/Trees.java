package arbres;


import java.util.TreeSet;

public class Trees {

    public static void main(String[] args) {
        TreeSet<Integer> T = new TreeSet();

        /* L'ajout des éléments dans l'arbre se fait
        de façon ordonnée, comme expliqué 
        dans la section 7.7 du syllabus */
        T.add(1);
        T.add(3);
        T.add(4);
        T.add(2);
        T.add(0);
        T.add(20);
        T.add(5);

        System.out.println(T);

    }

}
