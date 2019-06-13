import java.util.List;
import java.util.Random;

public class Runner
{
	Layer[]			layers;
	List<double[]>	inputs;
	List<double[]>	targets;
	double			lambda;
	double[]		output;
	
	static Random	r	= new Random(System.currentTimeMillis());
	
	public Runner(List<double[]> inputs, List<double[]> targets, double lambda, Layer... layers)
	{
		this.inputs		= inputs;
		this.targets	= targets;
		this.lambda		= lambda;
		this.layers		= layers;
	}// end Runner - Constructor
	
	public double run()
	{
		double	MSE	= 0;
		int		i	= 0;
		while (i < inputs.size())
		{
			forward(inputs.get(i));
			MSE += layers[layers.length - 1].costFunction(targets.get(i));
			
			int j = layers.length - 2;
			while (j >= 0)
			{
				layers[j].computeDeltaError(layers[j + 1]);
				j--;
			}// end while - j
			
			j = 0;
			while (j < layers.length)
			{
				layers[j].adjustWeights(lambda);
				j++;
			}// end while - j
			
			i++;
		}// end while
		
		return MSE;
	}// end run
	
	public double[] forward(double[] input)
	{
		switch (layers.length)
		{
			case 2:
				return layers[1].forward(layers[0].forward(input));
			case 3:
				return layers[2].forward(layers[1].forward(layers[0].forward(input)));
			case 4:
				return layers[3].forward(layers[2].forward(layers[1].forward(layers[0].forward(input))));
			default:
				int i = 1;
				output = layers[0].forward(input);
				while (i < layers.length)
				{
					output = layers[i].forward(output);
					i++;
				}// end while
				return output;
		}// end switch layers.length
	}// end forward
	
	public static double random()
	{
		return Math.sqrt(-2.00001 * Math.log10(1 - r.nextDouble())) * Math.cos(2.00001 * Math.PI * r.nextDouble());
	}// end random
}// end Runner - class
