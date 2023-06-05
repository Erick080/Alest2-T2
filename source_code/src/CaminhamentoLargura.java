//import java.util.Queue;
import java.util.ArrayList;

public class CaminhamentoLargura {
    private boolean marked[];
    private int edgeTo[];
    private int distTo[];
    private int ref;

    public CaminhamentoLargura(Graph g,int s){
        this.ref = s;
        marked = new boolean[g.V()];
        edgeTo = new int [g.V()];
        distTo = new int [g.V()];
        bfs(g,s);
    }


    private void bfs(Graph g,int s){
        ArrayList<Integer> q = new ArrayList<Integer>();
        q.add(s);
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
                }
            }
        }
    }

    public boolean hasPath(int v){
        return marked[v] == true;
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPath(v)){return null;}
        Bag<Integer> b  = new Bag();
        b.add(v);
        int aux = edgeTo[v];
        while(aux != -1){
            b.add(aux);
            aux = edgeTo[aux];
        }
        return b;
    }

}
