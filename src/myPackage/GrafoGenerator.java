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

		escribirGrafoEnArchivo(path, array, cantNodos, cantAristas, porcentajeAdyacencia, grados.getNodo1(),
				grados.getNodo2());
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

		for (int i = 0; i < cantNodos * porcentaje; i++) {
			array.add(random.get(i).getNodos());
		}

		ParDeNodos grados = calcularGrado(array, cantNodos);
		String path = "grafo_aleatorio_con_porcentaje_adyacencia.txt";

		escribirGrafoEnArchivo(path, array, cantNodos, cantAristas, porcentaje, grados.getNodo1(), grados.getNodo2());
	}

	public static void regularConGrado(int cantNodos, int grado) throws IOException {
		ArrayList<ParDeNodos> array = new ArrayList<ParDeNodos>();
		int cantAristas = 0;
		int gradoActual = 2;
		int cantMaximaAristas = (cantNodos * (cantNodos - 1)) / 2;
		int salto = 0;
		int j = 0;
		
		if (grado % 2 != 0 && cantNodos % 2 != 0) {
			System.out.println("No se puede generar un grafo regular de grado impar con N impar");
			return;
		}

		if (grado % 2 != 0) { // si el grado es impar, genero la cruz
			for (int i = 0; i < cantNodos / 2; i++) {
				array.add(new ParDeNodos(i, i + cantNodos / 2));
				cantAristas++;
			}
		}

		if (grado > 1) {
			while (gradoActual <= grado) {
				salto = gradoActual / 2;
				j = 0;
				for (int i = 0; i < cantNodos; i++) {
					if(i + salto < cantNodos) 
						array.add(new ParDeNodos(i, i + salto));
					else {
						array.add(new ParDeNodos(i, j));
						j++;
					}
					cantAristas++;
				}
				gradoActual += 2;
			}
		}

		ParDeNodos grados = calcularGrado(array, cantNodos);
		double porcentajeAdyacencia = (double) cantAristas / cantMaximaAristas;
		String path = "grafo_regular.txt";

		escribirGrafoEnArchivo(path, array, cantNodos, cantAristas, porcentajeAdyacencia, grados.getNodo1(),
				grados.getNodo2());
	}
	
	public static void regularConPorcentajeAdyacencia(int cantNodos, double porcentaje) throws IOException {
		int cantMaximaAristas = (cantNodos*(cantNodos - 1)) / 2;
		int grado = (int) Math.ceil(((porcentaje * cantMaximaAristas) / (cantNodos / 2)));
		double minimo = (double) (cantNodos/2) / cantMaximaAristas;
		
		if(porcentaje < minimo) {
			System.out.println("El porcentaje de adyacencia ingresado es demasiado bajo para generar un grafo regular (el minimo es: " + String.format("%1.3f", minimo) + ")");
			System.exit(1);
		}
		if(porcentaje > 1) {
			System.out.println("El porcentaje de adyacencia ingresado es superior al 100%");
			System.exit(1);
		}
		
		regularConGrado(cantNodos, grado);
	}
	
	public static void nPartito(int cantNodos, int cantConjuntos) throws IOException {
		ArrayList<ParDeNodos> array = new ArrayList<ParDeNodos>();
		int cantMaximaAristas = (cantNodos*(cantNodos - 1)) / 2;
		int cantAristas = 0;
		int noAgregar = 0;
		int salto;
		boolean noAgregarFlag = true;
		
		if(cantConjuntos > cantNodos) {
			System.out.println("La cantidad de conjuntos no puede ser mayor a la cantidad de nodos");
			System.exit(1);
		}
		
		salto = cantConjuntos;

		for(int i = 0 ; i < cantNodos - 1 ; i++) {
			for(int j = i + 1 ; j < cantNodos ; j++) {
				if(noAgregarFlag) {
					noAgregar = i + salto;
					noAgregarFlag = false;
				}
				if(j != noAgregar) {
					array.add(new ParDeNodos(i, j));
					cantAristas++;
				}
				else {
					salto+=cantConjuntos;
					noAgregarFlag = true;
				}
			}
			salto = cantConjuntos;
			noAgregarFlag = true;
		}
		
		ParDeNodos grados = calcularGrado(array, cantNodos);
		double porcentajeAdyacencia = (double) cantAristas / cantMaximaAristas;
		String path = "grafo_" + String.valueOf(cantConjuntos) + "_partito.txt";
		
		escribirGrafoEnArchivo(path, array, cantNodos, cantAristas, porcentajeAdyacencia, grados.getNodo1(),
				grados.getNodo2());
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

	private static void escribirGrafoEnArchivo(String path, ArrayList<ParDeNodos> array, int cantNodos, int cantAristas,
			double porcentajeAdyacencia, int gradoMaximo, int gradoMinimo) throws IOException {
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

		if (gradoMinimo == 0) {
			System.out.println("El grafo generado tiene 0 como grado mínimo, genere un nuevo grafo...");
			System.exit(0);
		}
			
	}

	private static class RandomParDeNodos implements Comparable<RandomParDeNodos> {
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
			if (this.probabilidad > o.probabilidad)
				return -1;
			if (this.probabilidad < o.probabilidad)
				return 1;
			return 0;
		}
	}
	
}
