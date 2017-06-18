package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		GrafoNDNP grafo = new GrafoNDNP("grafo.in");
		grafo.mostrar();
		/*
		ProbadorColoreo probador = new ProbadorColoreo("grafo.in", "coloreado.out");
		System.out.println("ENTRADA: ");
		probador.mostrarEntrada();
		System.out.println("SALIDA: ");
		probador.mostrarSalida();
		System.out.println();
		if(probador.probar())
			System.out.println("TODO OK");
		else
			System.out.println("SALIDA NO VÁLIDA");
			*/
		//GrafoGenerator.aleatorioConProbabilidad(10, 0.9);
		//GrafoGenerator.aleatorioConPorcentajeAdyacencia(10, 0.5);
		//GrafoGenerator.regularConGrado(8, 3);
		//GrafoGenerator.regularConPorcentajeAdyacencia(6, 0.1);
		//GrafoGenerator.nPartito(4, 5);
	}

}
