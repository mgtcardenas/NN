import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Semirings
{
	static Layer         firstLayer  = new Layer(2, 30, new Sigmoid());
	static Layer         secondLayer = new Layer(30, 1, new Sigmoid());
	static List<float[]> inputs      = new ArrayList<>();
	static List<float[]> targets     = new ArrayList<>();
	static JPanel        panel       = new JPanel();
	static JFrame        frame       = new JFrame("JPanel Example");
	static BufferedImage bI          = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
	
	public static void main(String[] args) throws IOException
	{
		prepare(-25, -5, 100, 0, 180, 255 << 16);
		prepare(25, 5, 100, 180, 180, 255);
		
		//
		// float error = Float.MAX_VALUE;
		// Runner runner = new Runner(inputs, targets, (int) (inputs.size() * 0.1), firstLayer, secondLayer);
		// int i = 0;
		// while (i < 100000)
		// {
		// error = runner.epoch(0.01F);
		// if (i % 100 == 0)
		// System.out.println("LOOP: " + i + " - Error: " + error);
		// i++;
		// }// end while
		
		ImageIO.write(bI, "png", new File("semi-ring.png"));
		panel.add(new JLabel(new ImageIcon(bI)));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	}// end main
	
	private static void redrawRings(BufferedImage img)
	{
		for (int i = 0; i < inputs.size(); i++)
			img.setRGB((int) inputs.get(i)[0], (int) inputs.get(i)[1], targets.get(i)[0] == -1 ? 255 << 16 : 255);
	}// end redrawPoints
	
	//region Circles
	private static void prepare(int x, int y, int numPoints, int initialAngle, int angleMagnitude, int rgb)
	{
		int i = 0;
		while (i < numPoints)
		{
			drawCircle(x, y, Math.random() * 20 + 30, initialAngle, angleMagnitude, rgb);
			i++;
		}// end while
	}// end prepare
	
	private static void drawCircle(int x, int y, double r, int initialAngle, int angleMagnitude, int rgb)
	{
		double x1, y1, angle;
		
		angle	= Math.random() * angleMagnitude + initialAngle;
		x1		= r * Math.cos(angle * Math.PI / 180);
		y1		= r * Math.sin(angle * Math.PI / 180);
		drawCartesianPoint((int) Math.round(x + x1), (int) Math.round(y + y1), rgb);
	}// end drawCircle
	
	private static void drawCartesianPoint(int x, int y, int rgb)
	{
		bI.setRGB(150 + x, 150 - y, rgb);
		inputs.add(new float[] { 150 + x, 150 - y });
		targets.add(new float[] { rgb == 255 ? 0 : 1 });
	}// end drawCartesianPoint
	
	private static void paintWhite()
	{
		Graphics2D g2d = bI.createGraphics();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, bI.getWidth(), bI.getHeight());
		
		g2d.dispose();
	}// end paintWhite
	//endregion Circles
}// end Semirings - class
