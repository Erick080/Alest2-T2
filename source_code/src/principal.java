package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import Casos_de_Teste.*;

public class principal {
    try {
        long startTime = System.currentTimeMillis(); //metodo para cronometrar tempo de execucao
  
        File arquivo = new File("CasosDeTeste/caso_1000_100000.txt");
        Scanner scanner = new Scanner(arquivo);
        String qtdRodadas = scanner.nextLine().split(" ")[1]; //numero de rodadas
        int rodadas = Integer.parseInt(qtdRodadas);
        HashMap<Integer,macaco> lista = new HashMap<Integer,macaco>();
        int index = 0;  //itera pela lista de macacos
        
        while (scanner.hasNextLine()) { 
          String[] data = scanner.nextLine().split(" ");
          lista.put(index,new macaco(Integer.parseInt(data[4]), Integer.parseInt(data[7]))); // cria e adiciona o macaco no hashmap
  
          for(int i = 11;i<data.length;i++) {
            lista.get(index).add_coco(Integer.parseInt(data[i])); //adiciona cocos ao macaco
          }
  
          
          index++;
        }
        scanner.close();
  
        //operacoes
  
        while(rodadas != 0) { //controla as rodadas
          for(int k = 0;k < lista.size();k++) {  //itera entre macacos
            macaco m = lista.get(k);
            int par = m.get_par(); //pega o index do macaco que vai receber qtd de cocos pares
            int impar = m.get_impar(); //pega o index do macaco que vai receber qtd de cocos impares
  
            //transferencia de cocos;
            lista.get(par).add_coco_par(m.get_coco_par());
            lista.get(impar).add_coco_impar(m.get_coco_impar());
            lista.get(k).zerar_cocos(); //entregou todos os cocos, entao fica zerado
          }
          rodadas--;
        }
  
        // descobrir qual macaco vence
        macaco monke, aux;
        int melhorMacaco = 0; 
        monke = lista.get(0);
        for(int i=1; i < lista.size(); i++) {
          aux = lista.get(i);
          if(monke.cocos_length() < aux.cocos_length()) {
            monke = aux;
            melhorMacaco = i;
          }
        }
        System.out.printf("O macaco com mais cocos é o %d com %d cocos\n", melhorMacaco,lista.get(melhorMacaco).cocos_length());
        
        long endTime = System.currentTimeMillis();
        System.out.println("Tempo de execucao -  " + (endTime - startTime) + " millisegundos");
      }
      catch(FileNotFoundException e) {
        System.out.println("Aconteceu um erro");
        e.printStackTrace();
      }
}
