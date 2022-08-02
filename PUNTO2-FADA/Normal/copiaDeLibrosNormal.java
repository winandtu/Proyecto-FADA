import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class copiaDeLibrosNormal {
  public static int M; //Cantidad de libros
  public static int N;//cantidad de escritores
  public static int t0; //tiempo inicial
  public static int t1; //tiempo Final
  public static int[] pages = new int[501]; //Guardar en un arreglo las paginas
  public static boolean[] isFinalBook = new boolean[501];//Determinara en cierta pagina cuando el libro ha terminado

  public static boolean librosCompatibles(int NumeroPaginas) {
    int paginasActuales = 0;
    int numeroUsado = 0;
    //Recorremos la cantidad de libros de manera descendente
    for (int libro = M - 1; libro >= 0; --libro) {
      //Si las paginas del libro + mas las paginas escritas hasta ese momento es mayor al numero de paginas que le hemos dado como prueba, aumentara sus paginas y verificara si la suma de todas sus paginas es la misma cantidad de escritores retrornara false y el libro no se actualizara ,si no actualizara el libro y retornara true
      if (pages[libro] + paginasActuales > NumeroPaginas) {
        ++numeroUsado;
        if (numeroUsado == N) {
          return false;
        }
        paginasActuales = pages[libro];
      } else {
        paginasActuales += pages[libro];
      }
    }

    return true;
  }

  //utilizaremos un metodo para obtener las paginas minimas que se haran por dia
  public static int paginasMinimas(int sum, int largest) {
    int min = largest;
    int max = sum;

    while (min != max) {
      int middle = min + (max - min) / 2;
      if (librosCompatibles(middle)) {
        max = middle;
      } else {
        min = middle + 1;
      }
    }

    return min;
  }

  //Libros no compatibles 
  public static void librosNoCompatibles(int maximumPages) {
    int pagesForCurrent = 0;
    int currentScribe = N - 1;
  //Recorremos la cantidad de libros de manera descendente
    for (int book = M - 1; book >= 0; --book) {
      //Tendremos una condición que hara que si las paginas del libro + las paginas actuales superan el maximo de paginas que se pueden escribir o si las paginas escritas superan más de un libro, entonces se quitaran las paginas escritas que superan esas condiciones
      if (pages[book] + pagesForCurrent > maximumPages || currentScribe == book + 1) {
        --currentScribe;

        //si ya se termino de escribir el libro se reiniciaran el numero de paginas es decir se empezara de nuevo para un nuevo libro
        isFinalBook[book] = true;
        pagesForCurrent = 0;
      }

      pagesForCurrent += pages[book];
    }
  }

  public static void main(String[] args) throws IOException {

    File archivo = new File("Prueba5.txt");
    // BufferedReader bf = new BufferedReader(archivo);
    Scanner s = null;

    try {

      System.out.println("Contenido del archivo ");

      s = new Scanner(archivo);

      // Leemos linea a linea el fichero
      int count = -1; // seria n-1
      String[] PrimerosDatos = new String[20];
      while (s.hasNextLine()) {
        String linea = s.nextLine(); // Guardamos la linea en un String
        System.out.println(linea); // Imprimimos la linea
        count++; // numero de lineas que tiene el archivo
      }
      // Numero de lineas de un fichero:
      System.out.println("Total: " + count);
      

    } catch (Exception ex) {
      System.out.println("Mensaje: " + ex.getMessage());
      // System.out.println("Mensaje: " + count);
    } finally {
      // Cerramos el fichero tanto si la lectura ha sido correcta o no
      try {
        if (s != null)
          s.close();
      } catch (Exception ex2) {
        System.out.println("Mensaje 2: " + ex2.getMessage());
      }
    }

  }

}