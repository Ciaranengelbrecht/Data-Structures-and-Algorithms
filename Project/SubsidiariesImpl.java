// Ciaran Engelbrecht (23169641)

/**
 * An implementation of the Subsidiaries problem from the 2022 CITS2200 Project
 */
public class SubsidiariesImpl implements Subsidiaries {
  /**
   * {@inheritdoc}
   */

  /**
   * Uses the merge sort algorithm to calculate the amount if fines to be returned
   * @param owners is the array of owners of companies
   * @param queiries is the array of payers and payees to be processed
   * @return the smallest shared owner of both companies
   */ 
  public int[] sharedOwners(int[] owners, Query[] queries) {
    int[] result = new int[queries.length];

    for (int i = 0; i<queries.length; i++){ 
      Query query = queries[i];
      int payee = query.payee;
      int payer = query.payer;
      int temp1=payer;
      int temp2=payee;
      search: {
        while(temp1 != -1){
          temp1=owners[temp1];
          while(temp2 != -1){
          temp2 = owners[temp2];
            if(temp2 == temp1 || temp2 == payer){
              result[i] = temp2;
             break search;
            }
            else if(temp1 == payee){
              result[i] = payee;
             break search;
            }
          else result[i]=-1;
          } temp2 = payee;
        }   
      }   
      if(owners[payer]==owners[payee]){result[i]=owners[payee];}
      if(payee == payer){result[i]=payee;}
    }
  return result;}
}