package myPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class GrafoGenerator {

	public static void aleatorioConProbabilidad(int cantNodos, double probabilidad) throws IOException {
		ArrayList<ParDeNodos> array = new ArrayList<ParDeNodos>();
		Random rand = new Random();
		int cantMaximaAristas = ((cantNodos*(cantNodos - 1)) / 2);
		int cantAristas = 0;
		for(int i = 0 ; i < cantNodos - 1 ; i++) {
			for(int j = i + 1 ; j < cantNodos ; j++) {
				if(rand.nextFloat() < probabilidad) {
					ParDeNodos nodos = new ParDeNodos(i, j);
					array.add(new ParDeNodos(i, j));
					cantAristas++;
				}
			}
		}
		
		int gradoMaximo = 0;
		int gradoMinimo = Integer.MAX_VALUE;
		int max = 0;
		int min = 0;
		int nodoActual = array.get(0).getNodo1();
		for(int i = 0 ; i < array.size() ; i++) {
			if(array.get(i).getNodo1() == nodoActual) {
				max++;
				min++;
			}
			if(array.get(i).getNodo1() != nodoActual) {
				if(max > gradoMaximo)
					gradoMaximo = max;
				if(min < gradoMinimo)
					gradoMinimo = min;
				max = 0;
				min = 0;
				nodoActual = array.get(i).getNodo1();
			}
		}
	
		double porcentajeAdyacencia = (double)cantAristas / cantMaximaAristas;
		
		FileWriter file = new FileWriter("grafo_aleatorio_con_probabilidad.txt");
		BufferedWriter buffer = new BufferedWriter(file);
		System.out.println();
		buffer.write(String.valueOf(cantNodos));
		buffer.write(" ");
		buffer.write(String.valueOf(cantAristas));
		buffer.write(" ");
		buffer.write(String.format("%1.1f", porcentajeAdyacencia)); // Porcentaje de adyacencia
		buffer.write(" ");
		buffer.write(String.valueOf(gradoMaximo));
		buffer.write(" ");
		buffer.write(String.valueOf(gradoMinimo));
		buffer.newLine();
		
		for(int i = 0 ; i < array.size() ; i++) {
			buffer.write(String.valueOf(array.get(i).getNodo1()));
			buffer.write(" ");
			buffer.write(String.valueOf(array.get(i).getNodo2()));
			buffer.newLine();
		}
		
		buffer.close();
		
		if(gradoMinimo == 0)
			aleatorioConProbabilidad(cantNodos, probabilidad);
	}
	
	public static void aleatorioConPorcentajeAdyacencia(int cantNodos, float porcentaje) {
		
	}
}
