import extensions.CSVFile; //pr lire fichier csv
import extensions.File; //pr lire fichier texte

class Quizzy extends Program{
    /*  Jeu de la SAE1.02 en ijava: THOMAS DUJARDIN ET JESSY TOP 
        3 matieres : Francais, Géographie, Mathématique.
            - Pour le jeu du francais : 10 questions 1 bonne reponse rapporte 1 point 

            - Pour les maths, l'eleve choix entre la multiplication, division, l'addition et la soustaction et se forme un tableau ou chaque voisin doit par exemple 
              se multiplier et donc la fin l'éleve doit remplir le tableau pour gagner et si il repond une reponses fausse plus de 5 fois, il perd.
                -faire fonction --> "void algo pour que l'eleve chosise sa matiere et la difficulté " , "selon la matiere chosie, renvoie une fonction qui definie l'operateur selon le choix choisis",
                                    "fonction qui créer un tableau +/- grands selon la difficulté choisie", 
                                    "fonction qui remplie le tableau une case sur 2 avec un nombre aléatoire compris entre 1 et 10 "
                                    "fonction qui selon le signe multiplie, soustrer ect.. chaque voisin entre eux", 
                                    "fonction vie, où si l'eleve repond une mauvaise reponse il a une vie en moin, et si il tombe a 0 la partie s'arrete et l'eleve a perdu",
                                    "fonction gagné et si tableau est remplie " 
                                    "puis message de fin"

        - geo : un pays qui s'affiche en ascii et l'éleve doit trouver soit le pays, quel continent ou sa capital
                        créer classe reponse : 3 string  capital , ville, continent
    */

    final int NOMBREDEQUESTION=10;   //CHANGER LA VALEUR POUR AUGMENTER OU DIMINUER LE NOMBRE DE QUESTION
    final int NOMBREDEPAYS=6;
    String choix;
    String prenom;   
    int nombreDePoints=0;  
    int delai=1000;

    void initialisationFrancais(){
        clearScreen();  //affiche un ecran vide pour le jeu
        println("Bienvenue dans le jeu du francais ! ");
        delay(delai);
        println("Tu vas etre teste sur " + NOMBREDEQUESTION + " questions sur le theme du francais.\nIl peut y avoir de la grammaire, de la conjugaison, de l'ortographe ou encore du vocabulaire.");
        println();
        delay(delai);
        println("Pour que tes reponses soit acceptes, il faut que tu les ecrives exclusivement en minuscules ! (eh oui ! on est dur !)");
        delay(delai);
        println("Alors petit conseil : reste toujours en minuscule !");
        delay(delai); 
        prenom();   //appelle fonction qui demande prenom du joueur
        println("Commencons !");
        delay(delai);
        println("Tu as " + NOMBREDEQUESTION + " questions. C'est parti !");
        delay(delai);
        println();
    }

    void initialisationMaths(){
        clearScreen();
        println("Bienvenue dans le jeu des maths !!");
        prenom();
        println("Bienvenue a toi, " + prenom +".");
        delay(delai);
        println("Chaque bonne réponse te rapporteras 1 point.");
        delay(delai);
        println("Le jeu fonctionne en 2 étapes, 1er étape tu devras remplir les lignes qui sont a moitié remplies, en additionnant le voisin de droite et le voisin de gauche du nombre caché.");
        println("La 2éme étape est similaire à la 1ère sauf que cette fois-ci tu devras trouver les lignes entierement caché, pour se faire tu devras additionner les voisins verticaux du nombre caché (le voisin du haut et le voisin du bas).");
        delay(delai);
        println("Pour information, les tableaux sont crées aléatoirement !");
        delay(delai);
        println("Appuie sur entrer dès que tu es prêt!");
        readString();
        println("Je te souhaite bonne chance!.");
        delay(delai);
        clearScreen();
    }

    void initialisationGeo(){
        clearScreen();  //affiche un ecran vide pour le jeu
        println("Bienvenue dans le jeu de géo ! ");
        delay(delai);
        println("Tu vas devoir retrouver quel pays se cache sous le dessin qui va apparaitre.");
        println();
        delay(delai);
        println("Pour que tes réponses soit acceptées, il faut que tu les écrives exclusivement en minuscule ! (eh oui ! on est dur !)");
        delay(delai);
        println("Alors petit conseil : reste toujours en minuscule !");
        delay(delai);
        prenom();   //appelle fonction qui demande prenom du joueur
        println("Commençons !");
        delay(delai);
        println("Tu as " + NOMBREDEPAYS + " pays à retrouver. C'est parti !");
        delay(delai);
        println();
    }

    void algorithm(){
        clearScreen();          //affiche un ecran vide
        boolean choixValide=false;
        do{
            choixTheme();           //appel la fonction pour choisir le theme
            if(equals(choix, "1")){ 
    //jeu francais
                choixValide=true;
                initialisationFrancais();
                CSVFile fichier = loadCSV("Question.csv");  //charge le fichier de question
                int numQuestion=1;   //definit le numero de la question en cours (commence à 1 jusqu'a NOMBREDEQUESTION)

                for(int idxL=1; idxL<NOMBREDEQUESTION+1; idxL++){     //boucle qui fait toutes les questions
                    int ColonneZero=0;
                    boolean res=false;
                    questions(numQuestion, getCell(fichier, idxL, ColonneZero), getCell(fichier, idxL, ColonneZero+1), res, prenom);  //get cell(fichier ligne, colonne)
                    //                      question ligne1 colone0                   reponse ligne1  colonne1
                    numQuestion++;
                    if(res){ //si res=true (bonne reponse), ajoute 1 au nombre de point
                        nombreDePoints++; 
                    }
                    affichageCompteur(nombreDePoints);
                    delay(delai);
                }
                
                fin(NOMBREDEQUESTION);  //permet d'afficher le score en fonction du nombre de question
            }

            else if(equals(choix, "2")){
    //jeu maths
                choixValide=true;  //permet d'eviter de redemander quel jeu lancer
                initialisationMaths();

                boolean fini = false;
                int nombre_de_vie = 5;  //nombre de points à été placé en commun pour l'affichage du score
                String [][] tableau = new String[3][3];
                String [][] newtab = init(tableau);
                boolean victoire= false;
                while(victoire != true){

                for(int lignes =0;lignes<length(tableau,1); lignes= lignes+2){  // int lignes =0   +2       //horizontal
                    for(int colonnes =1;colonnes< length(tableau, 2); colonnes = colonnes +2){   //int colonnes =1    +2
                            if(nombre_de_vie ==0){
                                //lignes =length(tableau,1);
                                //colonnes = length(tableau,2);
                                delay(1000);
                                break;

                            }
                        int coordonneL = lignes+1;
                        int coordonneC= colonnes+1;
                        boolean saisie = false;
                        while(saisie != true){
                            afficher(newtab);
                            println("Quel est le numéro caché de la ligne "+coordonneL +" et de la colonnes "+coordonneC + " ?"); 
                            String resultat_donne = readString();
                            while(verifieChaine(resultat_donne) == false){
                                println("reponds un nombre");
                                resultat_donne = readString();
                            }
                            int nombre_a_comparer = stringToInt(resultat_donne);
                            if(nombre_a_comparer != calculeVoisinSurLaLigne(newtab, lignes, colonnes)){
                                clearScreen();
                                println( "mauvaise reponse essaie encore !, tu as perdu une vie !");
                                nombre_de_vie--;
                                println("ton nombre de vie est de "+nombre_de_vie+" vies");
                            }else{
                                clearScreen();
                                println("bonne reponse, vous gagnez 1 point!");
                                nombreDePoints++;
                                newtab[lignes][colonnes]= resultat_donne;
                                saisie = true;
                            }
                            if(nombre_de_vie ==0){
                                //lignes =length(tableau,1);
                                //colonnes = length(tableau,2);
                                println("ton nombre de vie a atteind 0 tu as perdu");
                                delay(1000);
                                break;

                            }
                        }
                    }
                }

                for(int lignes =1;lignes<length(tableau,1); lignes= lignes+2){  //vertical
                    for(int colonnes =0;colonnes< length(tableau, 2); colonnes++){
                            if(nombre_de_vie ==0){
                                //lignes =length(tableau,1);
                                //colonnes = length(tableau,2);
                                delay(1000);
                                break;

                            }
                        int coordonneL = lignes+1;
                        int coordonneC= colonnes+1;
                        boolean saisie = false;
                        while(saisie != true){
                            afficher(newtab);
                            println("Quel est le numéro caché de la ligne "+coordonneL +" et de la colonnes "+coordonneC + " ?"); 
                            String resultat_donne = readString();
                            while(equals(resultat_donne, "") || 47> (int)charAt(resultat_donne, 0) || 58< (int)charAt(resultat_donne, 0) ){
                                println("reponds un nombre");
                                resultat_donne = readString();
                            }
                            int nombre_a_comparer = stringToInt(resultat_donne);
                            if(nombre_a_comparer != calculeVoisinSurLaColone(newtab, lignes, colonnes)){
                                clearScreen();
                                println( "mauvaise reponse essaie encore !, tu as perdu une vie !");
                                nombre_de_vie--;
                                println("ton nombre de vie est de "+nombre_de_vie+" vies");
                            }else{
                                clearScreen();
                                println("bonne reponse, tu gagnes 1 point!");
                                nombreDePoints++;
                                newtab[lignes][colonnes]= resultat_donne;
                                saisie = true;
                            }
                            if(nombre_de_vie ==0){
                                //lignes =length(tableau,1);
                                //colonnes = length(tableau,2);
                                println("ton nombre de vie a atteind 0 tu as perdu");
                                delay(1000);
                                break;

                            }
                        }
                    }
                }
                victoire = true;
                finmath(nombreDePoints);
            }

            }
            else if(equals(choix, "3")){
    //jeu geo
                choixValide=true;
                initialisationGeo();
                CSVFile fichierPays = loadCSV("Pays.csv");  //charge le fichier de question
                int numQuestion=1;   //definit le numero de la question en cours (commence à 1 jusqu'a NOMBREDEQUESTION)

                String[] NomPays = new String [NOMBREDEPAYS]; //ranger les noms des fichiers dans un tableau dans l'ordre
                for(int idxL=1; idxL<NOMBREDEPAYS+1; idxL++){
                    NomPays[idxL-1]=getCell(fichierPays, idxL, 2);
                }

                for(int idxL=1; idxL<NOMBREDEPAYS+1; idxL++){     //boucle qui fait toutes les questions
                    int ColonneZero=0;
                        boolean res=false;
                        FichierTXT(NomPays[idxL-1]);  //affichage du pays en dessin ASCII
                        questions(numQuestion, getCell(fichierPays, idxL, ColonneZero), getCell(fichierPays, idxL, ColonneZero+1), res, prenom);  //get cell(fichier ligne, colonne) question ligne1 colone0 et reponse ligne1  colonne1
                        numQuestion++;
                        if(res){ //si res=true (bonne reponse), ajoute 1 au nombre de point
                            nombreDePoints++; 
                        }
                        affichageCompteur(nombreDePoints);
                        delay(delai);
                }
                fin(NOMBREDEPAYS);  //permet d'afficher le score en fonction du nombre de pays
            }
            else{
                println("je n'ai pas compris ta reponse ce choix n'exise pas réessaye");
            }

        }while(!choixValide);
    }

    void choixTheme(){
        println("=======================================");
        println("Bienvenue dans le jeu du test de culture !");
        println("=======================================");
        println("Sur quelle thème veux-tu te tester ? : pour le francais entre '1', pour les maths entre '2' ou pour la géo entre '3'.");
        choix=readString();
    }

    //fonction qui demande le prenom du joueur a chaque mini jeu
    void prenom(){
        println("Commencons par les bases. Comment t'appelles-tu ?");
        prenom= readString();

        String commencer=" ";
        while(!equals(commencer, "oui")){   //tant que joueur n'est pas pret on repose la question
            println(prenom + " es-tu prêt ?");
            commencer=readString();
        }
    }

    //fonction qui pose la question, ajoute 1 point si bonne reponse et affiche la reponse si mauvaise reponse
    void questions(int numQuestion, String question, String reponse, boolean res, String prenom){
        res=false;
        println(numQuestion + "e question !");
        println(question);
        println("Ecrit ci-dessous ta reponse :");
        String saisie=readString();
        println();
        if (equals(saisie, reponse)){
            println("Bonne reponse " + prenom + " ! tu gagnes 1 point.");
            nombreDePoints++; 
            res=true;
        }
        else{
            println("Mauvaise reponse.");
            println("La bonne reponse était : " + reponse);
            delay(delai);
        }
    }

    //fonction qui affiche le contenu du fichier avec en paramètre le nom du fichier
    void FichierTXT(String NomTXT){    
        File namePays = newFile(NomTXT + ".txt");  //charge le fichier correspondant au nom du fichier
        while(ready(namePays)){  //on s'arrête dès qu'on lit une ligne null (fin du fichier)
            println(readLine(namePays));    //affichage du contenu de la ligne suivante
        }
    }

    //fonction qui affiche la progression du joueur
    void affichageCompteur(int nombreDePoints){
        println("tu es a " + nombreDePoints + " points.");
        println();
    }


//////////////////////////////
////////FONCTIONS MATHS

    void afficher(String[][] tableau){
        for(int lignes =0;lignes<length(tableau,1); lignes++){
            for(int colonnes =0;colonnes< length(tableau, 2); colonnes++){
                print(tableau[lignes][colonnes]);
            }
            println(" ");
        }
    }

    String[][] init(String[][] tableau){
        String[][] tab = tableau;
        for(int lignes =0;lignes<length(tableau,1); lignes++){
            for(int colonnes =0;colonnes< length(tableau, 2); colonnes++){
                if(lignes%2 == 1 || colonnes%2 == 1 ){
                    tab[lignes][colonnes]="x";
                }else{
                    double nbrand = random()*10;
                    int intrand = (int) nbrand;
                    tab[lignes][colonnes]= intrand+"";
                }
            }
            println(" ");
        }
        return tab;
        
    }
    /*String [][] difficulte(int numerodifficulte){
       
    }*/

    int calculeVoisinSurLaLigne(String[][] tableau, int lignes, int colonnes){
        int result;
        int voisinAvant;
        int voisinsApres;
        voisinAvant = stringToInt(tableau[lignes][colonnes-1]);
        voisinsApres = stringToInt(tableau[lignes][colonnes+1]);
        result = voisinAvant + voisinsApres;
        return result;
    }

    void testcalculeVoisinSurLaLigne() {
        // Initialiser un monde exemple
        String[][] tableau = new String[][]{{"3", "_", "1" },
                                            {"_", "_", "_" },
                                            {"5", "_", "8" },};
        assertEquals(4, calculeVoisinSurLaLigne(tableau, 0, 1));
        assertEquals(13, calculeVoisinSurLaLigne(tableau, 2, 1));
    }
    int calculeVoisinSurLaColone(String[][] tableau, int lignes, int colonnes){
        int result;
        int voisinAvant;
        int voisinsApres;
        voisinAvant = stringToInt(tableau[lignes-1][colonnes]);
        voisinsApres = stringToInt(tableau[lignes+1][colonnes]);
        result = voisinAvant + voisinsApres;
        return result;
    }

    void testcalculeVoisinSurLaColone() {
        // Initialiser un monde exemple
        String[][] tableau = new String[][]{{"3", "_", "1" },
                                            {"_", "_", "_" },
                                            {"5", "_", "8" },};
        assertEquals(8, calculeVoisinSurLaColone(tableau, 1, 0));
        assertEquals(9, calculeVoisinSurLaColone(tableau, 1, 2));
    }
    boolean verifieChaine(String chaine){
        boolean result= false;
        for(int i=0; i<length(chaine); i++){
            if( equals(chaine, "") || 47> (int)charAt(chaine, i) || 58< (int)charAt(chaine, i)){
                result = false;
            }else{
                result= true;
            }
        }
        return result;
    }


//////////////////////////////
////////FONCTIONS MATHS


    //fonction de cloture de chaque mini jeu en fonction du nombre de question et nombre de point
    void fin(int nbDeQuestion){
        println();  //saut de ligne
        println("Voila tu as repondu a toutes mes questions");
        delay(delai);
        print("Calcule du score en cours");
        delay(500);
        print(".");
        delay(500);
        print(".");
        delay(500);
        println(".");
        delay(500);
        println("Tu as obtenu " + nombreDePoints + "/" + nbDeQuestion);
        delay(delai);
        if(nombreDePoints>=(nbDeQuestion/2)){
            //afficher message bravo en txt
            FichierTXT("bravo");
        }
        else{
            FichierTXT("insuffisant");
        }
        delay(delai);
        println("Merci d'avoir joue.\nAu revoir " + prenom + " !");
        println();        
    }
        void finmath(int nbDeQuestion){
        println();  //saut de ligne
        println("Voila tu as repondu a toutes mes questions");
        delay(delai);
        print("Calcule du score en cours");
        delay(500);
        print(".");
        delay(500);
        print(".");
        delay(500);
        println(".");
        delay(500);
        println("Tu as obtenu " + nombreDePoints + "/" + 5);
        delay(delai);
        if(nombreDePoints==5){
            //afficher message bravo en txt
            FichierTXT("bravo");
        }
        else{
            FichierTXT("insuffisant");
        }
        delay(delai);
        println("Merci d'avoir joue.\nAu revoir " + prenom + " !");
        println();        
    }
}