package pa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import pa.model.Automovel;
import pa.model.Proprietario;
import pa.view.VExporAutomoveis;
import pa.view.VRegistarAutomovel;
import pa.view.VTransferirPropriedade;

public class CtrlAutomovel
{
	private CtrlArquivo ctrlArq;
	private ArrayList<String> matricula = new ArrayList<String>();
	
	public CtrlAutomovel(CtrlArquivo ctrlArq)
	{
		this.ctrlArq = ctrlArq;
	}
	
	public int exporAutomoveis(String objetivo)
	{			
		int resposta;
		
		ArrayList<String[]> dados = new ArrayList<String[]>();
		
		for(Automovel auto: ctrlArq.getRegistos()) 
		{			
			String[] dadosAuto = new String[4];
			dadosAuto[0] = auto.getMatricula();
			dadosAuto[1] = auto.getMarca();
			dadosAuto[2] = auto.getCor();
			dadosAuto[3] = auto.getTipo();
			
			matricula.add(auto.getMatricula());
			
			dados.add(dadosAuto);
		}
	    
	    do
	    {
	    	if(objetivo == "expor")
			{
			    VExporAutomoveis vEAutos = new VExporAutomoveis();
			    resposta = vEAutos.set(dados);
			}
			else
			{
				VTransferirPropriedade vTProp = new VTransferirPropriedade();
			    resposta = vTProp.set(dados);
			}
	    }
		while (resposta == -1);
	    
	    if(resposta > 0)
	    	// Neste caso, a resposta é o número do registo
	    	resposta = resposta - 1;
		
		return resposta;
	}
	
	public int transferirPropriedade(int numRegisto)
	{
		int resposta;
		
		if(numRegisto < 0) 
		{
			// Obtem o número do automóvel
			resposta = exporAutomoveis("tranferir");
		}
		else
		{
			resposta = numRegisto;
		}	    
	    
	    if(resposta >= 0)
	    {
	    	numRegisto = resposta;
	    	
	    	CtrlProprietario ctrlProp = new CtrlProprietario();
	    	resposta = ctrlProp.escolherProprietario();
	    	
	    	int numProp = 0;
	    	switch (resposta) 
			{
				case -6:
					resposta = ctrlProp.registarProprietario();
					if(resposta > 0)
						numProp = resposta;
					break;
					
				case -3:
					break;

				default:
					numProp = resposta; 
			}
	    	
	    	if (numProp > 0)
	    	{
	    		Automovel auto = new Automovel();
	    		resposta = auto.transferir(numProp, matricula.get(numRegisto));
	    		if(resposta > 0)
	    		{
	    			Proprietario prop = ctrlProp.getProprietario(numProp);
	    			
	    			ctrlArq.getRegistos().get(numRegisto).setProprietario(prop);
	    			
	    			resposta = numRegisto;
	    		}
	    		else
	    		{
	    			resposta = -5;
	    		}
	    	}
	    }
		
		return resposta;
	}	
	
	public int registarAutomovel() 
	{
		int resposta = -2;	// Aponta para o Menu
		
		ArrayList<String> matriculas = new ArrayList<>();
		for(Automovel auto: ctrlArq.getRegistos()) 
		{			
			matriculas.add(auto.getMatricula());
		}
		
		HashMap<String, String> novoAuto;
		novoAuto = new VRegistarAutomovel().get(matriculas);
		String novaMatricula = novoAuto.get("matricula");
						
		if(!novaMatricula.equalsIgnoreCase("00000000"))
		{
			// Obter o número do proprietário			
			CtrlProprietario ctrlProp = new CtrlProprietario();
			resposta = ctrlProp.escolherProprietario();	
			
			if(resposta > 0)
			{
				int numProp = resposta;
				//Obter o proprietário pelo
				Proprietario prop = ctrlProp.getProprietario(numProp);
				
				// Juntar o número do proprietário aos dados restantes do automóvel
				novoAuto.put("proprietario",Integer.toString(numProp));
				
				// Formatar o tipo de automóvel conforme definido na base de dados
				String tipo = novoAuto.get("tipo");			
				if(tipo.charAt(0) == 'L' || tipo.charAt(0) == 'l')
					novoAuto.put("tipo", "LIGEIRO");
				else
					novoAuto.put("tipo", "PESADO");
				
				// Formatar as letras para maiúsculas
				novoAuto.replaceAll((chave, valor)->valor.toUpperCase());
				
				Automovel auto = new Automovel();
				auto.setMatricula(novoAuto.get("matricula"));
				auto.setMarca(novoAuto.get("marca"));
				auto.setCor(novoAuto.get("cor"));
				auto.setTipo(novoAuto.get("tipo"));
				auto.setProprietario(prop);				
				
				resposta = auto.registar();
				if (resposta > 0)
				{
					// Atualizar a base de dados corrente depois do registo
					ctrlArq.getRegistos().add(auto);
					resposta = ctrlArq.getRegistos().size() - 1;
				}
			}	
		}
		
		return resposta; 
	}
}
