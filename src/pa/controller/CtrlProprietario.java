package pa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import pa.model.Proprietario;
import pa.view.VExporProprietarios;
import pa.view.VRegistarProprietario;

public class CtrlProprietario
{		
	public int escolherProprietario() 
	{
		int resposta;

		ArrayList<String[]> dados = new ArrayList<String[]>();

		for(Proprietario prop: new Proprietario().getProprietarios()) 
		{			
			String[] dadosProp = new String[4];
			dadosProp[0] = Integer.toString(prop.getNumero());
			dadosProp[1] = prop.getNome();
			dadosProp[2] = prop.getTelefone();

			dados.add(dadosProp);
		}
		
		do
		{	
			resposta = new VExporProprietarios().set(dados);
		}		
		while (resposta == -1);
		
		if (resposta == -6)
		{
			resposta = registarProprietario();
		}
		return resposta;
	}
	
	public int registarProprietario()
	{
		int numProp = -3; // Aponta para o CU01: Consultar automóveis
		HashMap<String, String> novoProp = new VRegistarProprietario().get();
		
		if(!novoProp.isEmpty())
		{
			// Coloca o nome em maiúsculas
			novoProp.replace("nome", novoProp.get("nome").toUpperCase());
			
			// Cria um objeto do tipo proprietário com os dados recebidos do ator
			Proprietario prop = new Proprietario();
			prop.setNome(novoProp.get("nome"));
			prop.setTelefone(novoProp.get("telefone"));
			
			// Regista o proprietário
			int resposta = prop.registar();
			if(resposta > 0)
				numProp = resposta;
		}
		
		return numProp;
	}
	
	public Proprietario getProprietario(int numero)
	{
		return new Proprietario().getProprietario(numero);
	}
}
