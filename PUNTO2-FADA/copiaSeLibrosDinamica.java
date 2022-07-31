import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class copiaSeLibrosDinamica
{
	public static int[][] dp = new int[1000][1000];
	public static int[] pages = new int[1000];
	public static int n;
	public static int m;
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

	public static void main(String[] args)
	{
		File archivo = new File("/PUNTO2-FADA/SalidaDinamica.txt");
		FileReader fr = null;
     	BufferedReader br = null;
		PrintWriter pw = null;
		
		//Se crea el archivo si no existe
		if(!archivo.exists()) {
			try {
			  archivo.createNewFile();
			  FileWriter escritura = new FileWriter(archivo);
			  pw = new PrintWriter(archivo);
			  //Aqui se debe leer n y m del archivo de pruebas
			  archivo = new File ("/PUNTO2-FADA/Prueba5.txt");
			  fr = new FileReader (archivo);
			  br = new BufferedReader(fr);
			  // Lectura del fichero
			  String linea;
			  while((linea=br.readLine())!=null)
				 //Aqui escribimos n y m en el archivo final
				 escritura.append(linea);

			  


			} catch (IOException e) {
			  e.printStackTrace();
			}
		  }

	}

}
