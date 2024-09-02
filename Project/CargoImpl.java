// Ciaran Engelbrecht (23169641)
/**
 * An implementation of the Cargo problem from the 2022 CITS2200 Project
 */
public class CargoImpl implements Cargo {
  /**
   * {@inheritdoc}
   */
  /**
     * Iterates through all of the queries to update the departure mass for each stop
     * @param stops is amount of stops which will be in the route
     * @param queries is departure stop, the arrival stop and the mass to be updated at each stop inbetween
     * @return the departure mass
     */
  public int[] departureMasses(int stops, Query[] queries) {
    int result[] = new int[queries.length]; 
    int route[] = new int[stops]; 
    for (int i = 0; i<queries.length; i++){
      Query query = queries[i];
      int cargoMass = query.cargoMass;
      int collect = query.collect;
      int deliver = query.deliver;
        for (int j = collect; j<deliver; j++){
        route[j] += cargoMass;
        }
      result[i]= route[collect];
    }
      return result;
  }
}

