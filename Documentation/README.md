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

Les détails du setup sont: 

// Assignations des utilisateurs crées
Utilisateur#Num = (entreprise, prénom, nom, email, pseudo, mot de passe, telephone)

User1 = (null, "Giovanni", "Belval"  , "gio@b.com", "belgio", "123456", "1234567890");
User2 = (null, "Tobias"  , "Lepoutre", "tob@g.com", "toblep", "123456", "1234567890");
User3 = (null, "Raphael" , "Ana"     , "rap@b.com", "rahana", "123456", "1234567890");
User4 = (null, "Eloise"  , "Lepoutre", "elo@g.com", "elolep", "123456", "1234567890");
User5 = (null, "Mario"   , "Amir"    , "mar@g.com", "marami", "123456", "1234567890");

// Autres profils utilisateur créés mais non utilisé.
User(null, "Vincent" , "Pascal"  , "vin@b.com", "vinpas", "123456", "1234567890");
User(null, "Kael"    , "Biaritz" , "kae@g.com", "kaebia", "123456", "1234567890");
User(null, "Partale" , "Brother" , "par@b.com", "parbro", "123456", "1234567890");
User(null, "Rémis"   , "Amir"    , "rem@g.com", "remami", "123456", "1234567890");
User(null, "Auguste" , "Amir"    , "aug@b.com", "augami", "123456", "1234567890");


//Fournisseur(entreprise, prenom, email, mot de passe, telephone, adresse, capacite)

Seller1 = ("Google"  , "Theo", "t@gmail.com", "123456","1234567890","1234 rue de Paris",100);
Seller2 = ("Google"  , "Victor", "v@gmail.com", "123456","1234567890","1234 rue de genève",100);
Seller3 = ("Facebook", "francis", "ter@gmail.com", "123456","1234567890","1234 rue de Paris",100);
Seller4 = ("Netflix" , "edouard", "vit@gmail.com", "123456","1234567890","1234 rue de Paris",100);
Seller5 = ("Google"  , "valentin", "ol@gmail.com", "123456","1234567890","1234 rue de Lyon",100);
Seller6 = ("Google"  , "grégory", "at@gmail.com", "123456","1234567890","1234 rue de Montreal",100);

// Activités créées par utilisateur:
Utilisateur.createActivity(type, nom d'interet, nom d'activité, date de début, date de fin, recompense)

user1.createActivity("creation", "robotique"  , "atelier"      , LocalDate.of(2023,2,1) , LocalDate.of(2023,2,6) , 200);
user2.createActivity("creation", "robotique"  , "programation" , LocalDate.of(2023,2,1) , LocalDate.of(2023,2,5) , 150);
user2.createActivity("education", "automobile", "course"       , LocalDate.of(2023,2,5) , LocalDate.of(2023,2,8) , 150);
user3.createActivity("creation", "robotique"  , "photography"  , LocalDate.of(2023,2,4) , LocalDate.of(2023,2,3) , 150);
user3.createActivity("creation", "algebre"    , "aviation"     , LocalDate.of(2023,5,2) , LocalDate.of(2023,6,1) , 200);
user4.createActivity("education", "automobile", "sprint"       , LocalDate.of(2023,6,1) , LocalDate.of(2023,6,5) , 150);
user4.createActivity("creation", "robotique"  , "devoirs"      , LocalDate.of(2023,5,1) , LocalDate.of(2023,7,5) , 200);
user5.createActivity("creation", "informatif" , "seminaire"    , LocalDate.of(2023,5,12), LocalDate.of(2023,5,15), 150);
user5.createActivity("education", "automobile", "formule1"     , LocalDate.of(2023,8,1), LocalDate.of(2023 ,8,5) , 150);
user1.createActivity("creation", "robotique"  , "assemblage"   , LocalDate.of(2023,6,1), LocalDate.of(2023 ,6,2) , 150);
user1.createActivity("creation", "robotique"  , "spaceX"       , LocalDate.of(2023,2,1), LocalDate.of(2023 ,6,1) , 200);
user1.createActivity("education", "automobile", "karting"      , LocalDate.of(2023,6,1), LocalDate.of(2023 ,6,5) , 150);

// Composantes appartenant à chaque fournisseur:
Fournisseur.createActivity(type, nom d'interet, nom d'activité, date de début, date de fin, recompense)


seller1.createComposante("HautParleur", "BooseX100" , "", 30);
seller1.createComposante("bras", "mechaArm" , "", 25);
seller1.createComposante("bras", "mechaArm" , "", 25);
seller1.createComposante("ecran", "Iscreen" , "", 50);
seller1.createComposante("bras", "mechaArm" , "", 25);

seller2.createComposante("CPU", "RX900" , "the fastest CPU", 150);
seller2.createComposante("CPU", "FX1000" , "", 95);
seller2.createComposante("helice", "Ihelice" , "", 110);
seller2.createComposante("CPU", "RX900" , "the fastest CPU", 150);
seller2.createComposante("CPU", "FX1000" , "", 95);
seller2.createComposante("helice", "Ihelice" , "", 110);

seller3.createComposante("micro", "MRX18" , "slowest HP", 10);
seller3.createComposante("hautParleur", "HP360" , "", 95);
seller3.createComposante("helice", "Ihelice" , "", 110);
seller3.createComposante("CPU", "RX901" , "the fastest CPU for IA", 250);
seller3.createComposante("ecran", "Iscreen" , "", 95);
seller3.createComposante("helice", "Ihelice" , "", 110);

seller4.createComposante("CPU", "FX1000" , "", 95);
seller4.createComposante("helice", "Ihelice" , "", 110);
seller4.createComposante("bras", "mechaArm" , "", 25);
seller4.createComposante("ecran", "Iscreen" , "", 50);
seller4.createComposante("hautParleur", "HP360" , "", 95);


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
 

    Ensuite il suffit de suivre les instruction. Une fois l'inscription complété, \
    le code doit être Run à nouveau pour se connecter. C'est l'équivalent d'un reload.
