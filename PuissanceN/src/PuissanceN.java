import java.io.*;

public class PuissanceN {

	// Jeu similaire à puissance 4 avec un nombre de pions n à aligner sur un plateau de x lignes * y colonnes
	
	private static int col=0;
	private static int ligne=0;
	private static int pion=0;
	private static char[][]damier;
	static int x=0, lignepion=0;
	static int nbcoupj1 = 0, nbcoupj2 = 0;
	static boolean estjoueur1,gagne=false;

	public static void main(String[] args) {
		
		//ouverture du fichier accueil et demande des parametres
		
		System.out.println("Bienvenue à Puissance N");
		
		char rejouer;
		do {
			
			System.out.println("Merci de choisir le nombre de colonnes du tableau");
			
			//gestion des exceptions et nouvelle demande des parametres en cas de besoin grace a une modification du fichier Lire.java
			
			while (!integ()){System.out.println("Format incorrect, merci de choisir à nouveau le nombre de colonnes du tableau");};
			
			while(x<3){
				
				System.out.println("vous devez choisir un nombre de colonne au moins egal à 3");
				System.out.println("merci de rentrer un autre nombre");
				while (!integ()){System.out.println("Format incorrect, merci de choisir à nouveau le nombre de colonne du tableau");};
	
			}
			setCol(x);
			
			int pionmax;
			pionmax = getCol();
			
			System.out.println("Merci de choisir le nombre de lignes du tableau");
			
			while (!integ()){System.out.println("Format incorrect, merci de choisir à nouveau le nombre de lignes du tableau");};
			while(x<3){
				
				System.out.println("vous devez choisir un nombre de ligne au moins egal à 3");
				System.out.println("merci de rentrer un autre nombre");
				while (!integ()){System.out.println("Format incorrect, merci de choisir à nouveau le nombre de ligne du tableau");};
	
			}
			setLigne(x);
			
			if(getCol()>getLigne()){
				
				pionmax=getLigne();
			
			}
			
			System.out.println("Merci de choisir le nombre de pions à aligner pour gagner la partie");
			while (!integ()){System.out.println("Format incorrect, merci de choisir à nouveau le nombre de pions à aligner pour gagner");};
			pion =x;
				
			while (pion > pionmax && pion < 3) {
					
					System.out.println("Vous devez choisir un nombre de pion à aligner plus petit que " + pionmax + " et plus au moins egal à 3");
					System.out.println("merci de rentrer un autre nombre");
					while (!integ()){System.out.println("Format incorrect, merci de choisir à nouveau le nombre de pion à aligner pour gagner");};
					pion = x;
					
			};
			
			
			paramDamier(getCol(), getLigne());
			
			printDamier(getCol(), getLigne());
			
			estjoueur1 = true;
			
			jouerPion();
			
			if (gagne){
				if(estjoueur1){System.out.println("Bravo Joueur 1 vous avez gagné la partie !");}
				else{System.out.println("Bravo Joueur 2 vous avez gagné la partie !");}
			}
			else{System.out.println("La partie est finie, il n'y a pas de gagnant.");}
			
			System.out.println("Voulez vous rejouer une partie ? (o/n)");
			rejouer = charac();
			
			if (rejouer != 'O' && rejouer != 'o' && rejouer != 'N' && rejouer != 'n'){
				
				System.out.println("Vous devez repondre par o ou n, voulez vous rejouer une partie ?");
				rejouer = charac();
				
			}
			
		} while (rejouer == 'O' || rejouer == 'o');
	}

	public static int getCol() {
		return col;
	}

	public static void setCol(int col) {
		PuissanceN.col = col;
	}

	public static int getLigne() {
		return ligne;
	}

	public static void setLigne(int ligne) {
		PuissanceN.ligne = ligne;
	}

	public static int getPion() {
		return pion;
	}

	public static void setPion(int pion) {
		PuissanceN.pion = pion;
	}

	private static void jouerPion(){
		String nomjoueur;
		int colonnejouee;
		
		if(estjoueur1){nomjoueur = "Joueur 1";}
		else{nomjoueur= "Joueur 2";}
		
		System.out.println("A vous de jouer " + nomjoueur + " indiquez la colonne ou vous voulez jouer :");
		while (!integ() && x> col){System.out.println("Merci de choisir un nombre correspondant à une colonne du tableau de jeu" + (char) 13 + "indiquez la colonne ou vous voulez jouer :");}
		colonnejouee=x;
		while(!colonneValide(x)){
			
			System.out.println("la colonne jouée est pleine, merci de jouer une autre colonne");
			while (!integ() && x> col){System.out.println("Merci de choisir un nombre correspondant à une colonne du tableau de jeu" + (char) 13 + "indiquez la colonne ou vous voulez jouer :");}
			colonnejouee=x;
		}
		
		printDamier(getCol(), getLigne());
		if((estjoueur1 && nbcoupj1>=pion)||(!estjoueur1 && nbcoupj2>=pion)){gagne=coupGagnant(colonnejouee, lignepion);}
		if(!gagne && (nbcoupj1+nbcoupj2)<(col*ligne)){
			
			estjoueur1 = !estjoueur1;
			jouerPion();
			
		}
		
	}
	
	private static boolean coupGagnant(int colonnejouee, int lignepion){
		boolean horizpos=true, horizneg=true, diagpos=true, diagneg=true, verti=true;
		int i = 1;
		while(horizpos && i <= pion ){
			if (colonnejouee+pion > col+1){horizpos=false;}
			else{	
				if(damier[colonnejouee][lignepion]==damier[colonnejouee+i][lignepion]){i++;}
				else{horizpos=false;}
			}
		}
		
		while(horizneg && i < pion ){
			if (colonnejouee-pion<0){horizneg=false;}
			else{	
				if(damier[colonnejouee][lignepion]==damier[colonnejouee-i][lignepion]){i++;}
				else{horizneg=false;}
			}
		}
		
		while(diagpos && i < pion ){
			if (colonnejouee+pion>col+1||lignepion-pion<0){diagpos=false;}
			else{	
				if(damier[colonnejouee][lignepion]==damier[colonnejouee+i][lignepion-i]){i++;}
				else{diagpos=false;}
			}
		}
		
		while(diagneg && i < pion ){
			if (colonnejouee-pion<0||lignepion-pion<0){diagneg=false;}
			else{	
				if(damier[colonnejouee][lignepion]==damier[colonnejouee-i][lignepion-i]){i++;}
				else{diagneg=false;}
			}
		}
		
		while(verti && i < pion ){
			if (lignepion-pion<0){verti=false;}
			else{	
				if(damier[colonnejouee][lignepion]==damier[colonnejouee][lignepion-i]){i++;}
				else{verti=false;}
			}
		}
		
		
		return (horizpos || horizneg || diagpos || diagneg || verti);
	}
	
	private static boolean colonneValide(int colonne){
		boolean temp = false;
		int i = 1;
		char lepion;
		
		if(estjoueur1){lepion='X';}
		else{lepion='O';}
		
		do{
			
			if(damier[x][i]== ' '){
				
				damier[x][i]=lepion;
				lignepion=i;
				temp = true;
				if(estjoueur1){nbcoupj1++;}
				else{nbcoupj2++;}
				
			}
			i++;
		}while(!temp && i<ligne+1);
		
		return temp;
	}
	
	private static void paramDamier(int Col, int Ligne) {
		damier = new char[Col+2][Ligne+1];
		for (int i = 0; i < Ligne + 1; i++) {
			damier[0][i] = '|';
			damier[col+1][i] = '|';
		}
		
		for (int i = 1; i < col+1; i++) {
			damier[i][0]= (char) (i+48);
		}
		
		for (int i = 1; i < Col+1; i++) {
			for (int j = 1; j < Ligne+1; j++) {
				damier[i][j]=' ';
			}
		}
	}

	private static void printDamier(int Col, int Ligne) {
		
		for (int i = ligne+1; i > 0; i--) {
			for (int j = 0; j < Col+1; j++) {
				System.out.print(damier[j][i-1]);
			}
			System.out.println(damier[Col+1][i-1]);
		}
		
	}
	
	public static char[][] getDamier() {
		return damier;
	}

	public static void setDamier(char[][] damier) {
		PuissanceN.damier = damier;
	}

	public static String S() // Lire un String
	{
	String tmp = "";
	char C='\0';
	try {
	         while ((C=(char) System.in.read()) !='\n')
	         {
	          if (C != '\r')  tmp = tmp+C;
	 
	         }
	   }
	 catch (IOException e)
	        {
	          System.out.println("Erreur de frappe");
	          System.exit(0);
	        }
	 return tmp;
	}
	
	 public static boolean integ()  // Lire un entier
	 {
	 	 	try {
				 x=Integer.parseInt(S());
	  	 	}
	 	 	catch (NumberFormatException e) {
	          return false;
	    }	
		  return true ;
	 }

	 public  static char charac()  // Lire un caractere
	 {
	  String tmp=S();
	  if (tmp.length()==0)
		  return '\n';
	  else 
			{
			return tmp.charAt(0);
			}
	 }
}
