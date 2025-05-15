package pa.model;

import java.util.ArrayList;

import pa.dao.ArquivoDAO;

public class Arquivo 
{
	private ArrayList<Automovel> registos;	
	
	public ArrayList<Automovel> getRegistos() 
	{
		return registos;
	}

	public void setRegistos(ArrayList<Automovel> registos) 
	{
		this.registos = registos;
	}

	public boolean baixarRegistos()
	{
		return new ArquivoDAO().baixarRegistos(this);  
	}
}
