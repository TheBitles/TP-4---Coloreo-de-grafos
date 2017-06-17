package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
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
		
		//GrafoGenerator.aleatorioConProbabilidad(10, 0.9);
		//GrafoGenerator.aleatorioConPorcentajeAdyacencia(10, 0.5);
		
		//GrafoGenerator.regularConGrado(8, 3);
		//GrafoGenerator.regularConPorcentajeAdyacencia(6, 0.1);
		GrafoGenerator.nPartito(8, 4);
	}

}
