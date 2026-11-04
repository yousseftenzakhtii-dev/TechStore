package techstore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Programme de test de TechStore.
 * Partie 1 : création et manipulation des personnes et adresses.
 * Partie 2 : gestion du stock par le gérant (menu interactif).
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        partie1();

        Stock stock = creerStockInitial();
        System.out.println("\nStock initial :");
        System.out.println(stock);

        menuGerant(stock, sc);
    }

    // ------------------------------------------------------------------
    // Partie 1 : classes et objets
    // ------------------------------------------------------------------
    private static void partie1() {
        System.out.println("===== PARTIE 1 : PERSONNES ET ADRESSES =====");

        // 1. Afficher les adresses créées
        Adresse a1 = new Adresse("Monastir", 5000);
        Adresse a2 = new Adresse("Ben Arous", 1133);
        Adresse a3 = new Adresse("Ben Arous", 1145);
        System.out.println("L'adresse a1 : " + a1);
        System.out.println("L'adresse a2 : " + a2);
        System.out.println("L'adresse a3 : " + a3);

        // 2. Créer deux personnes avec les deux constructeurs
        Personne p1 = new Personne("01111111", "Kbayer", "Jasser", "Jendouba", 8160);
        Personne p2 = new Personne("02222222", "Smida", "Ghayth", a1);
        System.out.println("\nLa personne p1 : " + p1);
        System.out.println("La personne p2 : " + p2);

        // 3. Afficher les informations des personnes
        System.out.println("\nInformations initiales :");
        System.out.println(p1);
        System.out.println(p2);

        // 4 à 6. Modifier nom, prénom et adresse
        p1.setNom("Karray");
        p1.setPrenom("Yassine");
        p2.setAdresse(a3);

        // 7. Afficher de nouveau pour vérifier les modifications
        System.out.println("\nInformations après modification :");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println();
    }

    // ------------------------------------------------------------------
    // Partie 2 : gestion du stock
    // ------------------------------------------------------------------
    private static Stock creerStockInitial() {
        // Prix et quantité de la casque : valeurs de départ choisies pour le test (non fournies par le sujet)
        ArrayList<String> noms = new ArrayList<>(Arrays.asList("Micro Casque 588 Filaire"));
        ArrayList<Double> prix = new ArrayList<>(Arrays.asList(49.0));
        ArrayList<Long> quantites = new ArrayList<>(Arrays.asList(5L));
        return new Stock(noms, prix, quantites);
    }

    private static void afficherMenu() {
        System.out.println("\n========== GESTION DU STOCK TECHSTORE ==========");
        System.out.println("0 - Ajouter un article");
        System.out.println("1 - Augmenter la quantité d'un article");
        System.out.println("2 - Modifier un article");
        System.out.println("3 - Supprimer un article");
        System.out.println("4 - Consulter le stock");
        System.out.println("5 - Quitter");
        System.out.print("Votre choix : ");
    }

    private static void menuGerant(Stock stock, Scanner sc) {
        while (true) {
            afficherMenu();
            if (!sc.hasNextLine()) {
                break;
            }
            String choix = sc.nextLine().trim();
            try {
                switch (choix) {
                    case "0": {
                        String nom = demander(sc, "Nom de l'article : ");
                        double prix = Double.parseDouble(demander(sc, "Prix (TND) : "));
                        long q = Long.parseLong(demander(sc, "Quantité : "));
                        if (prix < 0) {
                            System.out.println("Erreur : le prix ne peut pas être négatif.");
                            break;
                        }
                        Element e = stock.ajouterArticle(nom, prix, q);
                        System.out.println("Article ajouté. Référence : " + e.getReferenceArticle()
                                + ", quantité en stock : " + e.getQuantite());
                        break;
                    }
                    case "1": {
                        String ref = demander(sc, "Référence de l'article : ");
                        long q = Long.parseLong(demander(sc, "Quantité à ajouter : "));
                        if (stock.trouverArticle(ref) == null) {
                            System.out.println("Aucun article avec la référence " + ref + ".");
                        } else if (stock.incrementerQuantiteArticle(ref, q)) {
                            System.out.println("Quantité augmentée de " + q + " pour " + ref + ".");
                        } else {
                            System.out.println("Quantité invalide : elle doit être strictement positive.");
                        }
                        break;
                    }
                    case "2": {
                        String ref = demander(sc, "Référence de l'article à modifier : ");
                        Element cible = stock.trouverArticle(ref);
                        if (cible == null) {
                            System.out.println("Aucun article avec la référence " + ref + ".");
                            break;
                        }
                        String nom = demander(sc, "Nouveau nom : ");
                        double prix = Double.parseDouble(demander(sc, "Nouveau prix (TND) : "));
                        long q = Long.parseLong(demander(sc, "Nouvelle quantité : "));
                        if (prix < 0) {
                            System.out.println("Erreur : le prix ne peut pas être négatif.");
                            break;
                        }
                        stock.modifierArticle(ref, nom, prix, q);
                        System.out.println("Article modifié. Nouvelle référence : "
                                + cible.getReferenceArticle() + ".");
                        break;
                    }
                    case "3": {
                        String ref = demander(sc, "Référence de l'article à supprimer : ");
                        if (stock.supprimerArticle(ref)) {
                            System.out.println("Article " + ref + " supprimé du stock.");
                        } else {
                            System.out.println("Aucun article avec la référence " + ref + ".");
                        }
                        break;
                    }
                    case "4": {
                        System.out.println("\n--- Stock (" + stock.getArticleNb() + " article(s)) ---");
                        System.out.println(stock);
                        break;
                    }
                    case "5": {
                        System.out.println("Au revoir.");
                        return;
                    }
                    default:
                        System.out.println("Choix invalide, veuillez recommencer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : valeur numérique invalide.");
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage() + ".");
            }
        }
    }

    private static String demander(Scanner sc, String question) {
        System.out.print(question);
        return sc.hasNextLine() ? sc.nextLine().trim() : "";
    }
}
