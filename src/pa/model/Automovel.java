package pa.model;

import pa.dao.AutomovelDAO;

public class Automovel 
{
	private String matricula;
	private String marca;
	private String tipo;
	private String cor;
	private Proprietario proprietario;
	
	public String getMatricula() 
	{
		return matricula;
	}

	public void setMatricula(String matricula) 
	{
		this.matricula = matricula;
	}

	public String getMarca() 
	{
		return marca;
	}

	public void setMarca(String marca) 
	{
		this.marca = marca;
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	public String getCor() 
	{
		return cor;
	}

	public void setCor(String cor) 
	{
		this.cor = cor;
	}

	public Proprietario getProprietario() 
	{
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) 
	{
		this.proprietario = proprietario;
	}

	public boolean matriculaExiste(String matricula) 
	{
		return new AutomovelDAO().matriculaExiste(matricula); 		
	}

	public int registar() 
	{
		return new AutomovelDAO().registar(this); 			
	}

	public int transferir(int numProprietario, String matricula) 
	{
		return new AutomovelDAO().transferir(numProprietario, matricula);	
	}	
}