package salaDeOperaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MainDinamica {

	private static int numeroDeProcedimientos;
	private static String nombre[];
	private static String lineaLeida;
	private static StringTokenizer tokenTurno;
	private static int horaInicio[];
	private static int horaFinal[];
	private static int[][] cost; //matriz de costos
	private static int[][] aux0;//matriz auxiliar
	private static int[] solX; //matriz solucíon
	private static PrintWriter writer;

	public static void main(String[] args) {
		long inicio = System.currentTimeMillis();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		System.out.println("SALA DE OPERACIONES");
		System.out.println("");

		try {
			archivo = new File("src/salaDeOperaciones/Prueba5.txt"); // Se debe revisar bien la ruta al ejecutarse ya que es la ruta de mi computador usando el ide eclipse
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			numeroDeProcedimientos = Integer.parseInt(br.readLine());
			System.out.println("Datos De Procedimientos Desorganizados:");
			System.out.println("Numero De Procedimientos = " + numeroDeProcedimientos);
			nombre = new String[numeroDeProcedimientos];
			horaInicio = new int[numeroDeProcedimientos];
			horaFinal = new int[numeroDeProcedimientos];

			for (int i = 0; i < numeroDeProcedimientos; i++) {
				lineaLeida = br.readLine();
				tokenTurno = new StringTokenizer(lineaLeida);
				nombre[i] = tokenTurno.nextToken();
				horaInicio[i] = Integer.parseInt(tokenTurno.nextToken());
				horaFinal[i] = Integer.parseInt(tokenTurno.nextToken());

			}
			for (int j = 0; j < numeroDeProcedimientos; j++) {
				System.out.println(nombre[j] + " " + horaInicio[j] + " " + horaFinal[j]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("");
		System.out.println("Datos De Procedimientos Organizados Descendentemente Segun Su Hora De Finalizaci�n:");
		System.out.println("");
		System.out.println("ENTRADA:");
		System.out.println("Numero De Procedimientos = " + numeroDeProcedimientos);

		insertionSort(nombre, horaInicio, horaFinal);

		for (int j = 0; j < numeroDeProcedimientos; j++) {
			System.out.println(nombre[j] + " " + horaInicio[j] + " " + horaFinal[j]);
		}

		calcularMatrizCostos(25, numeroDeProcedimientos, horaInicio, horaFinal);
		System.out.println("");
		System.out.println("Matriz De Costos");
		for (int x = 0; x < numeroDeProcedimientos; x++) {
			System.out.print("|");
			for (int y = 0; y < 25; y++) {
				System.out.print(cost[y][x]);
				if (y != 25)
					System.out.print("\t");
			}
			System.out.println("|");
		}

		System.out.println("");
		System.out.println("Matriz Auxiliar");

		for (int x = 0; x < numeroDeProcedimientos; x++) {
			System.out.print("|");
			for (int y = 0; y < 25; y++) {
				System.out.print(aux0[y][x]);
				if (y != 25)
					System.out.print("\t");

			}
			System.out.println("|");
		}
		System.out.println("");
		System.out.println("Solucion");
		calcularSolucion(aux0, numeroDeProcedimientos, 0, horaFinal);
		for (int i = 0; i < solX.length; i++) {
			System.out.print("[" + solX[i] + "]");
		}
		System.out.println("");
		System.out.println("");

		System.out.println("SALIDA:");
		System.out.println("Guardada en SolucionDinamica.txt");
		int procedimientosARealizarEnLaSala = calcularNumeroDeProcedimientos(solX);
		System.out.println(procedimientosARealizarEnLaSala);
		int tiempoTotalDeUsoDeLaSala = cost[0][numeroDeProcedimientos - 1];
		System.out.println(tiempoTotalDeUsoDeLaSala);
		try {
			writer = new PrintWriter("src/salaDeOperaciones/SolucionDinamica.txt");
			writer.println(procedimientosARealizarEnLaSala);
			writer.println(tiempoTotalDeUsoDeLaSala);
			calcularProcedimientos(solX);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long fin = System.currentTimeMillis();
        
        double tiempo = (double) ((fin - inicio));
         
        System.out.println(tiempo +" Milisegundos");
	}

	public static void calcularMatrizCostos(int tiempo, int numeroProc, int inicio[], int fin[]) {

		cost = new int[tiempo][numeroProc];
		aux0 = new int[tiempo][numeroProc];
		solX = new int[numeroProc];

		for (int j = 0; j < tiempo; j++) {
			if (inicio[0] < j) {
				cost[j][0] = 0;
				aux0[j][0] = 0;
			} else {
				cost[j][0] = fin[0] - inicio[0];
				aux0[j][0] = 1;
			}
		}

		for (int i = 1; i < numeroProc; i++) {
			for (int j = 0; j < tiempo; j++) {
				if (inicio[i] < j) {
					cost[j][i] = cost[j][i - 1];
					aux0[j][i] = 0;

				} else {
					if (cost[j][i - 1] > fin[i] - inicio[i] + cost[fin[i]][i - 1]) {
						cost[j][i] = cost[j][i - 1];
						aux0[j][i] = 0;

					} else {
						cost[j][i] = fin[i] - inicio[i] + cost[fin[i]][i - 1];
						aux0[j][i] = 1;
					}
				}
			}
		}
	}

	public static void calcularSolucion(int A[][], int numeroProc, int tiempo, int tiempoSiguiente[]) {
		if (numeroProc == 1) {
			solX[numeroProc - 1] = A[tiempo][numeroProc - 1];
		} else {
			if (A[tiempo][numeroProc - 1] == 1) {
				solX[numeroProc - 1] = 1;
				calcularSolucion(A, numeroProc - 1, tiempoSiguiente[numeroProc - 1], tiempoSiguiente);
			} else {
				solX[numeroProc - 1] = 0;
				calcularSolucion(A, numeroProc - 1, tiempo, tiempoSiguiente);

			}
		}


	}

	public static void insertionSort(String[] vector1, int[] vector2, int[] vector3) {
		
		for (int i = 0; i < vector3.length; i++) {
			String aux = vector1[i];
			int aux2 = vector2[i];
			int aux3 = vector3[i];
			int j;
			for (j = i - 1; j >= 0 && vector3[j] < aux3; j--) {
				vector3[j + 1] = vector3[j];
				vector2[j + 1] = vector2[j];
				vector1[j + 1] = vector1[j];
			}
			vector1[j + 1] = aux;
			vector2[j + 1] = aux2;
			vector3[j + 1] = aux3;
		}

	}

	public static int calcularNumeroDeProcedimientos(int[] solucion) {
		int total = 0;
		for (int i = 0; i < solucion.length; i++) {
			if (solucion[i] == 1) {
				total++;
			}
		}

		return total;

	}

	public static void calcularProcedimientos(int[] solucion) {

		for (int i = 0; i < solucion.length; i++) {
			if (solucion[i] == 1) {
				System.out.println(nombre[i] + " " + horaInicio[i] + " " + horaFinal[i]);
				writer.println(nombre[i] + " " + horaInicio[i] + " " + horaFinal[i]);

			}
		}
	}
}
