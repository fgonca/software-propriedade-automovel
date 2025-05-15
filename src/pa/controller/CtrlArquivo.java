package pa.controller;

import java.util.ArrayList;

import pa.model.Arquivo;
import pa.model.Automovel;
import pa.view.VExporMenu;
import pa.view.VExporRegisto;

// Singleton
public enum CtrlArquivo 
{
	INSTANCE;
	
	private Arquivo arq;
	
	public int baixarRegistos()
	{
		int resposta = -1;
		
	    // Obter da base de dados os dados dos automóveis
		arq = new Arquivo();		
		if(arq.baixarRegistos())
			resposta = -2;		
		return resposta;
	}	
	
	public int exporMenu()
	{
		VExporMenu vEMenu = new VExporMenu();		
		return vEMenu.set();
	}
	
	public ArrayList<Automovel> getRegistos()
	{
		return this.arq.getRegistos();
	}
	
	public int exporRegisto(int numRegisto) 
	{
		// Obtem o registo do arquivo, prepara os dados
		Automovel auto = arq.getRegistos().get(numRegisto);
		String[] dadosAuto = new String[7];
		dadosAuto[0] = auto.getMatricula();
		dadosAuto[1] = auto.getMarca();
		dadosAuto[2] = auto.getCor();
		dadosAuto[3] = auto.getTipo();
		dadosAuto[4] = Integer.toString(auto.getProprietario().getNumero());
		dadosAuto[5] = auto.getProprietario().getNome();
		dadosAuto[6] = auto.getProprietario().getTelefone();

		return new VExporRegisto().set(dadosAuto);
	}
}
