public class Layer
{
	private Neuron[]	neurons;
	public double[]		input;
	
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
		input = inputUnits;
		
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
	 * Calculate and return the general error (delta)
	 * which is just the difference between the output and the desired output
	 * times the derivative of the activation function.
	 * Finally, return the MSE (Mean Square Error)
	 * 
	 * @param  desiredOutput - what we wanted to obtain
	 * @return El Mean Square Error
	 */
	public double costFunction(double[] desiredOutput)
	{
		double	error;
		double	MSE;
		
		MSE = 0;
		
		int i = 0;
		while (i < desiredOutput.length)
		{
			error				= neurons[i].getOutput() - desiredOutput[i];
			neurons[i].delta	= error * Neuron.activationFunctionDerivative(neurons[i].getOutput());
			MSE					+= error * error * 0.5;
			i++;
		}// end while
		
		return MSE;
	}// end costFunction
	
	/**
	 * Calculates the general error (delta) for all the layers using the nextLayers after them
	 * @param nextLayer - the layer after this one
	 */
	public void computeDeltaError(Layer nextLayer)
	{
		int l1 = 0;
		while (l1 < this.neurons.length)
		{
			int l2 = 0;
			while (l2 < nextLayer.getNeurons().length)
			{
				neurons[l1].delta += nextLayer.getNeurons()[l2].getWeights()[l1] * nextLayer.getNeurons()[l2].getDelta();
				l2++;
			}// end while - j
			
			neurons[l1].delta *= Neuron.activationFunctionDerivative(neurons[l1].getOutput());
			l1++;
		}// end while - i
	}// end computeDeltaError
	
	/**
	 * Adjust the weights of the whole neural network
	 * Lambda is a value between 0 and 1
	 *
	 * @param lambda - a hyperparameter which dictates how big the adjustments are
	 */
	public void adjustWeights(double lambda)
	{
		int i = 0;
		while (i < neurons.length)
		{
			neurons[i].bias -= neurons[i].delta * lambda;
			
			int j = 0;
			while (j < neurons[i].getWeights().length)
			{
				neurons[i].getWeights()[j] -= neurons[i].delta * lambda * input[j];
				j++;
			}// end while - j
			
			neurons[i].delta = 0;
			i++;
		}// end while - i
	}// end adjustWeights
}// end Layer - class
