public abstract class Aid
{
	public static void printHorizontal(float[] vector)
	{
		System.out.print("[ ");
		int i = 0;
		while (i < vector.length)
		{
			System.out.print(vector[i] + (i == vector.length - 1 ? "" : ", "));
			i++;
		}// end while
		System.out.println(" ]");
	}// end printHorizontal
}// end Aid - class
