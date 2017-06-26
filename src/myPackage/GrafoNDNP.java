package myPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GrafoNDNP {

	private MatrizSimetrica grafo;
	private int cantNodos;
	private int cantAristas;
	private double ptajeAdyacencia;
	private int gradoMax;
	private int gradoMin;
	
	private ArrayList<Nodo> nodos;
	private int[] nodosColoreados; // el indice coincide con el numero de nodo, y el valor que guarda es el color
	private int[] gradosNodos;

	public GrafoNDNP(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		int fil;
		int col;
		int indice;

		this.cantNodos = scan.nextInt();
		this.cantAristas = scan.nextInt();
		this.ptajeAdyacencia = scan.nextDouble();
		this.gradoMax = scan.nextInt();
		this.gradoMin = scan.nextInt();
		this.grafo = new MatrizSimetrica(this.cantNodos);
		nodos = new ArrayList<Nodo>();
		nodosColoreados = new int[this.cantNodos];
		gradosNodos = new int[this.cantNodos];
		
		for(int i = 0 ; i < this.cantNodos ; i++) {
			gradosNodos[i] = 0;
		}

		for (int i = 0; i < this.cantAristas; i++) {
			fil = scan.nextInt();
			col = scan.nextInt();
			gradosNodos[fil]++;
			gradosNodos[col]++;
			indice = this.grafo.getIndice(fil, col);
			this.grafo.ponerArista(indice);
		}

		for(int i = 0 ; i < this.cantNodos ; i++) {
			Nodo nodo = new Nodo(i);
			nodo.setGrado(this.gradosNodos[i]);
			this.nodos.add(nodo);
		}
			
		scan.close();
	}
	
	private void colorear() {
		
	}
	
	public void coloreoSecuencial(int corridas) {
		
	}
	
	public void coloreoWelshPowell(int corridas) {

	}

	public void coloreoMatula(int corridas) {

	}

	private void escribirSolucion(int[] coloreados, int cantColores) throws IOException {
		FileWriter file = new FileWriter("coloreado.out");
		BufferedWriter buffer = new BufferedWriter(file);
		
		buffer.write(String.valueOf(this.cantNodos));
		buffer.write(" ");
		buffer.write(String.valueOf(cantColores));
		buffer.write(" ");
		buffer.write(String.valueOf(this.cantAristas));
		buffer.write(" ");
		buffer.write(String.valueOf(NumberFormat.getInstance().format(this.ptajeAdyacencia)));
		buffer.write(" ");
		buffer.write(String.valueOf(this.gradoMax));
		buffer.write(" ");
		buffer.write(String.valueOf(this.gradoMin));
		buffer.newLine();
		
		for(int i = 0 ; i < coloreados.length ; i++) {
			buffer.write(String.valueOf(i));
			buffer.write(" ");
			buffer.write(String.valueOf(coloreados[i]));
			buffer.newLine();
		}
		
		buffer.close();
	}
	
	

	public void mostrar() {
		this.grafo.mostrar();
	}

	public void mostrarGrados() {
		for(int i = 0 ; i < this.cantNodos ; i++)
			System.out.println("n: " + i + " g: " + this.gradosNodos[i]);
	}
	
	// test
	public void mostrarAristas() {
		int n = this.cantNodos - 1;
		int fil = -1;
		int col = -1;
		int k = 0;
		
		do {
			for(int i = k ; i < n ; i++) {
				fil = k;
				col = i + 1;
				if(this.grafo.hayArista(this.grafo.getIndice(fil, col)))
					System.out.println(fil + " " + col);
			}
			k++;
		} while (this.grafo.getIndice(fil, col) < this.grafo.getDimension() - 1);
	}
}
