# L'outil Robotix

#### par Tobias Lepoutre, Giovanni Belval et Adam Mahiou

## Description du projet:

Robotix est un outil en ligne de commande permetant à l'utilisateur de \
contrôler ses robots, attribuer des tâches, surveiller l'utilisation des \
ressources, identifier les problèmes et acheter des composantes auprès de \
fournisseurs. Il offre aussi la possibilité de participer à diverses activités, \
suivre d'autres utilisateurs et recevoir des notifications pertinentes. 

## Détails techniques:

- Kit de Développement Logiciel (SDK) utilisé pour Java: \
Oracle OpenJDK version 20.0.2


- Bibliothèques utilisées: \
JUnit4 et JUnit5.8.1


- Applications requises: \
IntelliJ(.java), Maven(JAR) et Visual Paradigm(.vpp)

## Fonctionnalités & données initiales:

- Requêtes publiques
  - Récupérer la liste des utilisateurs
  - Rechercher un utilisateur
  - Voir le profil d'un utilisateur
  - Récupérer la liste des activités
  - Récupérer la liste des intérêts
  - Récupérer la liste des fournisseurs
  - Rechercher un fournisseur
  - Voir le profil d'un fournisseur
  - Rechercher une composante


- Fonctionnalités pour utilisateurs
  - Modifier son profil
  - Gérer sa flotte (robots et composantes)
  - Gérer ses suiveurs
  - Gérer ses activités
  - Gérer ses intérêts
  - Suivre un utilisateur
  - S'inscrire à une activité
  - Se souscrire à un intérêt
  - Voir l'état de ses robots
  - Voir les métriques
  - Voir ses notifications


- Fonctionnalités pour fournisseurs
  - Modifier son profil
  - Gérer ses composantes
  - Enregistrer une composante


- Description des données initiales:

  - 10 utilisateurs, dont au moins un utilisateur avec un suiveur
  - 5 fournisseurs
  - 2 robots chez chaque utilisateur
  - 5 composantes chez chaque fournisseur, dont au moins un par type
  - 20 actions
  - 10 tâches
  - 10 intérêts
  - 5 activités par utilisateurs, dont au moins une non débutée, une en \
  cours et une terminée


## Connexion:

- Informations de connexion initiales:
  - Utilisateurs: \
  nom, prénom, courriel, pseudo, mot de passe, numéro de téléphone \
  et nom d'entreprise (facultatif).
  - Fournisseur: \
  nom d'entreprise, prénom, courriel, mot de passe, numéro de \
  téléphone, localisation et capacité de production.
  

- Éxecuter l'application:
  -     Ouvrir le projet:
        Lancer IntelliJ IDEA et ouvrir le projet.

  -     Run le fichier JAR: Implémentation -> DM3 -> DM3-1.0-SNAPSHOT-jar-with-dependencies.jar
 
  - 


    Pour interagir avec Robotix, il faudra ensuite utiliser la ligne 
  de commande (Terminal) et la console pour executer des actions et \
  voir les sorties de l'application.
