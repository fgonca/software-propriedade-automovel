package pa;

public class Espera 
{
	public static void espera(int tempo)
	{
		try 
		{
			Thread.sleep(tempo);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
