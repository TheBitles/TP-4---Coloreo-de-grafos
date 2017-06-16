package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("grafo.txt");
		Scanner scan = new Scanner(file);
		
		int ordenMat = scan.nextInt();
		int cantPares = scan.nextInt();
		MatrizSimetrica mat = new MatrizSimetrica(ordenMat);
		scan.nextDouble();
		scan.nextInt();
		scan.nextInt();
		
		for(int i = 0 ; i < cantPares ; i++) {
			int fila = scan.nextInt();
			int columna = scan.nextInt();
			mat.ponerElemento(1, mat.getIndice(fila, columna));
		}
		
		mat.mostrar();
	}

}
