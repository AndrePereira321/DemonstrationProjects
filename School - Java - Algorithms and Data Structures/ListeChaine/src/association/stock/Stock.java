/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association.stock;

import association.MapListe;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andre Pereira
 */
public class Stock {

    private final MapListe<Integer, Article> articles;

    public Stock() {
        articles = new MapListe();
    }

    public void majStock(LinkedList<Article> articles) {
        for (int i = 0; i < articles.size(); ++i) {
            this.articles.setElement(articles.get(i).getCode(), articles.get(i));
        }
    }

    public int getQt(int code) {
        return this.articles.getValue(code).getQuantité();
    }
    public String getlIB(int code) {
        return this.articles.getValue(code).getLibelle();
    }
    
    public List<Integer> getArticlesCode() {
        return null;
    }
}

