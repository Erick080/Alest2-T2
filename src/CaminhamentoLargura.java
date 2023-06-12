package src;

import java.util.ArrayList;

public class CaminhamentoLargura {
    private boolean marked[];
    private int edgeTo[], distTo[], distHarbor[];
    private int harbor;
    private Graph g;

    public CaminhamentoLargura(Graph graph, int[] s){
        this.g = graph;
        marked = new boolean[g.V()];
        edgeTo = new int [g.V()];
        distTo = new int [g.V()];
        distHarbor = new int[s.length];
        distTo[s[0]] = 0;
        for(int i = 0; i < s.length; i++) {
            if(i == s.length-1) {
                bfs(s[s.length-1], s[0]);
            }
            else 
                if(!bfs(s[i], s[i+1]) && i < s.length-2) {
                    harbor++;
                    bfs(s[i], s[(i+=1) +1]);
                }
            harbor++;
        }
    }
        
    private boolean bfs(int s, int d){
        ArrayList<Integer> q = new ArrayList<Integer>();
        q.add(s);
        marked = new boolean[g.V()];
        marked[s] = true;
        distTo[s] = 0;

        while(!q.isEmpty()){
            int v = q.remove(0);
            for(int w:g.adj(v)){
                if(marked[w] == false){
                    edgeTo[w] = v;
                    marked[w] = true;
                    distTo[w] = distTo[v]+1;
                    q.add(w);
                    if(w == d) {
                        //System.out.println("achou");
                        distHarbor[harbor] = distTo[w];
                        return true;
                    }
                }
            }
        }
        distHarbor[harbor] = -1;
        //System.out.println("nao achou");
        return false;
    }

    public void print() {
        int total = 0;
        System.out.println("\t CAMINHO ENTRE PORTOS");
        System.out.println("\t-----------------------");
        for(int i = 0, j = 1, k = 2; i < harbor; i++, j++, k++){
            if(distHarbor[i] != -1) {
                total += distHarbor[i];
                if(i == 8) { 
                    k = 1;
                }
                System.out.printf("\t   Porto %d ao %d: %d\n",
                                j, k, distHarbor[i]);
                j = k-1;
            }    
            else {
                j--;
            }
        }
        System.out.println("\tTotal de combustivel: " + total);
    }

    public boolean hasPath(int v){
        return marked[v] == true;
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPath(v)){return null;}
        Bag<Integer> b  = new Bag<>();
        b.add(v);
        int aux = edgeTo[v];
        while(aux != -1){
            b.add(aux);
            aux = edgeTo[aux];
        }
        return b;
    }

}
