public interface Lock {
	public boolean myMethod1(int myParam);
	public boolean myMethod2();
	public boolean myMethod3(int myParam, int myParam2);
	}

public interface CombinationLock {

  public returnType open (int number 1, number 2, number 3)

  public returnType close (int number 1, number 2, number 3);   

  public returnType changeCombo (int number 1, number 2, number 3);   

	private int combinationNumber1 = 0;

    private int combinationNumber2 = 0;

    private int combinationNumber3 = 0;
}
CombinationLock(int combinationNumber1, int combinationNumber2, int combinationNumber3){

	this.combinationNumber1 = combinationNumber1;

	this.combinationNumber2 = combinationNumber2;

	this.combinationNumber3 = combinationNumber3;

}

public boolean open(int number1, int number2, int number3){

	if(number1 == combinationNumber1 && number2 == combinationNumber2 && number3 == combinationNumber3)

			return true;

	else

			return false;

}
public boolean changeCombo(int number1, int number2, int number3, int newNumber1, int newNumber2, int newNumber3){

       

	if (open(number1, number2, number3)){

			combinationNumber1 = newNumber1;

			combinationNumber2 = newNumber2;

			combinationNumber3 = newNumber3;

			return true;

	}else

			return false;

}