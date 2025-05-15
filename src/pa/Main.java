package pa;

import pa.controller.CtrlArquivo;
import pa.controller.CtrlAutomovel;
import pa.view.Carro;
import pa.view.Console;

public class Main 
{
	private static CtrlArquivo ctrlArq;
	private static CtrlAutomovel ctrlAuto;
	
	public static void main(String[] args)
	{		 
		ctrlArq = CtrlArquivo.INSTANCE;	
		
		ctrlAuto = new CtrlAutomovel(ctrlArq);	
		
		// Obter os registos da base de dados		
		int tarefa = ctrlArq.baixarRegistos();
		
		while (tarefa != 0 && tarefa != -1)
		{
			tarefa = ctrlArq.exporMenu();
		}
		
		mensagemFinal(tarefa);
	}
	
	private static int organizarResposta(int tarefa)
	{
		int resposta = -2;
		
		switch (tarefa) 
		{
			case -2:
				resposta = ctrlArq.exporMenu();
				break;
				
			case -3:				
				resposta = ctrlAuto.exporAutomoveis("expor");
				if(resposta >= 0)
				{
					int numRegisto = resposta;
					resposta = ctrlArq.exporRegisto(numRegisto);
					if(resposta == -5)
					{
						resposta = transferirPropriedade(numRegisto);
					}
				}
				break;
				
			case -4:				
				resposta = ctrlAuto.registarAutomovel();
				if(resposta >= 0)
				{					
					resposta = ctrlArq.exporRegisto(resposta);
				}
				break;
				
			case -5:
				resposta = transferirPropriedade(-3);
		}
		
		return resposta;
	}
	
	private static void sobe(int n)
	{
		for (int i = 0; i<= n; i++)
		{
			System.out.println("");
			Espera.espera(250);
		}
	}
	
	private static int transferirPropriedade(int ref) 
	{
		int resposta;

		// Tranferir e expor o registo, enquanto o ator desejar
		do
		{
			resposta = ctrlAuto.transferirPropriedade(ref);
			if(resposta >= 0)
			{
				int numRegisto = resposta;
				resposta = ctrlArq.exporRegisto(numRegisto);
				if(resposta == -5)
					ref = numRegisto;
			}
		}
		while(resposta == -5);
		
		return resposta;
	}
	
	private static void mensagemFinal(int tarefa)
	{
		if(tarefa == -1)
		{
			System.out.println(Cor.vermelha()+"Falha na obtenção dos registos\033[0m"+Cor.reset());
		}
		else
		{		
			Console.limpar();
			System.out.println("                Aprendi o Seguinte:");
			sobe(10);	
			
			for(String parte: Carro.partes())
			{
				System.out.println(parte);
				Espera.espera(250);				
			}
			sobe(8);
			Espera.espera(1000);			
			Console.limpar();
			System.out.println("                        FIM!");
			Console.ajustar(10);
		}
	}
}
