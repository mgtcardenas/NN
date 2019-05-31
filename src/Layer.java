public class Layer
{
	private Neuron[] neurons;
	
	public Layer(int numWeights, int numNeurons)
	{
		this.neurons = new Neuron[numNeurons];
		
		int i = 0;
		while (i < numNeurons)
		{
			this.neurons[i] = new Neuron(numWeights);
			i++;
		}// end while
	}// end Layer - constructor
	
	public Neuron[] getNeurons()
	{
		return neurons;
	}// end getNeurons
	
	public double[] forward(double[] inputUnits)
	{
		double[]	outputUnits	= new double[neurons.length];
		int			i			= 0;
		while (i < neurons.length)
		{
			outputUnits[i] = neurons[i].evaluate(inputUnits);
			i++;
		}// end while
		
		return outputUnits;
	}// end forward
	
	/**
	 * Calculate and return the general error (not the MSE = Mean Square Error)
	 * which is just the difference between the output and the desired output
	 * times the derivative of the activation function,
	 * 
	 * @param  desiredValue
	 * @return
	 */
	public double costFunction(double[] desiredValue)
	{
		return 0L;
	}// end costFunction
	
	public void computeDeltaError(Layer aLayer)
	{
		
	}// end computeDeltaError
	
	/**
	 * Lambda is a value between 0 and 1
	 * 
	 * @param lambda
	 */
	public void adjustWeights(double lambda)
	{
		
	}// end adjustWeights
}// end Layer - class
