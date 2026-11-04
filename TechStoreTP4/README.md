# TP4 Java : Classes, objets et gestion de stock (TechStore)

Projet Java réalisé par **Youssef Tenzakhti** dans le cadre du TP4.

## Structure

```
TechStoreTP4/
├── src/
│   └── techstore/
│       ├── Adresse.java   # Activité 1.1 : gouvernorat et code postal
│       ├── Personne.java  # Activité 1.2 : CIN, nom, prénom, adresse
│       ├── Test.java      # Activité 1.3 (personnes) et 2.4 (menu du gérant)
│       ├── Article.java   # Activité 2.1 : id unique, nom, référence, prix
│       ├── Element.java   # Activité 2.2 : article + quantité
│       └── Stock.java     # Activité 2.3 : collection d'éléments et ses méthodes
├── output_partie1.txt     # Sortie réelle de la partie 1
├── output_scenario.txt    # Sortie réelle du scénario de test du gérant
├── scenario.txt           # Entrées du scénario de test (une valeur par ligne)
└── README.md
```

## Compilation et exécution

Avec Eclipse : créer le projet `TechStoreTP4`, le package `techstore` dans `src`, puis exécuter `Test.java`.

En ligne de commande (JDK 17 ou plus récent) :

```bash
javac -encoding UTF-8 -d bin src/techstore/*.java
java -cp bin techstore.Test                  # mode interactif
java -cp bin techstore.Test < scenario.txt   # scénario de test du TP
```

## Règles de conception

- **Identifiant** : `Article` utilise un compteur statique ; chaque nouvel article reçoit un id unique.
- **Référence** : on prend les lettres du nom (espaces ignorés) situées aux indices 0, 5, 10, 15... puis on ajoute l'id.
  Exemple : `Micro Casque 588 Filaire` (id 1) donne `MCeie1`.
- **Modification** : `modifier()` crée un nouvel objet `Article` (comme demandé dans le sujet). L'article reçoit donc un nouvel id et une nouvelle référence.
- **Égalité d'articles** : `equals(Article)` considère deux articles identiques si leur référence est la même.
- **Ajout** : si la référence existe déjà dans le stock, la quantité est augmentée au lieu de créer un doublon.
- **Quantités** : une quantité négative ou nulle est refusée ; une diminution supérieure au stock est refusée.

## Remarque

Le sujet ne donne ni le prix ni la quantité de départ du « Micro Casque 588 Filaire » (49.0 TND, 5 unités ici). Ces valeurs sont modifiables dans `Test.creerStockInitial()`.
