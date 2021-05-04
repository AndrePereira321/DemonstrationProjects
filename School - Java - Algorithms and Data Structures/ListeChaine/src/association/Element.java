/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association;

/**
 *
 * @author Andre Pereira
 */
public class Element<K, V> {

    private V value;
    private K key;
    private Element next;

    public Element(K key, V value) {
        this.value = value;
        this.key = key;
        this.next = null;
    }

    public Element(K key, V value, Element next) {
        this.value = value;
        this.key = key;
        this.next = next;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }
    
    
}
