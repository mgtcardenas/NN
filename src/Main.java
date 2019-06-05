import java.util.ArrayList;
import java.util.List;

public class Main
{
	private static double[]	output		= new double[1];
	private static Layer	firstLayer	= new Layer(2, 7, new Sigmoid());
	private static Layer	secondLayer	= new Layer(7, 1, new Sigmoid());
	static List<double[]>	inputs		= new ArrayList<>();
	static List<double[]>	targets		= new ArrayList<>();
	
	public static void main(String[] args)
	{
		int i = 0;
		while (i < 5000)
		{
			System.out.println("LOOP " + i);
			testBackward();
			i++;
		}// end while
		
		i = 0;
		while (i < inputs.size())
		{
			System.out.print("Input ");
			int j = 0;
			while (j < inputs.get(i).length)
			{
				System.out.print(inputs.get(i)[j] + " ");
				j++;
			}// end while
			
			output = secondLayer.forward(firstLayer.forward(inputs.get(i)));
			
			System.out.print("Output ");
			j = 0;
			while (j < output.length)
			{
				System.out.print(output[j]);
				j++;
			}// end while
			
			System.out.println();
			i++;
		}// end while
	}// end main
	
	private static void testBackward()
	{
		double MSE = 0;
		
		inputs.add(new double[] { 0, 0 });
		inputs.add(new double[] { 1, 1 });
		inputs.add(new double[] { 0, 1 });
		inputs.add(new double[] { 1, 0 });
		
		targets.add(new double[] { 1 });
		targets.add(new double[] { 1 });
		targets.add(new double[] { 0 });
		targets.add(new double[] { 0 });
		
		int i = 0;
		while (i < inputs.size())
		{
			output	= secondLayer.forward(firstLayer.forward(inputs.get(i)));
			MSE		+= secondLayer.costFunction(targets.get(i));
			firstLayer.computeDeltaError(secondLayer);
			
			firstLayer.adjustWeights(0.1);
			secondLayer.adjustWeights(0.1);
			
			i++;
		}// end while
		
		System.out.println("El error fue " + MSE / inputs.size() * 100);
	}// end testBackward
}// end Main - class
