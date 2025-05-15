package pa.dao;

import pa.Cor;
import pa.Espera;
import pa.view.Console;

public class Mensagem 
{	
	public static void erro(String mensagem, int ajuste, int tempo)
	{
		Console.limpar();
		System.out.println("            " + Cor.vermelha() + mensagem + Cor.reset());
		Console.ajustar(ajuste);
		Espera.espera(tempo);
	}
	
	public static void ok(String mensagem, int ajuste, int tempo)
	{
		Console.limpar();
		System.out.println("            " + Cor.azul() + mensagem + Cor.reset());
		Console.ajustar(ajuste);
		Espera.espera(tempo);
	}
}
