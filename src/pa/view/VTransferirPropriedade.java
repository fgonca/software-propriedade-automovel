package pa.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VTransferirPropriedade 
{
	private Scanner teclado;
	
	public int set(ArrayList<String[]> dados) 
	{
		int resposta = 0;
		String mensagem = "";

		do
		{
			Console.limpar();
			VExporCabecalho.set("TRANSFERIR PROPRIEDADE");

			if(resposta == -1)
				System.out.println(" [#Ref: Selecionar]" + VMensagem.erro("\"" + mensagem + "\" é opção inválida!"));
			else
				System.out.println(" [#Ref: Selecionar]");
			System.out.println(" [C: Cancelar     ]");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("+---+---------+---------------+--------+-------+");
			System.out.println("|Ref|Matricula|     Marca     |   cor  |  tipo |");
			System.out.println("+---+---------+---------------+--------+-------+");

			int i = 0;
			for (String[] auto: dados)
			{
				System.out.printf("|%-3s|%-9s|%-15s|%-8s|%-7s|\n", 
						i+1, auto[0],auto[1], auto[2], auto[3]);
				System.out.println("+---+---------+---------------+--------+-------+");
				i++;
			}

			int numLinhasEscritas = 13 + 2 * dados.size();
			int numLinhasBrancas = 24 - numLinhasEscritas;
			if(numLinhasBrancas > 0) 
			{
				Console.ajustar(numLinhasBrancas);
			}

			teclado = new Scanner(System.in);

			try 
			{
				resposta = teclado.nextInt();			
				if(resposta <= 0 || resposta > dados.size())
				{
					mensagem = Integer.toString(resposta);
					resposta = -1;	
				}						
			} 
			catch (InputMismatchException e) 
			{
				mensagem = teclado.next();
				switch (mensagem.charAt(0)) 
				{					
					case 'C', 'c':
						resposta = -3;
						break;
	
					default:
						resposta = -1;
				}			
			}
		}
		while (resposta == -1);
		return resposta;
	}
}
