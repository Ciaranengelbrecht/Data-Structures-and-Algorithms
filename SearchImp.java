import CITS2200.Graph;
import CITS2200.Search;
import java.util.*;

/**
 * A breadth-first search and depth-first search implementation for directed graphs.
 * @author Ciaran Engelbrecht - 23169641
 */

public class SearchImp implements Search {

    /**
     * Completes the BFS algorithm
     * @param g is graph being analysed
     * @param startVertex is the first vertex to perform BFS
     * @return the parent of each vertex in the tree
     */
    public int[] getConnectedTree (Graph g, int startVertex) {
        int size = g.getNumberOfVertices();
        int[] parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
        Queue<Integer> q;
        q = new LinkedList<Integer>();
        q.offer(startVertex);
        while(q.peek() != null) {
            int current = q.remove();
            for (int i = 0; i < size; i++) {
                if (g.getWeight(current,i) >= 1 && parent[i] == -1) {
                    q.offer(i);
                    parent[i] = current;
                }
            }
        }
        return parent;
    }
    

    /**
     * Completes the BFS algorithm to find the distances of vertices from the starting vertex
     * @param g is graph being analysed
     * @param startVertex is the first vertex to perform BFS
     * @return the distance of each vertex from the starting vertex
     */
    public int[] getDistances(Graph g, int startVertex) {
        int size = g.getNumberOfVertices();
        int[] distance = new int[size];
        for (int i = 0; i < size; i++) {
            distance[i] = -1;
        }
        distance[startVertex] = 0;
        Queue<Integer> q;
        q = new LinkedList<Integer>();
        q.offer(startVertex);
        while(q.peek() != null) {
            int current = q.remove();
            for (int i = 0; i < size; i++) {
						  if (g.getWeight(current,i) >= 1 && distance[i] == -1) {
								  q.offer(i);
                  distance[i] = distance[current] + 1;    
                }
            }
        }
        return distance;
    }
    

    /**
     * Completes the DFS algorithm
     * @param g is graph being analysed
     * @param startVertex is the first vertex to perform DFS
     * @return a 2-dimensional array, containing the start and end time
     */
    public int[][] getTimes(Graph g, int startVertex) {
        int size = g.getNumberOfVertices();
        int[][] times = new int[size][2];
        int time = 0;
        boolean[] visited = new boolean[size];
        for(int i = 0; i<size; ++i) {
            if(visited[i] = false) {
                dFSTimer(g, startVertex, visited, times, time);
            }
        }
        return times;
    }
    

    /**
     * Find the start and end time of each vertex
     * @param g is graph being analysed
     * @param current is the current vertex
     * @param visited keeps track if the vertex is visited
     * @param times is the start and end times of the vertex
     * @param time is the time
     */    
    public void dFSTimer(Graph g, int current, boolean[]visited, int[][] times, int time) {
        visited[current] = true;
        int[][] adjacent = g.getEdgeMatrix();
        int size = g.getNumberOfVertices();
        times[current][0] = ++time;
        for(int i = 0; i < size; ++i) {
            if((adjacent[current][i] > 0) && (visited[i] = false)) {
                dFSTimer(g, i, visited, times, time);
            }
        }
        times[current][1] = ++time;
    }
}