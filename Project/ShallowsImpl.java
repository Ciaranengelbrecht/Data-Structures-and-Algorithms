// Ciaran Engelbrecht (23169641)
import java.util.*;

/**
 * An implementation of the Shallows problem from the 2022 CITS2200 Project
 */
public class ShallowsImpl implements Shallows {
  /**
   * {@inheritdoc}
   */

    static class Node {
        int vert, depth;
        Node(int v, int w){
            vert = v;
            depth = w;
        }
        int getVert(){ 
          return vert; 
        }
        int getDepth(){ 
          return depth; 
        }
    }
  
  /**
   * Uses Dijkstra's greedy algorithm to process all the paths and calculate the route with the greatest depth allowance
   * with a priority queue to store these values
   * @param ports the amount of ports in the route
   * @param graph is the graph of the nodes of each port and their depth
   * @param origin is the port which the route will begin at
   * @return the minimum depth of the best route with the greatest overall depth
   */  
    public static int[] dijkstra(int ports, ArrayList<ArrayList<Node>> graph, int origin){
        int[] distance = new int[ports];
        Arrays.fill(distance, 0);
        distance[origin] = Integer.MAX_VALUE;
  
        PriorityQueue<Node> pq = new PriorityQueue<>((vert1, vert2) -> vert2.getDepth() - vert1.getDepth());
        pq.add(new Node(origin, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            for (Node n : graph.get(current.getVert())) {
                if (distance[current.getVert()] > distance[n.getVert()] && n.getDepth() > distance[n.getVert()]) {
                    distance[n.getVert()] = Math.min(n.getDepth(),distance[current.getVert()]);
                    pq.add(new Node(n.getVert(),distance[n.getVert()]));
                }           
            }
        }
        return distance;
    }
  /**
   * Processes the given lanes with their allocated depths to return the path with the greatest overall depth
   * @param ports is the amount of ports to be stopped at in a given path
   * @param lanes is the array of lanes with the depth of each lane
   * @param origin is the port which the route begins
   * @return array of the best path's depth values
   */
    public int[] maximumDraughts(int ports, Lane[] lanes, int origin) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < ports; i++) {
            graph.add(new ArrayList<>());
        }
      for(Lane lane : lanes){
        int depart = lane.depart;
        int arrive = lane.arrive;
        int depth = lane.depth;
        graph.get(depart).add(new Node(arrive, depth));
      }
      int[] distance = dijkstra(ports, graph, origin);
      return distance;
    }
}