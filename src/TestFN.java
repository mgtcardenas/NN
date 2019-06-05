public class TestFN
{
	public static void main(String[] args)
	{
		Fun		myFun	= null;
		ActFun	f		= ActFun.SIGMOID;
		switch (f)
		{
			case SIGMOID:
				myFun = new Sigmoid();
				break;
			
			case RELU:
				myFun = new Relu();
				break;
			
			case HTAN:
				myFun = new HTan();
				break;
			
			default:
				System.err.println("QUE PASÓ MI VALEDOR...");
				break;
		}// end switch f
		
		System.out.println(myFun.activate(100));
		System.out.println(myFun.derive(5));
	}// end mai
}// end TestFN - class
