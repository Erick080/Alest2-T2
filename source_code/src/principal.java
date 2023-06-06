package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
//import Casos_de_Teste.*;

public class principal {
  public static void main(String args[]) {
    long startTime, endTime;
    File arquivo;
    int size, index, i;
    String sizes[];
    Scanner scanner;

    try {
      startTime = System.currentTimeMillis(); //metodo para cronometrar tempo de execucao
      
      arquivo = new File("Casos_De_Teste/case0.map");
      scanner = new Scanner(arquivo);

      // size[0] = altura, sizep[1] = largura
      sizes = scanner.nextLine().split("");
      size = Integer.parseInt(sizes[0]);
      size *= Integer.parseInt(sizes[1]);

      index = 0;
      while (scanner.hasNextLine()) { 
        String[] data = scanner.nextLine().split("");
        for(i=0; i < data.length; i++) {
          //if(data[i].equals("."))
            System.out.printf(data[i]);
        }
        System.out.println("\n");
        index++;
      }
      scanner.close();
      
      endTime = System.currentTimeMillis();
      System.out.println("Tempo de execucao -  " + (endTime - startTime) + " millisegundos");
    }
    catch(FileNotFoundException e) {
      System.out.println("Aconteceu um erro");
      e.printStackTrace();
    }
    
    System.out.println("Hello World");
  }
}
