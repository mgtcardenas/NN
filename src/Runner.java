import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Runner
{
	Layer[]			ls;
	List<double[]>	inputs;
	List<double[]>	targets;
	List<Integer>	indexes;
	double			divs;
	double[]		output;
	double[]		input;
	double[]		target;
	double			error;
	
	static Random	r	= new Random(System.currentTimeMillis());
	
	public Runner(List<double[]> inputs, List<double[]> targets, int divs, Layer... ls)
	{
		this.inputs		= inputs;
		this.targets	= targets;
		this.divs		= divs;
		this.ls			= ls;
		this.error		= 0;
		
		this.indexes	= new ArrayList<>();
		int i = 0;
		while (i < inputs.size())
		{
			indexes.add(i, i);
			i++;
		}// end while
	}// end Runner - Constructor
	
	public double epoch(double lambda)
	{
		Collections.shuffle(indexes);
		error = run(lambda);
		
		return error;
	}// end epoch
	
	private double run(double lambda)
	{
		error = 0;
		int i = 0;
		while (i < inputs.size() / divs)
		{
			work(lambda, indexes.get(i));
			i++;
		}// end while
		
		error = 100 * (error / ((double) inputs.size() / divs));
		
		return error;
	}// end run
	
	private double work(double lambda, int index)
	{
		input	= inputs.get(index);
		target	= targets.get(index);
		
		output	= forward(input);
		error	+= ls[ls.length - 1].costFunction(target);
		
		computeDeltas();
		adjustWeights(lambda);
		
		return error;
	}// end work
	
	private void computeDeltas()
	{
		int l = 0;
		while (l < ls.length - 1)
		{
			ls[ls.length - l - 2].computeDeltaError(ls[ls.length - l - 1]);
			l++;
		}// end while
	}// end computeDeltas
	
	private void adjustWeights(double lambda)
	{
		int j = 0;
		while (j < ls.length)
		{
			ls[j].adjustWeights(lambda);
			j++;
		}// end while - j
	}// end adjustWeights
	
	public double[] forward(double[] input)
	{
		switch (ls.length)
		{
			case 2:
				return ls[1].forward(ls[0].forward(input));
			case 3:
				return ls[2].forward(ls[1].forward(ls[0].forward(input)));
			case 4:
				return ls[3].forward(ls[2].forward(ls[1].forward(ls[0].forward(input))));
			default:
				int i = 1;
				output = ls[0].forward(input);
				while (i < ls.length)
				{
					output = ls[i].forward(output);
					i++;
				}// end while
				return output;
		}// end switch ls.length
	}// end forward
	
	public static double random()
	{
		return Math.sqrt(-2.00001 * Math.log10(1 - r.nextDouble())) * Math.cos(2.00001 * Math.PI * r.nextDouble());
	}// end random
}// end Runner - class
