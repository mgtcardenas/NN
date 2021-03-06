import java.util.ArrayList;
import java.util.List;

public class Xor
{
	static Layer			firstLayer	= new Layer(2, 7, new Sigmoid());
	static Layer			secondLayer	= new Layer(7, 1, new Sigmoid());
	static List<float[]>	inputs		= new ArrayList<>();
	static List<float[]>	targets		= new ArrayList<>();
	
	public static void main(String[] args)
	{
		prepare();
		
		float	error	= 0;
		Runner	runner	= new Runner(inputs, targets, 1, firstLayer, secondLayer);
		int		i		= 0;
		while (i < 100000)
		{
			error = runner.epoch(0.1F);
			if (i % 100 == 0)
				System.out.println("LOOP: " + i + " - Error: " + error);
			i++;
		}// end while
		
		i = 0;
		while (i < inputs.size())
		{
			System.out.println("Input \t - " + Aid.getStringHorizontal(inputs.get(i)));
			System.out.println("Output \t - " + Aid.getStringHorizontal(runner.forward(inputs.get(i))));
			i++;
		}// end while
	}// end main
	
	private static void prepare()
	{
		inputs.add(new float[] { 0, 0 });
		inputs.add(new float[] { 1, 1 });
		inputs.add(new float[] { 0, 1 });
		inputs.add(new float[] { 1, 0 });
		
		targets.add(new float[] { 1 });
		targets.add(new float[] { 1 });
		targets.add(new float[] { 0 });
		targets.add(new float[] { 0 });
	}// end prepare
}// end Xor - class
