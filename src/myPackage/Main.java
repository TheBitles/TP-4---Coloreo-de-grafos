package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		//GrafoGenerator.aleatorioConProbabilidad(10, 0.9);
		//GrafoGenerator.aleatorioConPorcentajeAdyacencia(600, 0.9);
		//GrafoGenerator.regularConGrado(8, 3);
		//GrafoGenerator.regularConPorcentajeAdyacencia(1000, 0.50);
		//GrafoGenerator.nPartito(4, 5);
		//GrafoGenerator.nPartito(10, 3);
		
		GrafoNDNP grafo = new GrafoNDNP("grafo.in");

		//grafo.coloreoSecuencial(10000);
		//grafo.coloreoWelshPowell(10000);
		//grafo.coloreoMatula(10000);
		
		ProbadorColoreo probador = new ProbadorColoreo("grafo.in", "coloreado.out");
		
		//System.out.println("ENTRADA: ");
		//probador.mostrarEntrada();
		//System.out.println("SALIDA: ");
		//probador.mostrarSalida();
		//System.out.println();
		
		if(probador.probar())
			System.out.println("TODO OK");
		else
			System.out.println("SALIDA NO VÁLIDA");
	}
}
