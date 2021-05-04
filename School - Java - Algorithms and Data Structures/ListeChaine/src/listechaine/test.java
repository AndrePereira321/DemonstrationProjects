/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listechaine;

/**
 *
 * @author Andre Pereira
 */
public class test {

    static void symmDiff(int[] arr1, int[] arr2,
            int n, int m) {
        // Traverse both arrays simultaneously. 
        int i = 0, j = 0;
        while (i < n && j < m) {
            // Print smaller element and move  
            // ahead in array with smaller element 
            if (arr1[i] < arr2[j]) {
                System.out.print(arr1[i] + " ");
                i++;
            } else if (arr2[j] < arr1[i]) {
                System.out.print(arr2[j] + " ");
                j++;
            } // If both elements same, move ahead 
            // in both arrays. 
            else {
                i++;
                j++;
            }
        }
    }

// Driver code 
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 5, 7, 8, 10, 12, 15, 18, 19};
        int[] arr2 = {5, 8, 11, 12, 14, 15, 16, 17};
        int n = arr1.length;
        int m = arr2.length;
        symmDiff(arr1, arr2, n, m);
    }
}

/* This code is contributed by Kriti Shukla */


