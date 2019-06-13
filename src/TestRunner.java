import java.util.ArrayList;
import java.util.List;

public class TestRunner
{
	static Layer			firstLayer	= new Layer(2, 7, new Sigmoid());
	static Layer			secondLayer	= new Layer(7, 1, new Sigmoid());
	static List<double[]>	inputs		= new ArrayList<>();
	static List<double[]>	targets		= new ArrayList<>();
	
	public static void main(String[] args)
	{
		prepare();
		
		double	MSE		= 0;
		Runner	runner	= new Runner(inputs, targets, 0.1, firstLayer, secondLayer);
		int		i		= 0;
		while (i < 100000)
		{
			MSE = runner.run();
			if (i % 10000 == 0)
				System.out.println("LOOP " + i + ": " + MSE / inputs.size() * 100);
			i++;
		}// end while
		
		i = 0;
		while (i < inputs.size())
		{
			System.out.print("Input:\t");
			Aid.printHorizontal(inputs.get(i));
			System.out.print("Output:\t");
			Aid.printHorizontal(runner.forward(inputs.get(i)));
			i++;
		}// end while
	}// end main
	
	private static void prepare()
	{
		inputs.add(new double[] { 0, 0 });
		inputs.add(new double[] { 1, 1 });
		inputs.add(new double[] { 0, 1 });
		inputs.add(new double[] { 1, 0 });
		
		targets.add(new double[] { 1 });
		targets.add(new double[] { 1 });
		targets.add(new double[] { 0 });
		targets.add(new double[] { 0 });
	}// end prepare
}// end TestRunner - class
