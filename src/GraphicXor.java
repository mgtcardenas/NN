import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GraphicXor
{
	static Layer			firstLayer	= new Layer(2, 2, new HyperbolicTangent());
	static Layer			secondLayer	= new Layer(2, 1, new HyperbolicTangent());
	static List<float[]>	inputs		= new ArrayList<>();
	static List<float[]>	targets		= new ArrayList<>();
	static JPanel			panel		= new JPanel();
	static JFrame			frame		= new JFrame();
	static BufferedImage	bI			= new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
	
	public static void main(String[] args)
	{
		prepare();
		
		// Img
		panel.add(new JLabel(new ImageIcon(bI)));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// NN
		float	error	= Float.MAX_VALUE;
		Runner	runner	= new Runner(inputs, targets, 4, firstLayer, secondLayer);
		int		epoch	= 0;
		while (error > 0.001)
		{
			error = runner.epoch(0.001F);
			if (epoch % 100 == 0)
				evaluateNN(runner, error, epoch);
			epoch++;
		}// end while
	}// end main
	
	private static void evaluateNN(Runner runner, double error, int epoch)
	{
		int		p, gray;
		float[]	output;
		
		for (int y = -149; y <= 150; y++) // Go through every one of the 300 by 300
		{
			for (int x = -150; x < 150; x++)
			{
				output = runner.forward(new float[] { x, y });
				Aid.normalizeOutput(output, -1, 1, 0, 1);
				gray	= (int) (output[0] * 255);
				p		= ((gray) << 16) | ((gray) << 8) | (gray);
				bI.setRGB(150 + x, 150 - y, p);
			}// end for - x
		}// end for - y
		
		redrawDots();
		panel.getComponent(0).repaint();
		frame.setTitle("Epoch " + epoch + " - Error: " + error);
	}// end evaluateNN
	
	private static void redrawDots()
	{
		for (int i = 0; i < inputs.size(); i++)
			bI.setRGB(150 + (int) inputs.get(i)[0], 150 - (int) inputs.get(i)[1], targets.get(i)[0] != 0 ? 255 << 16 : 255);
	}// end redrawPoints
	
	private static void drawCartesianPoint(int x, int y, int rgb)
	{
		bI.setRGB(150 + x, 150 - y, rgb);
		inputs.add(new float[] { x, y });
		targets.add(new float[] { rgb == 255 ? 0 : 1 });
	}// end drawCartesianPoint
	
	private static void prepare()
	{
		drawCartesianPoint(-1, -1, 255 << 16);
		drawCartesianPoint(1, 1, 255 << 16);
		drawCartesianPoint(-1, 1, 255);
		drawCartesianPoint(1, -1, 255);
	}// end prepare
}// end GraphicXor - class
