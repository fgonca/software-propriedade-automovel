package pa.view;

import java.util.Scanner;

public class VExporRegisto 
{
	private Scanner teclado = new Scanner(System.in);
	
	public int set(String[] dados) 
	{	
		int resposta;
		
		Console.limpar();
	 	VExporCabecalho.set("REGISTO");
		System.out.println(" [T: Tranferir    ]");
		System.out.println(" [F: Fechar       ]");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(" AUTOMÓVEL");
		System.out.println("    Matrícula: " + dados[0]);
		System.out.println("    Marca: " + dados[1]);
		System.out.println("    Cor: " + dados[2]);
		System.out.println("    Tipo: " + dados[3]);
		System.out.println();
		System.out.println(" PROPRIETÁRIO");
		System.out.println("    Numero: " + dados[4]);
		System.out.println("    Nome: " + dados[5]);
		System.out.println("    Telefone: " +dados[6]);
		
		Console.ajustar(4);
		
		// Retorna a primeira letra pressionada
		String letra = teclado.next().substring(0, 1);
		
		switch (letra)
		{			
			case "T", "t":
				resposta = -5;
			break;
			
			default:
			resposta = -3;
		}
		
		return resposta;		
	}
}
