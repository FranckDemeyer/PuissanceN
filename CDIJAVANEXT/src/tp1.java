import java.util.Scanner;
public class tp1 {
	public static void main(String[] args) {
		int nombre;
		Scanner clavier = new Scanner(System.in);
		System.out.println("Entrez un nombre entier positif, n�gatif ou nul :");
		nombre = clavier.nextInt();
		clavier.close();
		if (nombre > 0) System.out.println(nombre + " est positif.");
		else if (nombre < 0) System.out.println(nombre + " est n�gatif.");
		else System.out.println(nombre + " est nul.");
	}
}
