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
		}// end while - i
		System.out.println(" ]");
	}// end printHorizontal
	
	public static String getStringHorizontal(float[] vector)
	{
		String	s	= "[ ";
		int		i	= 0;
		while (i < vector.length)
		{
			s += vector[i] + (i == vector.length - 1 ? "" : ", ");
			i++;
		}// end while - i
		s += " ]";
		return s;
	}// end getStringHorizontal
}// end Aid - class
