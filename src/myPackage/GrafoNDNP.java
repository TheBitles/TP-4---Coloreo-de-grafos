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
		
		for(int i = 0 ; i < this.cantAristas ; i++) {
			fil = scan.nextInt();
			col = scan.nextInt();
			indice = this.grafo.getIndice(fil, col);
			this.grafo.ponerArista(indice);
		}
		
		scan.close();
	}

	public void coloreoSecuencialAleatorio() {
		
	}
	
	public void coloreoWelshPowell() {
		
	}
	
	public void coloreoMatula() {
		
	}

	public void mostrar() {
		this.grafo.mostrar();
	}
}
