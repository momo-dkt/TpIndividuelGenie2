# TP 1 - Jeu de Poker (Fiche de réponses)

  - Auteur : Mohamed Diakhate (DIAM16069901)
  - Date de remise : 26/02/2021
  - Estimation du temps de travail passé sur le projet : _xx_ heures.

## Réponses aux questions  
 
Les tailles de réponses sont données à titre indicatif, mais il est clair qu'une question sur quinze (15) points attends plus de matière dans la réponse qu'une question sur cinq (5). Ne faites pas un roman, soyez direct et allez droit au but dans votre argumentation.
 
### Question 1: Évolution du code légataire
  -Pour pouvoir accumuler le nombre de parties gagnées par joueur , il faudrait créer une classe Joueur et y ajouter un attribut victoire.
  -Pour pouvoir arbitrer des parties à plus de deux joueurs , le programme devrait demander le nombre de joueurs au début de chaque partie.
  -Pour que l'as devienne la carte la plus forte , il faudrait changer l'ordre de la table de comparaison , en modifiant la  valeur de l'as de telle sorte qu'elle soit la plus grande .

### Question 2: Analyse des défauts du code légataire
  -Tout le code du programme est centralisé dans la classe main
  -Les cartes des joueurs sont représentées sur des tableaux de caractères .
  -L'utilisation   d'une variable statique  dont son accès est difficile à contrôler .
  -Ce code légataire n'est pas une application orientée objet ,car dans un jeu de poker il devrait y avoir minimalement une classe Carte , Joueur et Arbitre.

### Question 3: Justification des choix de conception
   1)J'ai opté pour la création de deux énumérations : Couleur qui contient les couleurs possibles qu'une carte pourrait avoir et Valeur qui contient toutes
    les valeurs possibles d'une carte. J'ai aussi créé une classe Carte qui est composée d'une Couleur et d'une Valeur, une classe Mainjouer qui est composée 
    essentiellement d'un arraylist de Carte et une classe Joueur qui est composée d'une MainJoueur . J'ai aussi créé une classe Arbitre qui s'occupe de tout ce
    qui est en rapport avec la règle du jeu et son application , une classe TableDeJeu qui initialise les données du jeu et une interface RegleDuJeu qui contient des 
   méthodes en rapport avec la régle du jeu  qui seront implémentées par la classe Arbitre.
   2)
    -Avec la création de ces différentes classes énoncées à la première question , la répartition des tâches  de l'application objet devient facile et naturelle
     , car chaque classe aura une et une seule tâche.
    -La conception actuelle du projet respecte la substitution de Liskov  .On peut prendre l'exemple de l'interface RegleDuJeu qui contient des méthodes qui ne
     sont pas implémentées et dont toutes les classes qui héritent d'elle vont avoir leur propre implémentation de leur règle du jeu.Donc on ne pourra pas faire des
     choses différentes dans les sous-classes ne pouvant pas se faire dans la classe mère.
    -Dans ma conception orientée objet, chaque classe a une  connaissance limitée des autres, car ayant peu ou pas d'accès.Seule la classe Arbitre a une 
     connaissance des attributs des joueurs , que ce soit la main du joueur , sa combinaison ou la valeur maximale de la main , car un jeu de poker ne pourrait 
     pas exister sans la connaissance des attributs des joueurs par l'arbitre.
   3)Je pense que ma conception n'a pas de faiblesses.   
### Question 4: Évolution du code objet
     Avec la classe Joueur que j'ai créé, l'ajout des parties gagnées deviendra facile ,il faudra juste ajouter un attribut nbrVictoire dans la classe Joueur.
     L'implémentation du multijoueur a pu être fait en demandant à l'utilisateur le nombre de joueurs pour chaque partie , puis la création des mains des joueurs 
    , leur vérification avant de passer à l'arbitrage . Ces différentes approches d'implémentation respectent le principe de conception Ouvert/Fermé.

## Auto-évaluation (optionnelle)

Vous êtes libre de faire l'exercice de vous auto-évaluer en remplissant la grille d'évaluation du projet ci-dessous.

| Élement         | Critère d'évaluation                       | Note  |
| :---:           | :---                                       | :---: |
|  _Questions_    | (#1) Évolution du code légataire           |5/5    |
|                 | (#2) Analyse des défauts du code légataire |10/10   |
|                 | (#3) Justification des choix de conception |15/15   |
|                 | (#4) Évolution du code objet               |5 /5    |
|  _Modèles_      | Justesse & Pertinence de la conception     |15/15   |
|                 | Cohérence inter-modèles                    |5/5    |
|                 | Respect des principes de conception        | 15/15   |
|  _Code_         | Qualité du code Java et du dépôt Git       |10/10   |
|                 | Cohérence du code avec les modèles         |10/10   | 
|                 | Qualité des tests                          |10/10   | 
| **Note Finale** | Questions + Modèles + Code                 |100 /100  | 

_Cette auto-évaluation permet au correcteur de vous donner une rétro-action plus personnalisée en pointant les critères sur lesquels vous vous sur-évaluez et ceux sur lesquels au contraire vous vous sous-évaluez._
