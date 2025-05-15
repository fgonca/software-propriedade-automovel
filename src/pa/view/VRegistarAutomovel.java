package pa.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VRegistarAutomovel 
{
	private Scanner teclado;
	
	public HashMap<String, String> get(ArrayList<String> matriculas) 
	{		
		teclado = new Scanner(System.in);

		String mensagem = "";
		HashMap<String, String> dados = new HashMap<>();

		String matricula;
		boolean matriculaExiste = false;
		do
		{
			Console.limpar();			
			VExporCabecalho.set("REGISTAR AUTOMÓVEL");
			if(!mensagem.isEmpty())
			{
				System.out.println("Matrícula: " + VMensagem.erro(mensagem));
			}
			Console.ajustar(6);
			for(String parte: Carro.partes())
			{
				System.out.println(parte);		
			}
			Console.ajustar(7);
			System.out.print("Matrícula (8 caracteres): ");
			matricula = teclado.nextLine();
			if(matricula.length() != 8)
				mensagem = "Deve ter oito (8) caracteres!";
			else if(matriculas.contains(matricula.toUpperCase()))
			{
				mensagem = "\"" + matricula + "\" já existe!";
				matriculaExiste = true;
			}
			else
				matriculaExiste = false;
		}
		while(matricula.length() != 8 || matriculaExiste);
		dados.put("matricula", matricula);

		Console.limpar();			
		VExporCabecalho.set("REGISTAR AUTOMÓVEL");
		System.out.println("Matrícula: " + dados.get("matricula"));
		Console.ajustar(5);
		for(String parte: Carro.partes())
		{
			System.out.println(parte);		
		}
		Console.ajustar(7);
		System.out.print("Marca: ");
		dados.put("marca", teclado.nextLine());

		Console.limpar();			
		VExporCabecalho.set("REGISTAR AUTOMÓVEL");
		System.out.println("Matrícula: " + dados.get("matricula"));
		System.out.println("Marca: " + dados.get("marca"));
		Console.ajustar(4);
		for(String parte: Carro.partes())
		{
			System.out.println(parte);		
		}
		Console.ajustar(7);
		System.out.print("Cor: ");		
		dados.put("cor", teclado.nextLine());

		// Não aceita valores diferentes de L/l e P/p
		mensagem = "";
		String tipo;
		do {
			Console.limpar();			
			VExporCabecalho.set("REGISTAR AUTOMÓVEL");
			System.out.println("Matrícula: " + dados.get("matricula"));
			System.out.println("Marca: " + dados.get("marca"));
			System.out.println("Cor: " + dados.get("cor"));
			if(!mensagem.isEmpty())
			{
				System.out.println("Tipo: " + VMensagem.erro(mensagem));
				Console.ajustar(2);
			}
			else
				Console.ajustar(3);

			for(String parte: Carro.partes())
			{
				System.out.println(parte);		
			}
			Console.ajustar(7);
			System.out.print("Tipo (L: Ligeiro/P: Pesado): ");
			String resposta = teclado.nextLine();
			tipo = resposta.substring(0, 1);
			switch (tipo)
			{
			case "L", "l":
				tipo = "LIGEIRO";
			break;

			case "P", "p":
				tipo = "PESADO";
			break;

			default:
				mensagem = "\"" + resposta + "\" é opção inválida!";
			}
		}
		while (tipo != "LIGEIRO" && tipo != "PESADO");		

		dados.put("tipo", tipo);

		Console.limpar();			
		VExporCabecalho.set("REGISTAR AUTOMÓVEL");
		System.out.println("Matrícula: " + dados.get("matricula"));
		System.out.println("Marca: " + dados.get("marca"));
		System.out.println("Cor: " + dados.get("cor"));
		System.out.println("Tipo: " + dados.get("tipo"));
		Console.ajustar(2);
		for(String parte: Carro.partes())
		{
			System.out.println(parte);		
		}
		Console.ajustar(7);

		System.out.print("Confirmar (S/N): ");
		char confirmar = teclado.nextLine().charAt(0);

		if(confirmar != 'S' && confirmar != 's')			
			dados.put("matricula", "00000000");	

		return dados;			
	}
}
