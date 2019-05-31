public class Neuron
{
	private double[]	weights;
	private double		bias;
	private double		delta;
	private double		output;
	
	private double random()
	{
		return Math.sqrt(-2.00001 * Math.log10(1 - Math.random())) * Math.cos(2.00001 * Math.PI * Math.random());
	}// end random
	
	public Neuron(int numOfWeights)
	{
		this.weights = new double[numOfWeights];
		
		int i = 0;
		while (i < numOfWeights)
		{
			this.weights[i] = random();
			i++;
		}// end while
		
		this.bias = 0;
	}// end Neuron - constructor
	
	public double[] getWeights()
	{
		return weights;
	}// end getWeights
	
	public void setWeights(double[] weights)
	{
		this.weights = weights;
	}// end setWeights
	
	private double activationFunction(double x)
	{
		return 1 / (1 + Math.exp(-x));
	}// end activationFunction
	
	public double evaluate(double[] inputUnits)
	{
		this.output = 0;
		
		int i = 0;
		while (i < inputUnits.length)
		{
			output += inputUnits[i] * weights[i];
			i++;
		}// end while
		
		this.output = activationFunction(output) + bias;
		
		return this.output;
	}// end evaluate
}// end Neuron - class
