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
public class Achat {

    private int codeArticle;
    private int quantité;

    public Achat(int codeArticle, int quantité) {
        this.codeArticle = codeArticle;
        this.quantité = quantité;
    }

    public int getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(int codeArticle) {
        this.codeArticle = codeArticle;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }
    
    
}
