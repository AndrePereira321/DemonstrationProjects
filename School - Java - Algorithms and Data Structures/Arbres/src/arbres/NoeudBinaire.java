package arbres;

/**
 *
 * @author Andre Pereira
 */
public class NoeudBinaire<T> {

    private T valeur;
    private NoeudBinaire<T> filsGauche;
    private NoeudBinaire<T> filsDroit;

    // *** constructeur ***
    public NoeudBinaire(T valeur) {
        this.valeur = valeur;
        this.filsDroit = null;
        this.filsGauche = null;
    }

    // *** getteurs ***
    public T getValeur() {
        return this.valeur;
    }

    public NoeudBinaire<T> getGauche() {
        return this.filsGauche;
    }

    public NoeudBinaire<T> getDroit() {
        return this.filsDroit;
    }

    // *** setteurs ***   
    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    public void setGauche(NoeudBinaire<T> gauche) {
        this.filsGauche = gauche;
    }

    public void setDroit(NoeudBinaire<T> droit) {
        this.filsDroit = droit;
    }
}
