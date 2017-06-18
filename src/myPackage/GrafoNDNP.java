package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GrafoNDNP {

	private MatrizSimetrica grafo;
	private int cantNodos;
	private int cantAristas;
	private double ptajeAdyacencia;
	private int gradoMax;
	private int gradoMin;

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

		for (int i = 0; i < this.cantAristas; i++) {
			fil = scan.nextInt();
			col = scan.nextInt();
			indice = this.grafo.getIndice(fil, col);
			this.grafo.ponerArista(indice);
		}

		scan.close();
	}

	public void coloreoSecuencialAleatorio() {
		// No tengo idea como carajo hacer si no tengo los métodos de getFila()
		// y getColumna(), la csm
	}

	public void coloreoWelshPowell() {

	}

	public void coloreoMatula() {

	}

	public void mostrar() {
		this.grafo.mostrar();
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
