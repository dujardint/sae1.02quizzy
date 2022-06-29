# Quizzy 
by Thomas Dujardin and Jessy Top

## Présentation de Quizzy

Logiciel proposant 3 mini-jeux pour les primaires :
-   un questionnaire sur le francais, 10 questions, 1 bonne reponse rapporte 1 point pour gagner avoir minimum 5/10
    On peut modifier, ajouter ou supprimer des questions pour cela modifier dans le fichier Question.csv puis dans le code java ligne 24 y inscire le nombre de questions

-   un jeu de maths ou il faut calculer les voisins de nombres. l'eleve choisit entre la multiplication, division, l'addition et la soustaction et se forme un 
    tableau ou chaque voisin doit par exemple se multiplier. l'éleve doit remplir le tableau pour gagner et si il repond faux plus de 5 fois, il perd.

-   un jeu de géographie ou il faut retrouver le pays à partir d'une image d'un pays en ASCII 
    On peut modifier, ajouter ou supprimer des questions pour cela modifier dans le fichier Pays.csv puis dans le code java ligne 25 y inscire le nombre de questions
    et ligne 70 ajouter

## Utilisation de Quizzy

Afin d'utiliser le projet, il doit être suffisant de taper les 
commandes suivantes:

./compile.sh
//compilation des fichiers présents dans 'src' et création des fichiers '.class' dans 'classes'

./run.sh Quizzy
//lancement du jeu
