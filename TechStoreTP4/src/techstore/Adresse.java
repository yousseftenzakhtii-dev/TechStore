package techstore;

/**
 * Adresse d'une personne : gouvernorat et code postal.
 */
public class Adresse {

    private String gouvernorat;
    private int codePostal;

    public Adresse(String gouvernorat, int codePostal) {
        this.gouvernorat = gouvernorat;
        this.codePostal = codePostal;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Adresse [gouvernorat=" + gouvernorat + ", codePostal=" + codePostal + "]";
    }
}
