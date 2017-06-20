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
	
	public int getCantNodos() {
		return cantNodos;
	}

	public void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}

	public int getCantAristas() {
		return cantAristas;
	}

	public void setCantAristas(int cantAristas) {
		this.cantAristas = cantAristas;
	}

	public double getPtajeAdyacencia() {
		return ptajeAdyacencia;
	}

	public void setPtajeAdyacencia(double ptajeAdyacencia) {
		this.ptajeAdyacencia = ptajeAdyacencia;
	}

	public int getGradoMax() {
		return gradoMax;
	}

	public void setGradoMax(int gradoMax) {
		this.gradoMax = gradoMax;
	}

	public int getGradoMin() {
		return gradoMin;
	}

	public void setGradoMin(int gradoMin) {
		this.gradoMin = gradoMin;
	}

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

	public void coloreoSecuencialAleatorio() throws IOException {
		ArrayList<Integer> nodosSinColorear = new ArrayList<Integer>();
		ArrayList<NodoColoreado> nodosColoreados = new ArrayList<NodoColoreado>();
		Random random = new Random();
		int rand;
		int color = 1;
		int colorAdyacente = -1;
		int colorAux = -1;
		int nodoActual = -1;
		int nodoColoreado = -1;
		int colorMax = 1;
		int k = 0;
		
		for(int i = 0 ; i < this.cantNodos ; i++)
			nodosSinColorear.add(i);
		
		rand = random.nextInt((this.cantNodos - k) - 0) + 0;
		nodoActual = nodosSinColorear.get(rand);
		nodosColoreados.add(new NodoColoreado(nodoActual, color));
		nodosSinColorear.remove(rand);
		k++;
		
		for(int i = 0 ; i < this.cantNodos - 1 ; i++) {
			rand = random.nextInt((this.cantNodos - k) - 0) + 0; // indice
			nodoActual = nodosSinColorear.get(rand);
			for(int j = 0 ; j < nodosColoreados.size() ; j++) {
				nodoColoreado = nodosColoreados.get(j).getColor();
				colorAdyacente = nodosColoreados.get(j).getColor();
				if(this.grafo.hayArista(this.grafo.getIndice(nodoActual, nodoColoreado)) && colorAdyacente != colorAux) {
					color++;
					colorAux = colorAdyacente;
					colorMax++;
				}
			}
			nodosColoreados.add(new NodoColoreado(nodoActual, color));
			nodosSinColorear.remove(rand);
			color = 1;
			k++;
		}
		
		escribirSolucion(nodosColoreados, colorMax);
	}

	private void escribirSolucion(ArrayList<NodoColoreado> array, int cantColores) throws IOException {
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
		
		for(int i = 0 ; i < array.size() ; i++) {
			buffer.write(String.valueOf(array.get(i).getNodo()));
			buffer.write(" ");
			buffer.write(String.valueOf(array.get(i).getColor()));
			buffer.newLine();
		}
		
		buffer.close();
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
