public class Layer
{
	Neuron[]	neurons;
	double[]	input;
	
	public Layer(int numWeights, int numNeurons, Fun function)
	{
		this.neurons = new Neuron[numNeurons];
		
		int i = 0;
		while (i < numNeurons)
		{
			this.neurons[i] = new Neuron(numWeights, function);
			i++;
		}// end while
	}// end Layer - constructor
	
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
	 * @return               El Mean Square Error
	 */
	public double costFunction(double[] desiredOutput)
	{
		double	error;
		double	MSE;
		
		MSE = 0;
		
		int i = 0;
		while (i < desiredOutput.length)
		{
			error				= neurons[i].output - desiredOutput[i];
			neurons[i].delta	= error * neurons[i].derive(neurons[i].output);
			MSE					+= error * error * 0.5;
			i++;
		}// end while
		
		return MSE;
	}// end costFunction
	
	/**
	 * Calculates the general error (delta) for all the ls using the nextLayers after them
	 * 
	 * @param nextLayer - the layer after this one
	 */
	public void computeDeltaError(Layer nextLayer)
	{
		int l1 = 0;
		while (l1 < this.neurons.length)
		{
			int l2 = 0;
			while (l2 < nextLayer.neurons.length)
			{
				neurons[l1].delta += nextLayer.neurons[l2].weights[l1] * nextLayer.neurons[l2].delta;
				l2++;
			}// end while - j
			
			neurons[l1].delta *= neurons[l1].derive(neurons[l1].output);
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
			while (j < neurons[i].weights.length)
			{
				neurons[i].weights[j] -= neurons[i].delta * lambda * input[j];
				j++;
			}// end while - j
			
			neurons[i].delta = 0;
			i++;
		}// end while - i
	}// end adjustWeights
}// end Layer - class
