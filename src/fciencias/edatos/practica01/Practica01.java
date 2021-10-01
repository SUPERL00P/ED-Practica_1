package fciencias.edatos.practica01;

import java.util.Arrays;

/**
* Práctica 1 del curso de Estructuras de Datos.
* @author Samuel Jiménez Milke
* @author 
* @version 2.0 Septiembre 2021.
* @since Laboratorio de Estructuras de Datos 2022-1.
*/
public class Practica01{

	/** 
	* Hace la mezcla de dos arreglos ordenados desde la primera posición hasta
	* una posición límite
	* @param array1 el primer arreglo a mezlar
	* @param n el límite de mezcla del primer arreglo
	* @param array2 el segundo arreglo a mezclar
	* @param m el límite de mezcla del segundo arreglo.
	* @return un arreglo ordenado de longitud m+n con la mezcla definida.
	*/
	public static int[] mergeSortedArray(int[] array1, int n, int[] array2, int m){
		if(n > array1.length || m > array2.length)
			throw new RuntimeException("Límites no válidos");
		
		int[] result = new int[n + m];
		int pointer;
		for(pointer = 0; pointer < n; pointer++)
			result[pointer] = array1[pointer];
		for(int i = 0 ; i < m ; i++, pointer++)
			result[pointer] = array2[i];
		
		// Ordenamiento del arreglo result
		for(int j = 0; j < result.length - 1; j++){
			for(int k = j+1; k < result.length; k++){
				if(result[k] < result[j]){
					int aux = result[k];
					result[k] = result[j];
					result[j] = aux;
				}
			}
		}

		return result;
	}

    /**
    * Verifica si un tablero contiene los números desde 0 hasta n-1 en cada fila y cada columna.
    * @param board el tablero de nxn que contiene elementos dentro del rango [0, n-1].
    * @return true si el tablero contiene los números desde 0 hasta n-1 en cada fila y columna,
	* false en otro caso.
    */
    public static boolean isValidBoard(int[][] board){
    	int length = board.length;
		for (int i = 0; i < length ; i++) {
			for (int j = 0; j < length ; j++ ) {
				boolean verificador = false;
				// Verifica sobre las filas
				for(int k = 0 ; k < length; k++){
					if(board[i][k] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
				verificador = false;
				// Verifica sobre las columnas
				for(int k = 0 ; k < length; k++){
					if(board[k][i] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
			}
		}
		return true;
	}

	/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArray(int[] num, int position){
		for(int i = 0; i < position ; i++){
			int aux = num[0];
			for(int j = 0; j < num.length -1 ; j++){
				num[j] = num[j+1];
			}
			num[num.length-1] = aux;
		}
	}

	/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArrayPlus(int[] num, int position){
		int length = num.length;
		int modulo = position % length;

		// Si puedo dividir el número de posiciones a recorrer entre la
		// longitud del arreglo sin dejar residuo(modulo == 0), significa
		// que el arreglo no se verá modificado, por lo que solo en casos  
		// diferentes vale la pena rotar el arreglo
		if(modulo != 0){
			// Creo un arreglo auxiliar para almacenar el arreglo una vez
			// sean rotados los elementos
			int[] aux = new int[length];
			// Creo una variable que me ayudara a almacenar la nueva posición
			// que tendrá un elemento una vez sea rotado
			int nuevaPosicion = 0;
			for(int i=0; i < length;i++){
				// Empiezo calculando cuál sera la nueva posicion de mi
				//elemento una vez lo rote "position" neces a la izquienda.

				// Reviso si un elemento en la posición i puede ser rotado
				// "position" veces a la izquierda sin que se salga del arreglo
				// (que llegue a una posición negativa del arreglo)
				if((i - position) < 0){
					// En caso de que se salga del arreglo me posiciono al 
					// extremo derecho del arreglo y recorro 
					// las posiciones restantes a la izquierda (length + (i-position)).
					
					// Una vez conociendo cuál sera la nueva posición del elemento,
					// lo almaceno en mi variable
					nuevaPosicion = length + (i-position);
				}
				else{
					// En caso de que no nos salgamos del arreglo, basta con
					// recorrer las "position" posiciones a la izquierda. Y
					// nna vez conociendo cuál sera la nueva posición del elemento,
					// lo almaceno en mi variable
					nuevaPosicion = i - position;
				}		
				// Me posiciono en la posición que tendrá mi elemento una vez sea rotado
				// en mi arreglo auxiliar y almaceno el elemento
				aux[nuevaPosicion]  = num[i];
			} 

			// 	Por último copiamos los elementos de mi arreglo auxiliar(aux) al arreglo num
			for(int i=0; i<length; i++){
				num[i] = aux[i];
			}
		}
	}

	/**
	* 	
	* @param directorio el directorio donde se encuentran los archivos .txt de las pruebas a realizar.
	* @param nombreArreglo nombre del archivo .txt del arreglo que se utilizara en la prueba.
	* @param idArreglo nombre identificativo del arreglo que se utilizara en la prueba.
	* @param position cantidad de espacios a rotar.
	*/
	public static void calcularTiempoEjercicio3(String directorio, String nombreArreglo, String idArreglo, int position){
		// Creo el arreglo que será utilizado en la prueba
		int[] array = ArrayReader.readArray(directorio + nombreArreglo);
		//	Inicio la prueba de tiempo del algoritmo 1 (el que aún NO se ha mejorado)
		long inicio1 = System.currentTimeMillis();
			rotateArray(array, position);
		long fin1 = System.currentTimeMillis();
		//	Presento en pantalla el arreglo rotado por el Algoritmo 1
		System.out.println("Arreglo "+ idArreglo +" rotado "+position+" veces, según el Algoritmo 1: " + Arrays.toString(array));
		//	Como el arreglo fue modificado, lo inicializo de nuevo
		array = ArrayReader.readArray(directorio + nombreArreglo);
		//	Inicio la prueba de tiempo del algoritmo 2 (el que ya fue mejorado)
		long inicio2 = System.currentTimeMillis();
			rotateArrayPlus(array, position);
		long fin2 = System.currentTimeMillis();
		//	Presento en pantalla el arreglo rotado por el Algoritmo 2
		System.out.println("Arreglo "+ idArreglo +" rotado "+position+" veces, según el Algoritmo 2: " + Arrays.toString(array));
		//	Presento en pantalla los tiempos correspondientes a los algoritmos ejecutados
		System.out.println("El algoritmo 1 en el arreglo "+ idArreglo +" se tardó: " + (fin1-inicio1) + " milisegundos");
		System.out.println("El algoritmo 2 en el arreglo "+ idArreglo +" se tardó: " + (fin2-inicio2) + " milisegundos");
	}


	public static void main(String[] args) {

		String directorio1 = "./Examples/ArrayTests/";
		String directorio2 = "./Examples/BoardTests/";

		// EJEMPLOS DE ACTIVIDAD 1
		//System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		//int[] arrayA1 = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
		//int[] arrayA2 = ArrayReader.readArray(directorio1 + "ArrayA2.txt");
		// int[] resultA = mergeSortedArray(arrayA1, 3, arrayA2, 5);
		// System.out.println("Resultado A: "+Arrays.toString(resultA));

		//int[] arrayB1 = ArrayReader.readArray(directorio1 + "ArrayB1.txt");
		//int[] arrayB2 = ArrayReader.readArray(directorio1 + "ArrayB2.txt");
		// int[] resultB = mergeSortedArray(arrayB1, 5, arrayB2, 5);
		// System.out.println("Resultado B: "+Arrays.toString(resultB));

		//int[] arrayC1 = ArrayReader.readArray(directorio1 + "ArrayC1.txt");
		//int[] arrayC2 = ArrayReader.readArray(directorio1 + "ArrayC2.txt");
		// int[] resultC = mergeSortedArray(arrayC1, 4, arrayC2, 6);
		// System.out.println("Resultado C: "+Arrays.toString(resultC));



		// EJEMPLOS DE ACTIVIDAD 2
		//System.out.println("\nEJEMPLOS DE ACTIVIDAD 2\n");

		// int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
		// boolean boardResultA = isValidBoard(boardA);
		// System.out.println("El tablero A es válido: "+boardResultA);

		// int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
		// boolean boardResultB = isValidBoard(boardB);
		// System.out.println("El tablero B es válido: "+boardResultB);

		// int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
		// boolean boardResultC = isValidBoard(boardC);
		// System.out.println("El tablero C es válido: "+boardResultC);

		// int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");
		// boolean boardResultD = isValidBoard(boardD);
		// System.out.println("El tablero D es válido: "+boardResultD);


		// EJEMPLOS DE ACTIVIDAD 3
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 3\n");

		// rotateArray(arrayA1, 500);
		calcularTiempoEjercicio3(directorio1, "ArrayA1.txt","A1", 500);
		// rotateArray(arrayB1, 1000);
		calcularTiempoEjercicio3(directorio1, "ArrayB1.txt","B1", 1000);
		// rotateArray(arrayC1, 2000);
		calcularTiempoEjercicio3(directorio1, "ArrayC1.txt","C1", 2000);
		// rotateArray(arrayD1, 3000);
		calcularTiempoEjercicio3(directorio1, "ArrayD1.txt","D1", 3000);
		// rotateArray(arrayE1, 10000);
		calcularTiempoEjercicio3(directorio1, "ArrayE1.txt","E1", 10000);
		// rotateArray(arrayF1, 20000);
		calcularTiempoEjercicio3(directorio1, "ArrayF1.txt","F1", 20000);

		System.out.println("\n\nFIN DE EJEMPLOS\n");
	}
}