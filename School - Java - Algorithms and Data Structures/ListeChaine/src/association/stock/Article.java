/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association.stock;

/**
 *
 * @author Andre Pereira
 */
public class Article {

    private int code;
    private String libelle;
    private int prix;
    private int quantité;

    public Article(int code, String libelle, int prix, int quantité) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
        this.quantité = quantité;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantité() {
        return quantité;
    }

    public void addQuantité(int quantité) {
        this.quantité += quantité;
    }
    
    

    
}
