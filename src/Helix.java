import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Helix
{
	static Layer			firstLayer	= new Layer(2, 20, new Sigmoid());
	static Layer			secondLayer	= new Layer(20, 20, new Sigmoid());
	static Layer			thirdLayer	= new Layer(20, 1, new Sigmoid());
	static List<float[]>	inputs		= new ArrayList<>();
	static List<float[]>	targets		= new ArrayList<>();
	static JPanel			panel		= new JPanel();
	static JFrame			frame		= new JFrame();
	static BufferedImage	bI			= new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
	
	public static void main(String[] args)
	{
		drawHelix(1, 1, 180, 255 << 16);
		drawHelix(-1, -1, 0, 255);
		
		// Img
		panel.add(new JLabel(new ImageIcon(bI)));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// NN
		float	error	= Float.MAX_VALUE;
		Runner	runner	= new Runner(inputs, targets, (int) (inputs.size() * 0.1), firstLayer, secondLayer, thirdLayer);
		int		epoch	= 0;
		while (error > 0.01)
		{
			error = runner.epoch(0.01F);
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
				output	= runner.forward(new float[] { x, y });
				gray	= (int) (output[0] * 255);
				p		= ((gray) << 24) | ((gray) << 16) | (gray << 8) | gray;
				bI.setRGB(150 + x, 150 - y, p);
			}// end for - x
		}// end for - y
		
		redrawRings();
		panel.getComponent(0).repaint();
		frame.setTitle("Epoch " + epoch + " - Error: " + error);
	}// end evaluateNN
	
	// region Circles
	private static void redrawRings()
	{
		for (int i = 0; i < inputs.size(); i++)
			bI.setRGB(150 + (int) inputs.get(i)[0], 150 - (int) inputs.get(i)[1], targets.get(i)[0] != 0 ? 255 << 16 : 255);
	}// end redrawPoints
	
	private static void drawHelix(int x, int y, double initialAngle, int rgb)
	{
		double x1, y1, r, angle;
		
		r		= 0;
		angle	= initialAngle;
		while (angle <= (initialAngle + 360 * 2))
		{
			x1	= r * Math.cos(angle * Math.PI / 180);
			y1	= r * Math.sin(angle * Math.PI / 180);
			drawCartesianPoint((int) Math.round(x + x1), (int) Math.round(y + y1), rgb);
			angle += 10;
			r++;
		}// end while
		
	}// end drawHelix
	
	private static void drawCartesianPoint(int x, int y, int rgb)
	{
		bI.setRGB(150 + x, 150 - y, rgb);
		inputs.add(new float[] { x, y });
		targets.add(new float[] { rgb == 255 ? 0 : 1 });
	}// end drawCartesianPoint
		// endregion Circles
}// end Helix - class
