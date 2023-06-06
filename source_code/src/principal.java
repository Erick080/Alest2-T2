package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import Casos_de_Teste.*;

public class principal {
  public static void main() {
    try {
      long startTime = System.currentTimeMillis(); //metodo para cronometrar tempo de execucao

      File arquivo = new File("CasosDeTeste/caso_1000_100000.txt");
      Scanner scanner = new Scanner(arquivo);
      String qtdRodadas = scanner.nextLine().split(" ")[1]; //numero de rodadas
      int rodadas = Integer.parseInt(qtdRodadas);
      int index = 0;  //itera pela lista de macacos
      
      while (scanner.hasNextLine()) { 
        String[] data = scanner.nextLine().split(" ");

        for(int i = 11;i<data.length;i++) {
        }
        index++;
      }
      scanner.close();
      
      long endTime = System.currentTimeMillis();
      System.out.println("Tempo de execucao -  " + (endTime - startTime) + " millisegundos");
    }
    catch(FileNotFoundException e) {
      System.out.println("Aconteceu um erro");
      e.printStackTrace();
    }
  }
}
