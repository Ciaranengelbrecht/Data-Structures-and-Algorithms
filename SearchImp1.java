import CITS2200.Graph;
import CITS2200.Search;
import java.util.*;


public class SearchImp implements Search {
	private int WHITE = 0;
    private int GREY = 1;
    private int BLACK = 2;
    private int time;
    private int [] colours;
    private int[][] times;
    private LinkedList<int[]> bfsL = new LinkedList<int[]>();


    public int[] getConnectedTree(Graph testGraph, int startVertex) {
        // TODO: Implement getConnectedTree.
				BFS(testGraph,startVertex);
        return bfsL.get(0);
    }

    public int[] getDistances(Graph g, int startVertex) {
        // TODO: Implement getDistances.
				BFS(g,startVertex);
        return bfsL.get(1);
    }

		public void BFS(Graph g, int startVertex)
    {
        int [][] edgeMatrix = g.getEdgeMatrix();
        int[] colour = new int[g.getNumberOfVertices()];
        colour[startVertex] = GREY;
        int[] parent = new int[g.getNumberOfVertices()];
        Arrays.fill(parent, -1);
        int[]distances = new int[g.getNumberOfVertices()];
        Arrays.fill(distances, -1);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startVertex);
        distances[startVertex] = 0;

        while(!queue.isEmpty())
        {
            int focus = queue.poll();
            for(int i = 0;i < edgeMatrix.length; ++i)
            {
                if (edgeMatrix[focus][i] > 0 && colour[i] < 1)
                {
                    distances[i] = distances[focus] + 1;
                    parent[i] = focus;
                    colour[i] = 1;
                    queue.add(i);
                }
            }
        }
        bfsL.add(0, parent);
        bfsL.add(1,distances);
    }

    
    public int[][] getTimes(Graph g, int startVertex) {
        // TODO: Implement getTimes.
				int vertex = g.getNumberOfVertices();
        this.colours = new int[vertex];
        this.times = new int[vertex][2];
        this.time = 0;
        DFS(g.getEdgeMatrix(), startVertex);
        return times;
        
    }

		private void DFS(int [][] edgeMatrix, int vertex)
    {
        colours[vertex] = GREY;
        times[vertex][0] = time;
        time++;
        int[] edges = edgeMatrix[vertex];
        for(int i = 0 ;i < edges.length; i++)
        {
            if (colours[i] == WHITE)
            {
                DFS(edgeMatrix, i);
            }
        }
        colours[vertex] = BLACK;
        times[vertex][1] = time;
        time++;
    }
}
