package salaDeOperaciones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class MainNormal {
	
    private static int numeroDeProcedimientos;
	private static String[] nombre;
	private static int[] horaInicio;
	private static int[] horaFinal;
	private static PrintWriter writer;

	public static ArrayList<Intervalo> intervaloscheduling(ArrayList<Intervalo> intervalos){
		
        Collections.sort(intervalos, Comparator.comparing(p -> p.getTiempoFinal()));
        
        ArrayList<Intervalo> resultList = new ArrayList<>();       
 
        for(Intervalo currentInterval : intervalos) {
            if(resultList.isEmpty()) resultList.add(currentInterval);
            else{
                if(currentInterval.getTiempoComienzo() >= resultList.get(resultList.size()-1).getTiempoFinal()){
                    resultList.add(currentInterval);
                }
            }
        }
        return resultList;
    }

	public static void main(String args[] ) throws Exception {
		long inicio = System.currentTimeMillis();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File("src/salaDeOperaciones/Prueba10.txt"); // Se debe revisar bien la ruta al ejecutarse ya que es la ruta de mi computador usando el ide eclipse
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			numeroDeProcedimientos = Integer.parseInt(br.readLine());
			nombre = new String[numeroDeProcedimientos];
			horaInicio = new int[numeroDeProcedimientos];
			horaFinal = new int[numeroDeProcedimientos];
			
			for (int i = 0; i < numeroDeProcedimientos; i++) {
				String lineaLeida = br.readLine();
				StringTokenizer token = new StringTokenizer(lineaLeida);
				nombre[i] = token.nextToken();
				horaInicio[i] = Integer.parseInt(token.nextToken());
				horaFinal[i] = Integer.parseInt(token.nextToken());

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
		
        ArrayList<Intervalo> intervalos = new ArrayList<>();
        for (int j = 0; j < numeroDeProcedimientos; j++) {
            intervalos.add(new Intervalo(nombre[j],horaInicio[j],horaFinal[j]));
        }
        
        ArrayList<Intervalo> compatibleintervalos = intervaloscheduling(intervalos);
        int procedimientosARealizarse = 0;
        int contadorHoras = 0;
        int aux = 0;
        for(Intervalo interval : compatibleintervalos) {
        	procedimientosARealizarse++;
        	aux = interval.getTiempoFinal() - interval.getTiempoComienzo();
            contadorHoras += aux;
            
        }
        
		try {
            writer = new PrintWriter("src/salaDeOperaciones/SolucionNormal.txt");
            writer.println(procedimientosARealizarse);
            writer.println(contadorHoras);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        System.out.println(procedimientosARealizarse);
        System.out.println(contadorHoras);
        
        for(Intervalo interval : compatibleintervalos) {
        	writer.println(interval.getNombre() + " " + interval.getTiempoComienzo() + "," + interval.getTiempoFinal());
        	System.out.println(interval.getNombre() + " " + interval.getTiempoComienzo()+ "," + interval.getTiempoFinal());
        }
        writer.close();
		long fin = System.currentTimeMillis();
        
        double tiempo = (double) ((fin - inicio));
         
        System.out.println(tiempo +" Milisegundos");
        
    }
}



