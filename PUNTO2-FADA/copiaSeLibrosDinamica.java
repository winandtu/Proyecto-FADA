import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.crypto.Data;

public class copiaSeLibrosDinamica
{
	public static int[][] dp = new int[1000][1000];
	public static int[] pages = new int[1000];
	public static int n;//escritores
	public static int m;//libros
	public static int MAX = 1000000000;

	public static int rec(int position, int sub)
	{
		if (position > n)
		{
			if (sub == 0)
			{
				return 0;
			}
			return MAX;
		}
		if (sub == 0)
		{
			if (position > n)
			{
				return 0;
			}
			return MAX;
		}

		if (dp[position][sub] != -1)
		{
			return dp[position][sub];
		}

		int ans = MAX;
		int i;
		int sum = 0;
		for (i = position; i <= n; i++)
		{
			sum += pages[i];
			ans = Math.min(ans,Math.max(sum,rec(i + 1, sub - 1)));
		}
		dp[position][sub] = ans;
		return ans;
	}
	static String ARCHIVO_LECTURA = "Prueba5";
    static String ARCHIVO_ESCRITURA = "SalidaDinamica";
    /*
     * Método para realizar la lectura del problema, no modificar.
     */
    public static Entrada input(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Entrada input = null;
        try {
            archivo = new File (ARCHIVO_LECTURA +".txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            linea = br.readLine();
            String[]data = linea.split(" ");
            int n = Integer.valueOf(data[0]);
            int m = Integer.valueOf(data[1]);
            Libro[] libros = new Libro[m];
            for(int i = 0 ; i < m ; ++i){
                linea = br.readLine();
                libros[i] = new Libro(Integer.valueOf(data[0]));
            }
            input = new Entrada(n, m, libros);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{                    
            if( null != fr ){   
                fr.close();     
            }                  
        }catch (Exception e2){ 
            e2.printStackTrace();
        }
        return input;
    }
    /*
     * Método para realizar la escritura de la respuesta del problema, no modificar.
     */
    public static void output(Respuesta output){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ARCHIVO_ESCRITURA+".txt");
            pw = new PrintWriter(fichero);
            pw.println(output.tiempoTotal);
            for(int i = 0 ; i < output.libroQueEmpieza.length ; ++i){
                pw.println(output.libroQueEmpieza[i] + " " + output.libroQueTermina[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
           if (null != fichero)
              fichero.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /*
     * Implementar el algoritmo y devolver un objeto de tipo Respuesta, el cual servirá
     * para imprimir la solución al problema como se requiere en el enunciado.
     */
    static Respuesta solve(int n,int m, Libro [] libros){
        return new Respuesta(0);
    }

	public static void main(String[] args){

        Entrada entrada = input();
        
        int i,aux,ans,pos,sum;
        
        Respuesta escritura = new Respuesta(rec(0,entrada.m));

        output(escritura);
        
        while (aux <= n){
		    ans = MAX;
            pos = -1;
            sum = 0;
            for (i = aux; i <= n; i++){
                sum += pages[i];
                if (ans > Math.max(sum,rec(i + 1,entrada.m - 1))){
                    ans = Math.max(sum,rec(i + 1,entrada.m - 1));
                    pos = i;
                }
            }
		    m--;
		    for (i = aux; i <= pos; i++){
			    if (entrad == 0){
				    return 0;
			    }
                output(escritura.pages[i]);
                System.out.print(pages[i]);
                System.out.print(" ");
		}
		
		System.out.print(" / ");
		aux = pos + 1;
        }       

	    	

	}
	static class Entrada{
        int n;
        int m;
        Libro[]libros;
        public Entrada(int n, int m, Libro[] libros) {
            this.n = n;
            this.m = m;
            this.libros = libros;
        }
        
    }
    static class Respuesta{
        int tiempoTotal;
        /*
         * Esta variable contiene en su posición i, el nombre del libro por el que empieza el i-ésimo
         * escritor.
         */
        String[]libroQueEmpieza;
        /*
         * Esta variable contiene en su posición i, el nombre del libro por el que termina el i-ésimo
         * escritor.
         */
        String[]libroQueTermina;
        public Respuesta(int tiempoTotal) {
            this.tiempoTotal = tiempoTotal;
        }

        
    }
    /*
     * Clase base para interpretar los objetos tratados en el problema.
     */
    public static class Libro{
        int paginas;
        public Libro(int paginas) {
            
            this.paginas = paginas;
        }
    }

}
