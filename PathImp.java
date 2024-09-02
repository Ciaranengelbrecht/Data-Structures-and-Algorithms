import CITS2200.Graph;
import CITS2200.Path;
import java.util.*;
// Ciaran Engelbrecht (23169641)

public class PathImp implements Path {
	  /**
     * Finds minimum weight of a spanning tree for provided graph implementing Prim's algorithm
     * @param graph is Graph to be searched
     * @return the total weight of the minimum spanning tree, or -1 if there is no spanning tree
     */
  public int getMinSpanningTree(Graph graph) {
    // TODO: Implement getMinSpanningTree()
		int numOfVertices = graph.getNumberOfVertices();
 		int[][] weights = graph.getEdgeMatrix();
		int[] seen = new int[numOfVertices];
		int[] distance = new int[numOfVertices];
		Arrays.fill(distance, -1);
		Arrays.fill(seen, 0);

 		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
 		pq.add(new Edge(0, 0));
		distance[0] = 0;
		
 		while (!pq.isEmpty()) {		
			int vertex = pq.remove().vertex;
			if (seen[vertex] != 0) {
				continue;
			}
			seen[vertex] = 1;
 					
			for (int i = 0; i < numOfVertices; i++) {
				int weight = weights[vertex][i];
				if (weight != 0 && seen[i] != 1) {
					
					if (distance[i] == -1 || distance[i] > weight) { 
						distance[i] = weight;
						pq.add(new Edge(i, distance[i]));
					}
				}
			}
		}
 		int result = 0;
 		for (int i = 0; i < numOfVertices; i++){
 			if (distance[i] == -1){
 				return -1;
 			}
 			result += distance[i];
 		}
		return result;
	}
	

	  /**
     * Implements Dijkstra's algorithm on the provided non-negative weighted graph, to find distance to all vertices from given source
     * @param graph the Graph to be searched
     * @param source the origin vertex on which to begin the search
     * @return an array listing the shortest distance to each vertex from the single source
     * or -1 if the vertex is not reachable from the source. 
     */
  public int[] getShortestPaths(Graph graph, int source) {
    // TODO: Implement getShortstPaths()
		int numOfVertices = graph.getNumberOfVertices();
		int[][] weights = graph.getEdgeMatrix();
		int[] seen = new int[numOfVertices];
		int[] distance = new int[numOfVertices];
		Arrays.fill(distance, -1);
		Arrays.fill(seen, 0);

 		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
 		pq.add(new Edge(source, 0));
		distance[source] = 0;
		
 		while (!pq.isEmpty()) {			
			int vertex = pq.remove().vertex;
			if (seen[vertex] != 0) {
				continue;
			}
			seen[vertex] = 1;

			for (int i = 0; i < numOfVertices; i++) {
				int weight = weights[vertex][i];
				if (seen[i] != 1 && weight != 0) {
					if (distance[i] == -1 || distance[i] > weight + distance[vertex]) {
						distance[i] = weight + distance[vertex];
						pq.add(new Edge(i, distance[i]));
					}
				}
			}
		}
 		return distance;
	}
	
	private class Edge implements Comparable<Edge>{
		public int vertex;
		public int weight;
		
		public Edge(int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
		
		public int compareTo(Edge current) {
			int currentWeight = current.weight;
			
			if(weight > currentWeight){
				return 1;
			}
			else if(weight < currentWeight){
				return -1;
			}
			else return 0;
		}
  }
}
