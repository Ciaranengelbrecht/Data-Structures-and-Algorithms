// Ciaran Engelbrecht (23169641)
import java.util.*;
/**
 * An implementation of the Fines problem from the 2022 CITS2200 Project
 */
public class FinesImpl implements Fines {
  /**
   * {@inheritdoc}
   */
  long fineCount = 0;
  int[] count;    

  /**
   * Uses the merge sort algorithm to calculate the amount if fines to be returned
   * @param priorities is the array of ships in order (incorrect or otherwise), their value being their prioirity
   * @return the number of fines to be handed out
   */
  public long countFines(int[] priorities) {
    count = new int[priorities.length];
    int[] index = new int[priorities.length];
    for(int i=0;i<priorities.length;i++) index[i]=i;

    mergeSort(priorities,index,0,priorities.length-1);

    for (int value : count) {
      fineCount += value;
    }
    return fineCount;
  }
 /**
  *A  method to merge the elements in the array between p and r, the array a, between the indices p and r, inclusive
	*Given the array is sorted between p and q and q+1 and r, sorts the array between p and r
	*@param arr the array to be sorted, which is mutated by the method
  *@param index the array of @param arr indexes to be sorted
	*@param p the lower index of the range to be partitioned
	*@param q the midpoint of the two sorted sections.
	*@param r the upper index of the range to be paritioned
	*@return the index of the point of partition
	**/
  void merge(int[] arr, int[] index, int p, int q, int r){
    int leftArraySize = q-p+1;
    int rightArraySize = r-q;
    int[] leftArray = new int[leftArraySize];
    int[] rightArray = new int[rightArraySize];

    for(int i = 0; i <leftArraySize; i++){
        leftArray[i]=index[p+i];
    }
    for(int j = 0; j < rightArraySize; j++){
        rightArray[j]=index[j+q+1];
    }
    int i=0, j=0, k=p;
    int rightCount=0;
    while(i< leftArraySize && j < rightArraySize){
        if(arr[leftArray[i]]>= arr[rightArray[j]]){
            index[k] = leftArray[i];
            count[leftArray[i]] += rightCount;
            i++;
        }else{
            index[k] = rightArray[j];
            j++;
            rightCount++;
        }
        k++;
    }
    while(i < leftArraySize){
        index[k] = leftArray[i];
        count[leftArray[i]] += rightCount;
        i++;
        k++;
    }
    while (j < rightArraySize){
        index[k] = rightArray[j];
        j++;
        k++;
    }
  }
/**
	* Executes the merge sort algorithm
	* There is no return as the parameter is to be mutated
	* @param a the array of long integers to be sorted
  * @param index is the array of @param a indexes to be sorted
	**/
  void mergeSort(int[] a, int[] index, int p, int r){
    if(p<r){
        int q=(p+r)/2;
        mergeSort(a,index,p,q);
        mergeSort(a,index,q+1,r);
        merge(a,index,p,q,r);
    }
  }
}