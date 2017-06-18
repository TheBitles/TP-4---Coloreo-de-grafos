package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProbadorColoreo {

	private MatrizSimetrica entrada;
	private ArrayList<NodoColoreado> salida;
	
	public ProbadorColoreo(String pathIn, String pathOut) throws FileNotFoundException {
		File file = new File(pathIn);
		Scanner scan = new Scanner(file);
		salida = new ArrayList<NodoColoreado>();
		
		int cantAristas;
		int fil;
		int col;
		int indice;
		
		this.entrada = new MatrizSimetrica(scan.nextInt());
		cantAristas = scan.nextInt();
		scan.nextDouble();
		scan.nextInt();
		scan.nextInt();
		
		for(int i = 0 ; i < cantAristas ; i++) {
			fil = scan.nextInt();
			col = scan.nextInt();
			indice = this.entrada.getIndice(fil, col);
			//System.out.println(indice);
			System.out.println(this.entrada.getFila(indice) + " " + this.entrada.getColumna(indice));
			this.entrada.ponerArista(indice);
		}
		
		scan.close();
		file = new File(pathOut);
		scan = new Scanner(file);
		
		scan.nextInt();
		scan.nextInt();
		cantAristas = scan.nextInt();
		scan.nextDouble();
		scan.nextInt();
		scan.nextInt();
		
		for(int i = 0 ; i < cantAristas ; i++) {
			fil = scan.nextInt(); // nodo
			col = scan.nextInt(); // color
			salida.add(new NodoColoreado(fil, col));
		}
		
		scan.close();
	}
	
	public void mostrarEntrada() {
		for(int i = 0 ; i < this.entrada.getDimension() ; i++) {
			if(this.entrada.hayArista(i))
				System.out.println(this.entrada.getFila(i) + " " + this.entrada.getColumna(i));
		}
	}
	
	public void mostrarSalida() {
		for(int i = 0 ; i < this.salida.size() ; i++)
			System.out.println(this.salida.get(i).getNodo() + " " + this.salida.get(i).getColor());
	}
}
