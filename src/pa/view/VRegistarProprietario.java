package pa.view;

import java.util.HashMap;
import java.util.Scanner;

public class VRegistarProprietario 
{
	private HashMap<String, String> dados = new HashMap<>();
	private Scanner teclado;
	
	public HashMap<String, String> get() 
	{
		teclado = new Scanner(System.in);
		
		Console.limpar();			
		VExporCabecalho.set("REGISTAR PROPRIETÁRIO");
		for(String parte: ImgProprietario.partes())
		{
			System.out.println(parte);		
		}
		Console.ajustar(14);
		System.out.print("Nome: ");
		dados.put("nome", teclado.nextLine());
		
		Console.limpar();			
		VExporCabecalho.set("REGISTAR PROPRIETÁRIO");
		for(String parte: ImgProprietario.partes())
		{
			System.out.println(parte);		
		}
		System.out.println("Nome: " + dados.get("nome"));
		Console.ajustar(13);
		System.out.print("Telefone: ");		
		dados.put("telefone", teclado.nextLine());
		
		Console.limpar();			
		VExporCabecalho.set("REGISTAR PROPRIETÁRIO");
		for(String parte: ImgProprietario.partes())
		{
			System.out.println(parte);		
		}
		System.out.println("Nome: " + dados.get("nome"));
		System.out.println("Telefone: " + dados.get("telefone"));
		Console.ajustar(12);
		System.out.print("Confirmar (S/N): ");
		String confirmar = teclado.next();
		if(confirmar.charAt(0) != 'S' && confirmar.charAt(0) != 's')			
			dados.clear();
		
		return dados;
	}
}
