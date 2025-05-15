package pa.view;

import java.util.Scanner;

public class VExporMenu 
{
	private Scanner teclado;
	
	public int set() 
	{	
		int resposta;
		
		Console.limpar();
		
		VExporCabecalho.set("MENU PRINCIPAL");		
		System.out.println(" [A: Automóveis   ]");
		System.out.println(" [N: Novo         ]");
		System.out.println(" [S: Sair         ]");
		for(int i = 0; i < 3; i++)
			System.out.println();
		
		for(String parte: Carro.partes())
		{
			System.out.println(parte);		
		}
		
		Console.ajustar(7);
		
		teclado = new Scanner(System.in);
		switch (teclado.next().charAt(0))
		{
			case 'A', 'a':
				resposta = -3;
				break;
			
			case 'N', 'n':
				resposta = -4;
				break;
			
			case 'S', 's':
				resposta = 0;
				break;
			
			default:
				resposta = -2;
		};
		
		return resposta;
	}
}
