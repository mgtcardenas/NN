import java.util.ArrayList;
import java.util.List;

/**
 * Only 7 examples & 500 iterations/epochs you should be able to deduce the formula
 * for calculating celsius to fahrenheit
 * 
 * X * 1.8 + 32 is the formula to calculate it
 * 
 * In the end, test the forward with 100, it should be 212
 * 
 * You must have 3 ls
 * 1 input & 4 outputs, 4 inputs & 4 outputs, 4 inputs & 1 output
 * in that order...
 */
public class Celsius
{
	static float[]			output		= new float[1];
	static Layer			firstLayer	= new Layer(1, 4, new Lineal());
	static Layer			secondLayer	= new Layer(4, 4, new Lineal());
	static Layer			thirdLayer	= new Layer(4, 1, new Lineal());
	static List<float[]>	inputs		= new ArrayList<>();
	static List<float[]>	targets		= new ArrayList<>();
	
	public static void main(String[] args)
	{
		float error;
		
		prepare();
		
		int i = 0; // Traning
		while (i < 500)
		{
			error = testBackward();
			if (i % 10 == 0)
				System.out.println("LOOP " + i + ": " + error);
			i++;
		}// end while
		
		float[] input = new float[] { 100 };
		System.out.print("Input: [ 100 ] ");
		output = thirdLayer.forward(secondLayer.forward(firstLayer.forward(input)));
		System.out.print("Output ");
		Aid.printHorizontal(output);
	}// end main
	
	/**
	 * Prepare 7 examples (inputs & targets
	 */
	private static void prepare()
	{
		inputs.add(new float[] { -40 });
		inputs.add(new float[] { -10 });
		inputs.add(new float[] { 0 });
		inputs.add(new float[] { 8 });
		inputs.add(new float[] { 15 });
		inputs.add(new float[] { 22 });
		inputs.add(new float[] { 38 });
		
		targets.add(new float[] { -40 });
		targets.add(new float[] { 14 });
		targets.add(new float[] { 32 });
		targets.add(new float[] { 46.4F });
		targets.add(new float[] { 59 });
		targets.add(new float[] { 71.6F });
		targets.add(new float[] { 100.4F });
	}// end prepare
	
	private static float testBackward()
	{
		float	MSE	= 0;
		
		int		i	= 0;
		while (i < inputs.size())
		{
			output	= thirdLayer.forward(secondLayer.forward(firstLayer.forward(inputs.get(i))));
			MSE		+= thirdLayer.costFunction(targets.get(i));
			
			secondLayer.computeDeltaError(thirdLayer);
			firstLayer.computeDeltaError(secondLayer);
			
			firstLayer.adjustWeights(0.01F);
			secondLayer.adjustWeights(0.01F);
			thirdLayer.adjustWeights(0.01F);
			
			i++;
		}// end while
		
		return MSE / inputs.size() * 100;
	}// end testBackward
}// end Celsius - class
