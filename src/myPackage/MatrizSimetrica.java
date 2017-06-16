package myPackage;

public class MatrizSimetrica {

	private int[] matrizSimetrica; // Vector de pesos
	private int ordenMatriz;
	private int dimensionVector;
	private int ultPos; // Última posición libre
	
	public MatrizSimetrica(int n) { // Recibe el orden de la matriz
		this.ordenMatriz = n;
		this.dimensionVector = (n*n - n) / 2;
		this.ultPos = 0;
		matrizSimetrica = new int[this.dimensionVector];
	}

	public MatrizSimetrica(int mat[][], int n) { // Recibe la matriz y el orden de la misma
		this.ordenMatriz = n;
		this.dimensionVector = (n*n - n) / 2;
		this.ultPos = 0;
		matrizSimetrica = new int[this.dimensionVector];
		
		for(int i = 0 ; i < n - 1 ; i++) {
			for(int j = i + 1 ; j < n ; j++)
				agregar(mat[i][j]);
		}
	}
	
	public void agregar(int elemento) {
		matrizSimetrica[this.ultPos] = elemento;
		this.ultPos++;
	}
	
	public int getIndice(int fil, int col) {
		return fil * this.ordenMatriz + col - (fil*fil + 3*fil + 2) / 2;
	}
	
	public void setIndice(int fil, int col, int elemento) {
		matrizSimetrica[fil * this.ordenMatriz + col - (fil*fil + 3*fil + 2) / 2] = elemento;
	}
	
	public int getFila(int indice) {
		return this.dimensionVector - ((int) Math.ceil((1 + Math.sqrt(1 + 8*(this.dimensionVector - indice)))) / 2);
	}
	
	public int getColumna(int indice) {
		int fila = getFila(indice);
		return this.ordenMatriz - (this.dimensionVector - indice - ((this.ordenMatriz - fila - 1)*2 -(this.ordenMatriz - fila - 1))/2);
	}
	
	public void mostrar() {
		for(int i = 0 ; i < this.dimensionVector ; i++)
			System.out.print(this.matrizSimetrica[i] + " ");
	}
}
