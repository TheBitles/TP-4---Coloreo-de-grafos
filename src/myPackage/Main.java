package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//GrafoGenerator.regularConGrado(50, 5);
		GrafoNDNP grafo = new GrafoNDNP("grafo.in");
		//grafo.mostrar();
		//grafo.mostrarAristas();
		//grafo.mostrarNodos();
		
		grafo.coloreoSecuencial(10000);
		grafo.coloreoWelshPowell(10000);
		grafo.coloreoMatula(10000);
		
		//System.out.println(grafo.getGrafo().hayArista(grafo.getGrafo().getIndice(1, 0)));
		
		//ProbadorColoreo probador = new ProbadorColoreo("grafo.in", "coloreado.out");
		//System.out.println("ENTRADA: ");
		//probador.mostrarEntrada();
		//System.out.println("SALIDA: ");
		//probador.mostrarSalida();
		//System.out.println();
		/*
		if(probador.probar())
			System.out.println("TODO OK");
		else
			System.out.println("SALIDA NO VÁLIDA");
		*/
		//GrafoGenerator.aleatorioConProbabilidad(10, 0.9);
		//GrafoGenerator.aleatorioConPorcentajeAdyacencia(600, 0.9);
		//GrafoGenerator.regularConGrado(8, 3);
		//GrafoGenerator.regularConPorcentajeAdyacencia(1000, 0.50);
		//GrafoGenerator.nPartito(4, 5);
		//GrafoGenerator.nPartito(10, 3);
	}

}
