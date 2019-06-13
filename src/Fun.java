public interface Fun
{
	float activate(float x);
	
	float derive(float x);
}// end Fun - interface

enum ActFun
{
	SIGMOID, RELU, HTAN
}// end ACtivationFunc - enum

class Sigmoid implements Fun
{
	@Override
	public float activate(float x)
	{
		return (float) (1 / (1 + Math.exp(-x)));
	}// end activate
	
	@Override
	public float derive(float x)
	{
		return x * (1 - x);
	}// end derive
}// end Sigmoid - class

class Relu implements Fun
{
	@Override
	public float activate(float x)
	{
		return Math.max(0, x);
	}// end activate
	
	@Override
	public float derive(float x)
	{
		return x > 0 ? 1 : 0;
	}// end derive
}// end Relu - class

class HTan implements Fun
{
	@Override
	public float activate(float x)
	{
		return (float) (2 / (1 + Math.pow(Math.E, -(2 * x))) - 1);
	}// end activate
	
	@Override
	public float derive(float x)
	{
		return 1 - activate(x) * activate(x);
	}// end derive
}// end HTan - class

class Lineal implements Fun
{
	@Override
	public float activate(float x)
	{
		return 0.1F * x;
	}// end activate
	
	@Override
	public float derive(float x)
	{
		return 0.1F;
	}// end derive
}// end Lineal - class
