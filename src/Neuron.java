public class Neuron
{
	double[]	weights;
	double		bias;
	double		output;
	double		delta;
	Fun			function;
	
	public Neuron(int numOfWeights, Fun function)
	{
		this.weights	= new double[numOfWeights];
		this.bias		= Runner.random();
		this.function	= function;
		
		int i = 0;
		while (i < numOfWeights)
		{
			this.weights[i] = Runner.random();
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
