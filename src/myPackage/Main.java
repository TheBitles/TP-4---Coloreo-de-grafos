package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("grafo.txt");
		Scanner scan = new Scanner(file);
		
		int orden = scan.nextInt();
		int[][] matAux = new int[orden][orden];
		
		for(int i = 0 ; i < orden ; i++) {
			for(int j = 0 ; j < orden ; j++)
				matAux[i][j] = (scan.nextInt());
		}

		MatrizSimetrica mat = new MatrizSimetrica(matAux, orden);
		mat.mostrar();
	}

}
