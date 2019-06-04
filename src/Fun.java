public interface Fun
{
	double activate(double x);
	
	double derivate(double x);
}// end Fun - class

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
	public double derivate(double x)
	{
		return x * (1 - x);
	}// end derivate
}// end Sigmoid

class Relu implements Fun
{
	@Override
	public double activate(double x)
	{
		return Math.max(0, x);
	}// end activate
	
	@Override
	public double derivate(double x)
	{
		return x > 0 ? 1 : 0;
	}// end derivate
}// end Relu

class HTan implements Fun
{
	@Override
	public double activate(double x)
	{
		return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
	}// end activate
	
	@Override
	public double derivate(double x)
	{
		return 1 - activate(x) * activate(x);
	}// end derivate
}// end HTan
