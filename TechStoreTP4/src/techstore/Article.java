package techstore;

/**
 * Article commercial du magasin.
 * L'identifiant est unique et attribué automatiquement.
 * La référence est calculée à partir du nom, puis complétée par l'identifiant.
 */
public class Article {

    private static int compteur = 0;   // génère les identifiants uniques

    private final int id;
    private String nom;
    private String reference;
    private double prix;               // en dinars tunisiens (TND)

    public Article(String nom, double prix) {
        this.id = ++compteur;
        this.prix = prix;
        setNom(nom);                   // met aussi à jour la référence
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getReference() {
        return reference;
    }

    public double getPrix() {
        return prix;
    }

    /** Modifie le nom et recalcule la référence. */
    public void setNom(String nom) {
        this.nom = nom;
        this.reference = genererReference();
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    /** Modifie le nom et met à jour la référence. */
    public void modifier(String nom) {
        setNom(nom);
    }

    /** Modifie le nom et le prix, puis met à jour la référence. */
    public void modifier(String nom, double prix) {
        setNom(nom);
        setPrix(prix);
    }

    /**
     * Référence : lettres du nom (espaces ignorés) situées à un indice divisible par 5
     * (0, 5, 10...), suivies de l'identifiant de l'article.
     * Exemple : "Apple iPad" -> "Ai" + id.
     */
    private String genererReference() {
        String sansEspaces = nom.replace(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sansEspaces.length(); i += 5) {
            char c = sansEspaces.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(c);
            }
        }
        return sb.append(id).toString();
    }

    @Override
    public String toString() {
        return "id=" + id + ", nom=" + nom + ", reference=" + reference + ", prix=" + prix;
    }
}
