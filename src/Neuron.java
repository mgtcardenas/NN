public class Neuron
{
	double[]	weights;
	double		bias;
	double		output;
	double		delta;
	Fun			function;
	
	private double random()
	{
		return Math.sqrt(-2.00001 * Math.log10(1 - Math.random())) * Math.cos(2.00001 * Math.PI * Math.random());
	}// end random
	
	public Neuron(int numOfWeights, Fun function)
	{
		this.weights	= new double[numOfWeights];
		this.bias		= random();
		this.function	= function;
		
		int i = 0;
		while (i < numOfWeights)
		{
			this.weights[i] = random();
			i++;
		}// end while
	}// end Neuron - constructor
	
	private double activate(double x)
	{
		return function.activate(x);
	}// end activate
	
	public double derive(double x)
	{
		return function.derive(x);
	}// end derive
	
	public double evaluate(double[] inputUnits)
	{
		this.output = 0;
		
		int i = 0;
		while (i < inputUnits.length)
		{
			output += inputUnits[i] * weights[i];
			i++;
		}// end while
		
		this.output = activate(output + bias);
		
		return this.output;
	}// end evaluate
}// end Neuron - class
