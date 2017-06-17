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
		int cantMaximaAristas = ((cantNodos * (cantNodos - 1)) / 2);
		int cantAristas = 0;
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				if (rand.nextFloat() < probabilidad) {
					array.add(new ParDeNodos(i, j));
					cantAristas++;
				}
			}
		}

		ParDeNodos grados = calcularGrado(array, cantNodos);
		double porcentajeAdyacencia = (double) cantAristas / cantMaximaAristas;
		String path = "grafo_aleatorio_con_probabilidad.txt";

		escribirGrafoEnArchivo(path, array, cantNodos, probabilidad, cantAristas, porcentajeAdyacencia,
				grados.getNodo1(), grados.getNodo2());
	}

	public static void aleatorioConPorcentajeAdyacencia(int cantNodos, double porcentaje) {

	}
	
	private static ParDeNodos calcularGrado(ArrayList<ParDeNodos> array, int cantNodos) {
		int[] grados = new int[cantNodos];
		int gradoMaximo = 0;
		int gradoMinimo = Integer.MAX_VALUE;
		int max = 0;
		int min = 0;
		
		for(int i = 0 ; i < cantNodos ; i++)
			grados[i] = 0;
		
		for(int i = 0 ; i < array.size() ; i++) {
			grados[array.get(i).getNodo1()] += 1;
			grados[array.get(i).getNodo2()] += 1;
		}
		
		for(int i = 0 ; i < cantNodos ; i++) {
			max = grados[i];
			min = grados[i];
			if(max > gradoMaximo)
				gradoMaximo = max;
			if(min < gradoMinimo && min > 0)
				gradoMinimo = min;
		}
		
		return new ParDeNodos(gradoMaximo, gradoMinimo);
	}

	private static void escribirGrafoEnArchivo(String path, ArrayList<ParDeNodos> array, int cantNodos,
			double probabilidad, int cantAristas, double porcentajeAdyacencia, int gradoMaximo, int gradoMinimo) throws IOException {
		FileWriter file = new FileWriter(path);
		BufferedWriter buffer = new BufferedWriter(file);
		System.out.println();
		buffer.write(String.valueOf(cantNodos));
		buffer.write(" ");
		buffer.write(String.valueOf(cantAristas));
		buffer.write(" ");
		buffer.write(String.format("%1.1f", porcentajeAdyacencia));
																	
		buffer.write(" ");
		buffer.write(String.valueOf(gradoMaximo));
		buffer.write(" ");
		buffer.write(String.valueOf(gradoMinimo));
		buffer.newLine();

		for (int i = 0; i < array.size(); i++) {
			buffer.write(String.valueOf(array.get(i).getNodo1()));
			buffer.write(" ");
			buffer.write(String.valueOf(array.get(i).getNodo2()));
			buffer.newLine();
		}

		buffer.close();

		if (gradoMinimo == 0)
			aleatorioConProbabilidad(cantNodos, probabilidad);
	}
}
