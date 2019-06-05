import java.util.ArrayList;
import java.util.List;

public class TestNN
{
	static double[]			output		= new double[1];
	static Layer			firstLayer	= new Layer(2, 7, new Sigmoid());
	static Layer			secondLayer	= new Layer(7, 1, new Sigmoid());
	static List<double[]>	inputs		= new ArrayList<>();
	static List<double[]>	targets		= new ArrayList<>();
	
	public static void main(String[] args)
	{
		prepare();
		
		int i = 0;
		while (i < 100000)
		{
			System.out.print("LOOP " + i + ": ");
			testBackward();
			i++;
		}// end while
		
		i = 0;
		while (i < inputs.size())
		{
			System.out.print("Input: ");
			Aid.printHorizontal(inputs.get(i));
			output = secondLayer.forward(firstLayer.forward(inputs.get(i)));
			System.out.print("Output ");
			Aid.printHorizontal(output);
			i++;
		}// end while
	}// end main
	
	private static void prepare()
	{
		inputs.add(new double[] { 0, 0 });
		inputs.add(new double[] { 1, 1 });
		inputs.add(new double[] { 0, 1 });
		inputs.add(new double[] { 1, 0 });
		
		targets.add(new double[] { 1 });
		targets.add(new double[] { 1 });
		targets.add(new double[] { 0 });
		targets.add(new double[] { 0 });
	}// end prepare
	
	private static void testBackward()
	{
		double	MSE	= 0;
		
		int		i	= 0;
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
}// end TestNN - class
