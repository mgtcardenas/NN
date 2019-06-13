public class Neuron
{
	float[]	weights;
	float		bias;
	float		output;
	float		delta;
	Fun			function;
	
	public Neuron(int numOfWeights, Fun function)
	{
		this.weights	= new float[numOfWeights];
		this.bias		= Runner.random();
		this.function	= function;
		
		int i = 0;
		while (i < numOfWeights)
		{
			this.weights[i] = Runner.random();
			i++;
		}// end while
	}// end Neuron - constructor
	
	private float activate(float x)
	{
		return function.activate(x);
	}// end activate
	
	public float derive(float x)
	{
		return function.derive(x);
	}// end derive
	
	public float evaluate(float[] inputUnits)
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
