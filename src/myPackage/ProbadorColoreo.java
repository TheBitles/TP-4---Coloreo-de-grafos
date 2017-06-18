package myPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProbadorColoreo {

	private ArrayList<ParDeNodos> entrada;
	private ArrayList<NodoColoreado> salida;
	
	public ProbadorColoreo(String pathIn, String pathOut) throws FileNotFoundException {
		File file = new File(pathIn);
		Scanner scan = new Scanner(file);
		this.entrada = new ArrayList<ParDeNodos>();
		this.salida = new ArrayList<NodoColoreado>();
		
		int cantAristas;
		int fil;
		int col;
		int indice;

		scan.nextInt();
		cantAristas = scan.nextInt();
		scan.nextDouble();
		scan.nextInt();
		scan.nextInt();
		
		for(int i = 0 ; i < cantAristas ; i++) {
			fil = scan.nextInt();
			col = scan.nextInt();
			this.entrada.add(new ParDeNodos(fil, col));
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
		for(int i = 0 ; i < this.entrada.size() ; i++) {
			System.out.println(this.entrada.get(i).getNodo1() + " " + this.entrada.get(i).getNodo2());
		}
	}
	
	public void mostrarSalida() {
		for(int i = 0 ; i < this.salida.size() ; i++)
			System.out.println(this.salida.get(i).getNodo() + " " + this.salida.get(i).getColor());
	}
}
