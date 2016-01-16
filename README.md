# HMIN306-Projet4
Analyse statique du code JAVA d'une application  et modélisation sous formes de graphes

## Membres
- Manuel Chataigner
- Jimmy Lopez
- Morgane Vidal

## Travail attendu 
- [ ] Analyse du code source java en utilisant l'arbre syntaxique abstrait (AST)
-  [ ] Génération de deux types de graphes
  - [ ] Graphe d'appel au sein d'une classe
    - chaque méthode est un nœud
    - un appel entre les méthodes M1 et M2 : M1--> M2 est un arc dirigé de M1 vers M2 
  - [ ] Graphe d'appel de l'application 
    - un nœud est une classe
    - un arc de la classe C1  vers la classe C2 = au moins une méthode de C1 appelle une méthode de C2
    - poids d'un arc C1 vers C2 = nombre de méthodes de C1 qui appellent des méthodes dans C2. 
- [ ] Optionnel (Bonus) : visualisation de ces graphes 

## Dates de rendu
- livrable à rendre le lundi 18 janvier 12h00 avec : 
  - un document décrivant les étapes, les solutions proposées,les résultats obtenus et éventuellement, un bilan et une conclusion par rapport aux éléments précédents
  -  un fichier .zip contenant l'ensemble des fichiers source commentés 
- démonstration le mercredi 20 janvier
