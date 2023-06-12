package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
//import Casos_de_Teste.*;

public class principal {
  public static Graph graph;
  public static void main(String args[]) {
    long startTime, endTime;
    File arquivo;
    int height, width, index, i, harbors[], harbor;
    String str_sizes[], data[], data2[];
    Scanner scanner;

    try {
      startTime = System.currentTimeMillis(); //metodo para cronometrar tempo de execucao
      
      arquivo = new File("./Casos_de_Teste/case5.map");
      scanner = new Scanner(arquivo);

      // size[0] = altura, size[1] = largura
      str_sizes = scanner.nextLine().split(" ");
      height = Integer.parseInt(str_sizes[0]);
      width = Integer.parseInt(str_sizes[1]);

      harbors = new int[9];

      graph = new Graph(height * width);

      index = -1;
      data2 = scanner.nextLine().split("");

      while (scanner.hasNextLine()) { 
        data = data2;
        data2 = scanner.nextLine().split("");

        for(i=0; i < width; i++) {
          index++;
          if(!data[i].equals("*")) {
            if(!data[i].equals(".")) {
              harbor = Integer.parseInt(data[i]);
              harbors[harbor-1] = index;
            }
            if(i == width-1) {
              if(!data2[i].equals("*"))
                graph.addEdge(index, width+index);
              continue;
            }
            if(!data2[i].equals("*")) {
              graph.addEdge(index, width+index);
            }
            if(!data[i+1].equals("*")) {
              graph.addEdge(index, index+1);
            }
          }
        }
      }
      //le a ultima linha
      data = data2;
      for(i=0; i < width; i++) {
        if(!data[i].equals("*")) {
          if(!data[i].equals(".")) {
            harbor = Integer.parseInt(data[i]);
            harbors[harbor-1] = index+1;
          }
          if(i < width-1 && !data[i+1].equals("*")) {
            graph.addEdge(index, index+1);
          }
        }
        index++;
      }
      scanner.close();
      //acaba de escanear o grafo

      CaminhamentoLargura caminho = new CaminhamentoLargura(graph, harbors);
      //System.out.println(caminho.pathTo(harbors[1]));

      endTime = System.currentTimeMillis();
      System.out.println("Tempo de execucao -  " + (endTime - startTime) + " millisegundos");
    }
    catch(FileNotFoundException e) {
      System.out.println("Aconteceu um erro");
      e.printStackTrace();
    }
  }
}
