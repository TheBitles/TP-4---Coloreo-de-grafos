package myPackage;

public class MatrizSimetrica {

	private boolean[] matrizSimetrica;
	private int ordenMatriz;
	private int dimensionVector;
	
	public MatrizSimetrica(int n) { // Recibe el orden de la matriz
		this.ordenMatriz = n;
		this.dimensionVector = (n*n - n) / 2;
		matrizSimetrica = new boolean[this.dimensionVector];
		
		for(int i = 0 ; i < n - 1 ; i++)
			matrizSimetrica[i] = false;
	}
	
	public void ponerArista(int pos) {
		matrizSimetrica[pos] = true;
	}
	
	public boolean hayArista(int pos) {
		return matrizSimetrica[pos];
	}
	
	public int getIndice(int fil, int col) {
		return fil * this.ordenMatriz + col - (fil*fil + 3*fil + 2) / 2;
	}
	
	public void setIndice(int fil, int col) {
		matrizSimetrica[fil * this.ordenMatriz + col - (fil*fil + 3*fil + 2) / 2] = true;
	}
	
	public void mostrar() {
		for(int i = 0 ; i < this.dimensionVector ; i++)
			System.out.print(this.matrizSimetrica[i] + " ");
	}
	
	public int getOrdenMatriz() {
		return this.ordenMatriz;
	}
	
	public int getDimension() {
		return this.dimensionVector;
	}
}
