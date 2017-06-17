package myPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class GrafoGenerator {
	
	private static final int ALEATORIO_CON_PROBABILIDAD = 1;
	private static final int ALEATORIO_CON_PTAJE_ADYACENCIA = 2;

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
				grados.getNodo1(), grados.getNodo2(), ALEATORIO_CON_PROBABILIDAD);
	}

	public static void aleatorioConPorcentajeAdyacencia(int cantNodos, double porcentaje) throws IOException {
		ArrayList<ParDeNodos> array = new ArrayList<ParDeNodos>();
		ArrayList<RandomParDeNodos> random = new ArrayList<RandomParDeNodos>();
		Random rand = new Random();
		int cantMaximaAristas = ((cantNodos * (cantNodos - 1)) / 2);
		int cantAristas = 0;
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				random.add(new RandomParDeNodos(new ParDeNodos(i, j), rand.nextDouble()));
				cantAristas++;
			}
		}
		
		Collections.sort(random);
		
		for(int i = 0 ; i < cantNodos * porcentaje ; i++) {
			array.add(random.get(i).getNodos());
		}
		
		ParDeNodos grados = calcularGrado(array, cantNodos);
		String path = "grafo_aleatorio_con_porcentaje_adyacencia.txt";

		escribirGrafoEnArchivo(path, array, cantNodos, porcentaje, cantAristas, porcentaje,
				grados.getNodo1(), grados.getNodo2(), ALEATORIO_CON_PTAJE_ADYACENCIA);
	}

	private static ParDeNodos calcularGrado(ArrayList<ParDeNodos> array, int cantNodos) {
		int[] grados = new int[cantNodos];
		int gradoMaximo = 0;
		int gradoMinimo = Integer.MAX_VALUE;
		int max = 0;
		int min = 0;

		for (int i = 0; i < cantNodos; i++)
			grados[i] = 0;

		for (int i = 0; i < array.size(); i++) {
			grados[array.get(i).getNodo1()] += 1;
			grados[array.get(i).getNodo2()] += 1;
		}

		for (int i = 0; i < cantNodos; i++) {
			max = grados[i];
			min = grados[i];
			if (max > gradoMaximo)
				gradoMaximo = max;
			if (min < gradoMinimo && min > 0)
				gradoMinimo = min;
		}

		return new ParDeNodos(gradoMaximo, gradoMinimo);
	}

	private static void escribirGrafoEnArchivo(String path, ArrayList<ParDeNodos> array, int cantNodos,
			double probabilidad, int cantAristas, double porcentajeAdyacencia, int gradoMaximo, int gradoMinimo, int grafo)
			throws IOException {
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

		if (grafo == ALEATORIO_CON_PROBABILIDAD && gradoMinimo == 0)
			aleatorioConProbabilidad(cantNodos, probabilidad);
		if (grafo == ALEATORIO_CON_PTAJE_ADYACENCIA && gradoMinimo == 0)
			aleatorioConPorcentajeAdyacencia(cantNodos, probabilidad);
	}
	
	private static class RandomParDeNodos implements Comparable<RandomParDeNodos>{
		private ParDeNodos nodos;
		private double probabilidad;
		
		public RandomParDeNodos(ParDeNodos n, double prob) {
			this.nodos = n;
			this.probabilidad = prob;
		}

		public ParDeNodos getNodos() {
			return this.nodos;
		}
		
		public double getProbabilidad() {
			return this.probabilidad;
		}
		
		public void mostrar() {
			System.out.println(this.nodos.getNodo1() + " " + this.nodos.getNodo2() + " " + this.probabilidad);
		}

		@Override
		public int compareTo(RandomParDeNodos o) {
			if(this.probabilidad > o.probabilidad)
				return -1;
			if(this.probabilidad < o.probabilidad)
				return 1;
			return 0;
		}
	}
}
