public interface Fun
{
	double activate(double x);
	
	double derive(double x);
}// end Fun - interface

enum ActFun
{
	SIGMOID, RELU, HTAN
}// end ACtivationFunc - enum

class Sigmoid implements Fun
{
	@Override
	public double activate(double x)
	{
		return 1 / (1 + Math.exp(-x));
	}// end activate
	
	@Override
	public double derive(double x)
	{
		return x * (1 - x);
	}// end derive
}// end Sigmoid - class

class Relu implements Fun
{
	@Override
	public double activate(double x)
	{
		return Math.max(0, x);
	}// end activate
	
	@Override
	public double derive(double x)
	{
		return x > 0 ? 1 : 0;
	}// end derive
}// end Relu - class

// TODO: Ask Omar for his HTan activation function for the semiring exercise
class HTan implements Fun
{
	@Override
	public double activate(double x)
	{
		return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
	}// end activate
	
	@Override
	public double derive(double x)
	{
		return 1 - activate(x) * activate(x);
	}// end derive
}// end HTan - class

class Lineal implements Fun
{
	@Override
	public double activate(double x)
	{
		return 0.1 * x;
	}// end activate
	
	@Override
	public double derive(double x)
	{
		return 0.1;
	}// end derive
}// end Lineal - class
