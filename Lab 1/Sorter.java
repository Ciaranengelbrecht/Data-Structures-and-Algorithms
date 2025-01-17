//Student ID: 23169641 - Ciaran Engelbrecht
import CITS2200.Sort;

/**
* A class to give a comaprative view of common sorting algorithms.
* The maintains a private static variable that counts the number 
* of array assignments that are performed (as an approximate measure
* of the complexity of the algorithm.
* @author Tim French. 
**/

public class Sorter implements Sort
{
   private int count;
   
   /**
	* Returns the number of array assignment operations 
	* performed by this class since the count variable was rest.
	* @return the number of assignments
	**/
	public int getCount(){
		return count;
	}	
		
	/**
	*Resets the counter variable to 0
	**/
	public void reset(){
		count = 0;
	}
	
	/**
	* Executes the insertion sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void insertionSort(long[] a)
    {
    for (int j =1; j < a.length; j++) {
      	long key = a[j];
        int i = j - 1;

        while (i >= 0 && a[i] > key) {
            a[i + 1] = a[i]; count++;
            i = i - 1;
        }
      	
				a[i + 1] = key; count++;
        }				
		//System.out.println(count);
	}

	
	/**
	* Executes the quick sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void quickSort(long[] a){
		quickSort(a, 0, a.length-1);
		//System.out.println(count);

	}
		

	public void quickSort(long[] a, int p, int r){
		if (p < r)  
    {  
        int q = partition(a, p, r);
        quickSort(a, p, q - 1);  
        quickSort(a, q + 1, r);  
    } 
	}

	private int partition (long[] a, int p, int r){
		long pivot = a[r];
		int i = (p - 1);
		for (int j = p; j <= r - 1; j++)  
    {  
        if (a[j] < pivot)  
        {  
            i++;  
            long swap = a[i];  
            a[i] = a[j]; count++;  
            a[j] = swap;  count++; 
        }  
    }  
    long swap = a[i+1];  
    a[i+1] = a[r];  count++; 
    a[r] = swap;  count++; 
    return (i + 1);  
}  

	
	
	/**
	* Executes the merge sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void mergeSort(long[] a){
	mergeSort(a, 0, a.length-1);
	//System.out.println(count);

	}
	
    	/**
	*A private method to merge the elements in the array between p and r.
	*the array a, between the indices p and r, inclusive.
	*Given the array is sorted between p and q and q+1 and r
	*sorts the array between p and r.
	*@param a the array to be sorted, which is mutated by the method
	*@param p the lower index of the range to be partitioned
	*@param q the midpoint of the two sorted sections.
	*@param r the upper index of the range to be paritioned
	*@return the index of the point of partition
	**/
	private void merge(long[] a, int p, int q, int r)
	{
		int n1 = q - p +1;
		int n2 = r-q;
    long leftArray[] = new long [n1];
    long rightArray[] = new long [n2];
		for (int i = 0; i<n1; i++){
			leftArray[i] = a[p + i];
		}
		for (int j = 0; j < n2; j++){    
			rightArray[j] = a[q + 1 + j]; 	
		}
		int i=0;
		int j=0;
		int k=p;
		while (i < n1 && j < n2) {
			if (leftArray[i] <= rightArray[j]){
				a[k] = leftArray[i]; i++; count++;
			}
			else {
				a[k] = rightArray[j]; j++;count++;
			}
			k++;
		}
		while (i<n1) {
			a[k]=leftArray[i]; i++; k++;count++;
		}
		while (j<n2){
			a[k] = rightArray[j]; j++; k++;count++;
		}

	}
	
   /**
   *Overloads the mergeSort method with parameters to set the 
   *range to be sorted.
   **/ 
	private void mergeSort(long[] a, int p, int r)
	{
		if (p<r){
			int q = (p+r)/2;
			mergeSort(a, p, q); 
			mergeSort(a, q + 1, r);  
			merge(a, p, q, r);  
		}
	}
	


  
  }