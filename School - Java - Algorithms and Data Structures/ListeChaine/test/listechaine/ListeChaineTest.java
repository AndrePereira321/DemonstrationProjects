/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listechaine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre Pereira
 */
public class ListeChaineTest {

    public ListeChaineTest() {
    }

    /**
     * Test of getPremier method, of class ListeChaine.
     */
    @Test
    public void testGetPremier() {
        System.out.println("getPremier");
        ListeChaine instance = new ListeChaine();
        ElementListe expResult = null;
        ElementListe result = instance.getPremier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPremier method, of class ListeChaine.
     */
    @Test
    public void testSetPremier() {
        System.out.println("setPremier");
        ListeChaine instance = new ListeChaine();
        instance.setPremier(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estVide method, of class ListeChaine.
     */
    @Test
    public void testEstVide() {
        System.out.println("estVide");
        ListeChaine instance = new ListeChaine();
        boolean expResult = false;
        boolean result = instance.estVide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insererTete method, of class ListeChaine.
     */
    @Test
    public void testInsererTete() {
        System.out.println("insererTete");
        Object val = null;
        ListeChaine instance = new ListeChaine();
        instance.insererTete(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerTete method, of class ListeChaine.
     */
    @Test
    public void testSupprimerTete() {
        System.out.println("supprimerTete");
        ListeChaine instance = new ListeChaine();
        instance.supprimerTete();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerApres method, of class ListeChaine.
     */
    @Test
    public void testSupprimerApres() {
        System.out.println("supprimerApres");
        ListeChaine instance = new ListeChaine();
        instance.supprimerApres(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechercher method, of class ListeChaine.
     */
    @Test
    public void testRechercher() {
        System.out.println("rechercher");
        Object val = null;
        ListeChaine instance = new ListeChaine();
        ElementListe expResult = null;
        ElementListe result = instance.rechercher(val);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contient method, of class ListeChaine.
     */
    @Test
    public void testContient() {
        System.out.println("contient");
        Object val = null;
        ListeChaine instance = new ListeChaine();
        boolean expResult = false;
        boolean result = instance.contient(val);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerVal method, of class ListeChaine.
     */
    @Test
    public void testSupprimerVal() {
        System.out.println("supprimerVal");
        Object val = null;
        ListeChaine instance = new ListeChaine();
        boolean expResult = false;
        boolean result = instance.supprimerVal(val);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerToutVal method, of class ListeChaine.
     */
    @Test
    public void testSupprimerToutVal() {
        System.out.println("supprimerToutVal");
        Object val = null;
        ListeChaine instance = new ListeChaine();
        instance.supprimerToutVal(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nettoyage method, of class ListeChaine.
     **/
    @Test
    public void testNettoyage() {
        System.out.println("nettoyage");
        ListeChaine<Integer> instance = new ListeChaine();
        ListeChaine<Integer> autreInstance = new ListeChaine();
        instance.insererTete(5);
        instance.insererTete(2);
        instance.insererTete(2);
        instance.insererTete(2);
        instance.insererTete(5);
        instance.insererTete(5);
        instance.insererTete(8);
        instance.insererTete(9);
        instance.insererTete(3);
        instance.insererTete(0);
        instance.insererTete(0);
        instance.insererTete(0);
        instance.insererTete(0);
        System.out.println(instance);
        autreInstance.insererTete(5);
        autreInstance.insererTete(2);
        autreInstance.insererTete(2);
        autreInstance.insererTete(2);
        autreInstance.insererTete(5);
        autreInstance.insererTete(5);
        autreInstance.insererTete(8);
        autreInstance.insererTete(9);
        autreInstance.insererTete(3);
        instance.nettoyage();
        System.out.println(instance);
        System.out.println(autreInstance);
        assertEquals(instance.equals(autreInstance), true);
    } 

    /**
     * Test of toString method, of class ListeChaine.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ListeChaine instance = new ListeChaine();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ListeChaine.
     */
    @Test
    public void testEqualsWhenTrue() {
        System.out.println("equals");
        ListeChaine<Integer> instance = new ListeChaine();
        ListeChaine<Integer> autreInstance = new ListeChaine();
        instance.insererTete(3);
        autreInstance.insererTete(3);
        instance.insererTete(5);
        autreInstance.insererTete(5);
        instance.insererTete(8);
        autreInstance.insererTete(8);
        instance.insererTete(12);
        autreInstance.insererTete(12);
        instance.insererTete(-1);
        autreInstance.insererTete(-1);
        instance.insererTete(3);
        autreInstance.insererTete(3);
        boolean expResult = true;
        boolean result = instance.equals(autreInstance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ListeChaine.
     */
    @Test
    public void testEqualsWhenFalse() {
        System.out.println("equals");
        ListeChaine<Integer> instance = new ListeChaine();
        ListeChaine<Integer> autreInstance = new ListeChaine();
        instance.insererTete(3);
        autreInstance.insererTete(3);
        instance.insererTete(5);
        autreInstance.insererTete(5);
        instance.insererTete(8);
        autreInstance.insererTete(8);
        instance.insererTete(12);
        autreInstance.insererTete(12);
        instance.insererTete(-1);
        autreInstance.insererTete(-1);
        instance.insererTete(3);
        boolean expResult = false;
        boolean result = instance.equals(autreInstance);
        assertEquals(expResult, result);
    }

}
