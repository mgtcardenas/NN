public class Main
{
	private static final int SIZE = 3;
	
	public static void main(String[] args)
	{
		testForward();
	}// end main
	
	private static void test()
	{
		double[]	inputUnits	= new double[] { 5, 7, 9 };
		Neuron		n			= new Neuron(3);
		
		System.out.println(n.evaluate(inputUnits));
	}// end test
	
	private static void setWeights()
	{
	
	}// end setWeights
	
	private static void testForward()
	{
		double[]	inputUnits				= new double[] { 1, 1 };
		double[]	outputUnits				= new double[2];
		Layer		firstLayer				= new Layer(2, 3);
		Layer		secondLayer				= new Layer(3, 1);
		
		firstLayer.getNeurons()[0].setWeights(new double[]{0.712, 0.112});
		firstLayer.getNeurons()[1].setWeights(new double[]{0.355, 0.855});
		firstLayer.getNeurons()[2].setWeights(new double[]{0.268, 0.468});
		
		secondLayer.getNeurons()[0].setWeights(new double[]{0.116, 0.329, 0.708});
		
		double[] firstLayerOutput = firstLayer.forward(inputUnits);
		
		outputUnits = secondLayer.forward(firstLayerOutput);
		
		for (int i = 0; i < outputUnits.length; i++)
			System.out.println("Output unit " + i + ": " + outputUnits[i]);
	}// end testForward
	
	private static void randomTest()
	{
		double[]	inputUnits	= new double[SIZE];
		double[]	outputUnits	= new double[10];
		
		for (int i = 0; i < SIZE; i++)
			inputUnits[i] = Math.random();
		
		Layer sl = new Layer(10, SIZE);
		
		outputUnits = sl.forward(inputUnits);
		
		for (int i = 0; i < outputUnits.length; i++)
			System.out.println("Output unit " + i + ": " + outputUnits[i]);
	}// end randomTest
}// end Main - class
