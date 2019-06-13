import java.util.List;

public class Runner
{
	Layer[]			layers;
	List<double[]>	inputs;
	List<double[]>	targets;
	double			lambda;
	
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
			case 1:
				return layers[0].forward(input);
			case 2:
				return layers[1].forward(layers[0].forward(input));
			case 3:
				return layers[2].forward(layers[1].forward(layers[0].forward(input)));
			case 4:
				return layers[3].forward(layers[2].forward(layers[1].forward(layers[0].forward(input))));
			default:
				System.out.println("COMO ES LA FUNCION RECURSIVA?");
				return null;
		}// end switch layers.length
	}// end forward
}// end Runner - class
