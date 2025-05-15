package pa.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VExporProprietarios 
{
	private Scanner teclado;
	
	public int set(ArrayList<String[]> dados) 
	{
		int resposta = 0;
		String mensagem = "";
		
		do
		{
			Console.limpar();
			VExporCabecalho.set("PROPRIETÁRIOS");
			if(resposta == -1)
				System.out.println(" [#Ref: Selecionar]" + VMensagem.erro("\"" + mensagem + "\" é opção inválida!"));
			else
				System.out.println(" [#Ref: Selecionar]");
			System.out.println(" [N: Novo         ]");
			System.out.println(" [F: Fechar       ]");
			System.out.println();
			System.out.println();
			System.out.println("+------+------------------------+----------+");
			System.out.println("|Número|          Nome          | Telefone |");
			System.out.println("+------+------------------------+----------+");
			
			for (String[] prop: dados)
			{
				System.out.printf("|%-6s|%-24s|%-10s|\n", 
						prop[0], prop[1], prop[2]);
				System.out.println("+------+------------------------+----------+");
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
				
				boolean propExiste = false;
				for (String[] prop: dados)
				{
					if(Integer.parseInt(prop[0]) == resposta)
					{
						propExiste = true;
						break;
					}
				}
				
				if(!propExiste)
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
					case 'N', 'n':
						resposta = -6;
						break;
						
					case 'F', 'f':
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
