import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FailedRing
{
	static double[]			output			= new double[1];
	static Layer			firstLayer		= new Layer(2, 30, new Sigmoid());
	static Layer			secondLayer		= new Layer(30, 1, new Sigmoid());
	static List<double[]>	inputs			= new ArrayList<>();
	static List<double[]>	targets			= new ArrayList<>();
	static List<Integer>	firstIndexes	= new ArrayList<>();
	static List<Integer>	secondIndexes	= new ArrayList<>();
	static JPanel			panel			= new JPanel();
	static JFrame			frame			= new JFrame("JPanel Example");
	static BufferedImage	img				= new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
	
	public static void main(String[] args) throws IOException
	{
		double		error	= Integer.MAX_VALUE;
		Graphics2D	g2d		= img.createGraphics();
		
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
		g2d.dispose();
		
		drawRedSemiRing(img);
		drawBlueSemiRing(img);
		
		for (int i = 0; i < inputs.size() / 2; i++)
			firstIndexes.add(i);
		
		for (int i = inputs.size() / 2 + 1; i < inputs.size(); i++)
			secondIndexes.add(i);
		
		ImageIO.write(img, "png", new File("semi-ring.png"));
		panel.add(new JLabel(new ImageIcon(img)));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}// end main
	
	private static void evaluateNN(BufferedImage img, double error, int epoch) throws IOException
	{
		int p, gray;
		for (int y = 0; y < 300; y++) // Go through every one of the 300 by 300
		{
			for (int x = 0; x < 300; x++)
			{
				gray	= (int) (output[0] * 255);
				p		= ((gray) << 16) | ((gray) << 8) | (gray);
				img.setRGB(x, y, p);
			}// end for - x
		}// end for - y
		
		redrawRings(img);
		ImageIO.write(img, "png", new File("semi-ring.png"));
		panel.getComponent(0).repaint();
		frame.setTitle("Epoch" + epoch + " - Error: " + error);
	}// end evaluateNN
	
	private static void redrawRings(BufferedImage img)
	{
		for (int i = 0; i < inputs.size(); i++)
			img.setRGB((int) inputs.get(i)[0], (int) inputs.get(i)[1], targets.get(i)[0] == -1 ? 255 << 16 : 255);
	}// end redrawPoints
	
	private static void drawRedSemiRing(BufferedImage img)
	{
		img.setRGB(75, 150, 255 << 16);
		inputs.add(new double[] { 75, 150 });
		targets.add(new double[] { -1 });
		
		img.setRGB(90, 120, 255 << 16);
		inputs.add(new double[] { 90, 120 });
		targets.add(new double[] { -1 });
		
		img.setRGB(125, 100, 255 << 16);
		inputs.add(new double[] { 125, 100 });
		targets.add(new double[] { -1 });
		
		img.setRGB(160, 120, 255 << 16);
		inputs.add(new double[] { 160, 120 });
		targets.add(new double[] { -1 });
		
		img.setRGB(175, 150, 255 << 16);
		inputs.add(new double[] { 175, 150 });
		targets.add(new double[] { -1 });
		
		for (int x = 80, y = 140; x < 90 && y > 120; x += 5, y -= 10)
		{
			img.setRGB(x, y, 255 << 16);
			setRandomClosePoints(img, x, y, 255 << 16);
		}// end for
		
		for (int x = 95, y = 115; x < 125 && y > 100; x += 10, y -= 5)
		{
			img.setRGB(x, y, 255 << 16);
			setRandomClosePoints(img, x, y, 255 << 16);
		}// end for
		
		for (int x = 135, y = 105; x < 160 && y < 150; x += 10, y += 5)
		{
			img.setRGB(x, y, 255 << 16);
			setRandomClosePoints(img, x, y, 255 << 16);
		}// end for
		
		for (int x = 165, y = 130; x < 175 && y < 150; x += 5, y += 10)
		{
			img.setRGB(x, y, 255 << 16);
			setRandomClosePoints(img, x, y, 255 << 16);
		}// end for
	}// end drawRedSemiRing
	
	private static void drawBlueSemiRing(BufferedImage img)
	{
		img.setRGB(125, 125, 255);
		inputs.add(new double[] { 125, 125 });
		targets.add(new double[] { 1 });
		
		img.setRGB(140, 155, 255);
		inputs.add(new double[] { 140, 155 });
		targets.add(new double[] { 1 });
		
		img.setRGB(175, 175, 255);
		inputs.add(new double[] { 175, 175 });
		targets.add(new double[] { 1 });
		
		img.setRGB(210, 155, 255);
		inputs.add(new double[] { 210, 155 });
		targets.add(new double[] { 1 });
		
		img.setRGB(225, 130, 255);
		inputs.add(new double[] { 225, 130 });
		targets.add(new double[] { 1 });
		
		for (int x = 130, y = 135; x < 140 && y < 155; x += 5, y += 10)
		{
			img.setRGB(x, y, 255);
			setRandomClosePoints(img, x, y, 255);
		}// end for
		
		for (int x = 150, y = 160; x < 175 && y < 175; x += 10, y += 5)
		{
			img.setRGB(x, y, 255);
			setRandomClosePoints(img, x, y, 255);
		}// end for
		
		for (int x = 185, y = 170; x < 210 && y > 155; x += 10, y -= 5)
		{
			img.setRGB(x, y, 255);
			setRandomClosePoints(img, x, y, 255);
		}// end for
		
		for (int x = 215, y = 145; x < 225 && y > 130; x += 5, y -= 10)
		{
			img.setRGB(x, y, 255);
			setRandomClosePoints(img, x, y, 255);
		}// end for
	}// end drawBlueSemiRing
	
	private static void setRandomClosePoints(BufferedImage img, int x, int y, int p)
	{
		int tmpX, tmpY, max, min;
		
		max	= 7;
		min	= -7;
		
		for (int i = 0; i < 50; i++)
		{
			tmpX	= x + (int) (Math.random() * ((max - min) + 1)) + min;
			tmpY	= y + (int) (Math.random() * ((max - min) + 1)) + min;
			img.setRGB(tmpX, tmpY, p);
			Color color = new Color(p);
			inputs.add(new double[] { tmpX, tmpY });
			targets.add(new double[] { color.getRed() == 255 ? -1 : 1 });
		}// end for - i
	}// end setRandomClosePoints
}// end FailedRing - class
