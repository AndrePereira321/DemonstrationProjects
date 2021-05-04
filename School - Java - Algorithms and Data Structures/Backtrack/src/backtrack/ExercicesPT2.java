package backtrack;

import java.util.Arrays;

/**
 *
 * @author Andre Pereira
 */
public class ExercicesPT2 {
    
    public static final int[] tric = new int[10];
    
    
    public static void main(String[] args) {
        tricolore();
    }
    
    public static void tricolore() {
        if (triBacktrack(0)) {
            System.out.println("Voici le premier tri trouvée: ");
            System.out.println(Arrays.toString(tric));
        } else {
            System.out.println("Pas trouve");
        }
    }
    
    public static boolean triBacktrack(int pos) {
        if(pos == tric.length) {
            return true;
        } else {
            for(int i = 0; i <= 2; i++) {
                if(triEstAcceptable(i, pos)) {
                    tric[pos] = i;
                    return triBacktrack(pos + 1);    
                }
            }
        }
        return false;
    }
    
    public static boolean triEstAcceptable(int nb, int pos) {
        for(int f = 1; f <= (pos + 1) / 2; f++) {
            boolean estIdentique = true;
            for(int i = 1; i < 3 && pos-(f*i) >= 0; i++) {
                estIdentique = estIdentique && tric[pos-(f*i)] == nb;
            }
            if(estIdentique) {
                return false;
            }
        }
        return true;
    }
    
}
