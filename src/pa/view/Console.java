package pa.view;

public class Console 
{
	public static void limpar() 
	{
		for(int i=0; i<26; i++) 
		{
			System.out.println();
		}
	}
	
	public static void ajustar(int num) 
	{
		for(int i=0; i<num; i++) 
		{
			System.out.println();
		}
	}
}
