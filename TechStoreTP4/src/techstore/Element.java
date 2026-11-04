package techstore;

/**
 * Élément du stock : un article associé à sa quantité disponible.
 */
public class Element {

    private Article article;
    private long quantite;

    public Element(Article article, long quantite) {
        this.article = article;
        this.quantite = quantite;
    }

    /** Crée l'article à partir du nom et du prix, puis l'élément avec sa quantité. */
    public Element(String nom, double prix, long quantite) {
        this(new Article(nom, prix), quantite);
    }

    public Article getArticle() {
        return article;
    }

    public long getQuantite() {
        return quantite;
    }

    public String getReferenceArticle() {
        return article.getReference();
    }

    public String getNomArticle() {
        return article.getNom();
    }

    public double getPrixArticle() {
        return article.getPrix();
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setQuantite(long quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("la quantité ne peut pas être négative");
        }
        this.quantite = quantite;
    }

    /** Augmente la quantité. Retourne false si la valeur est invalide. */
    public boolean incrementerQuantite(long q) {
        if (q <= 0) {
            return false;
        }
        quantite += q;
        return true;
    }

    /** Diminue la quantité. Retourne false si la valeur est invalide ou supérieure au stock. */
    public boolean decrementerQuantite(long q) {
        if (q <= 0 || q > quantite) {
            return false;
        }
        quantite -= q;
        return true;
    }

    /** Crée un nouvel article puis l'affecte à l'élément avec la nouvelle quantité. */
    public void modifier(String nom, double prix, long quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("la quantité ne peut pas être négative");
        }
        this.article = new Article(nom, prix);
        this.quantite = quantite;
    }

    /** Vrai si la référence de l'article correspond à la chaîne donnée. */
    public boolean equals(String reference) {
        return article.getReference().equals(reference);
    }

    /**
     * Règle choisie : deux articles sont identiques si leur référence est la même.
     * (La référence contient l'identifiant unique, donc deux articles différents ne sont jamais égaux.)
     */
    public boolean equals(Article autre) {
        return autre != null && article.getReference().equals(autre.getReference());
    }

    @Override
    public String toString() {
        return "Element [article=" + article + ", quantite=" + quantite + "]";
    }
}
