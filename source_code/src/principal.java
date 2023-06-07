package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import java.util.HashMap;
import java.util.Scanner;
//import Casos_de_Teste.*;

public class principal {
  public static void main(String args[]) {
    long startTime, endTime;
    File arquivo;
    int size, index, i, harbors[],harbor_number;
    String sizes[], data[], data2[];
    Scanner scanner;
    Graph graph;

    try {
      startTime = System.currentTimeMillis(); //metodo para cronometrar tempo de execucao
      
      arquivo = new File("./Casos_de_Teste/case0.map");
      scanner = new Scanner(arquivo);

      // sizes[0] = altura, sizes[1] = largura
      sizes = scanner.nextLine().split(" ");
      size = Integer.parseInt(sizes[0]);
      size *= Integer.parseInt(sizes[1]);

      harbors = new int[9];

      graph = new Graph(size);

      index = -1;
      data2 = scanner.nextLine().split("");
      data = data2; //data percorre linha de cima
      data2 = scanner.nextLine().split(""); //data2 percorre linha de baixo
      
      while (scanner.hasNextLine()) { 
        for(i=0; i < 50; i++) {
          index++; //index = posicao do caractere na linha de cima
          if(!data[i].equals("*")) {
            if(!data[i].equals(".")) { //caso seja um porto,adiciona sua posicao na lista de portos
              size = Integer.parseInt(data[i]);
              harbors[size-1] = index;
            }
            if(i == 49) {
              if(!data2[i].equals("*"))
                graph.addEdge(index, 50+index);
              continue;
            }
            if(!data2[i].equals("*")) { //verifica char embaixo
              if(!data2[i].equals(".")) { //se for porto, adiciona posicao na lista
                harbor_number = Integer.parseInt(data2[i]);
                harbors[harbor_number-1] = index+1;
              }else{ //caso contrario so adiciona edge
                graph.addEdge(index, 50+index);
                if(i < 49 && !data2[i+1].equals("*")) { //verifica char na direita dele
                  graph.addEdge(index, index+1);
                }}
              
            }
            if(!data[i+1].equals("*")) { //verifica char na direita
              graph.addEdge(index, index+1);
            }
            
          }
          }
          data = data2; //data percorre linha de cima
          data2 = scanner.nextLine().split(""); //data2 percorre linha de baixo
          
        }
        scanner.close();
      String resultado = graph.toDot();
      PrintWriter out = new PrintWriter("dot.txt");
      out.write(resultado);
      out.close();
      for(i=0;i<harbors.length;i++)
        System.out.println(harbors[i]);
      endTime = System.currentTimeMillis();
      System.out.println("Tempo de execucao -  " + (endTime - startTime) + " millisegundos");
      }
      catch(FileNotFoundException e) {
        System.out.println("Aconteceu um erro");
        e.printStackTrace();
      }

      
  }
}
