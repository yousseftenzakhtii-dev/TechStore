package techstore;

import java.util.ArrayList;

/**
 * Stock du magasin : collection des éléments (article + quantité).
 * Les méthodes de recherche, modification et suppression utilisent la référence de l'article.
 */
public class Stock {

    private ArrayList<Element> articleList;

    /** Stock vide. */
    public Stock() {
        this.articleList = new ArrayList<>();
    }

    /** Stock à partir d'une liste d'éléments. */
    public Stock(ArrayList<Element> articleList) {
        this.articleList = new ArrayList<>(articleList);
    }

    /** Stock à partir de trois listes : noms, prix et quantités (même taille). */
    public Stock(ArrayList<String> noms, ArrayList<Double> prix, ArrayList<Long> quantites) {
        this();
        if (noms.size() != prix.size() || noms.size() != quantites.size()) {
            throw new IllegalArgumentException("les trois listes doivent avoir la même taille");
        }
        for (int i = 0; i < noms.size(); i++) {
            ajouterArticle(noms.get(i), prix.get(i), quantites.get(i));
        }
    }

    public ArrayList<Element> getArticleList() {
        return articleList;
    }

    public int getArticleNb() {
        return articleList.size();
    }

    public void setArticleList(ArrayList<Element> articleList) {
        this.articleList = articleList;
    }

    /** Ajoute un article avec sa quantité. Si la référence existe déjà, la quantité est augmentée. */
    public Element ajouterArticle(Article article, long quantite) {
        if (quantite <= 0) {
            throw new IllegalArgumentException("la quantité doit être positive");
        }
        Element existant = trouverArticle(article.getReference());
        if (existant != null) {
            existant.incrementerQuantite(quantite);
            return existant;
        }
        Element e = new Element(article, quantite);
        articleList.add(e);
        return e;
    }

    /** Crée un article à partir du nom et du prix, puis l'ajoute au stock. */
    public Element ajouterArticle(String nom, double prix, long quantite) {
        return ajouterArticle(new Article(nom, prix), quantite);
    }

    /** Recherche un élément à partir de sa référence. Retourne null s'il n'existe pas. */
    public Element trouverArticle(String reference) {
        for (Element e : articleList) {
            if (e.equals(reference)) {
                return e;
            }
        }
        return null;
    }

    /** Modifie le nom, le prix et la quantité de l'article ayant la référence donnée. */
    public boolean modifierArticle(String reference, String nom, double prix, long quantite) {
        Element e = trouverArticle(reference);
        if (e == null) {
            return false;
        }
        e.modifier(nom, prix, quantite);
        return true;
    }

    /** Supprime l'élément ayant la référence donnée. */
    public boolean supprimerArticle(String reference) {
        return articleList.removeIf(e -> e.equals(reference));
    }

    public boolean incrementerQuantiteArticle(String reference, long q) {
        Element e = trouverArticle(reference);
        return e != null && e.incrementerQuantite(q);
    }

    public boolean decrementerQuantiteArticle(String reference, long q) {
        Element e = trouverArticle(reference);
        return e != null && e.decrementerQuantite(q);
    }

    @Override
    public String toString() {
        if (articleList.isEmpty()) {
            return "Le stock est vide.";
        }
        StringBuilder sb = new StringBuilder();
        for (Element e : articleList) {
            sb.append(e).append("\n");
        }
        return sb.toString().trim();
    }
}
