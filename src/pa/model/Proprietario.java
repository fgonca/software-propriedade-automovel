package pa.model;

import java.util.ArrayList;
import pa.dao.ProprietarioDAO;

public class Proprietario 
{
	private int numero;
	private String nome;
	private String telefone;
	
	public int getNumero() 
	{
		return numero;
	}
	
	public void setNumero(int numero) 
	{
		this.numero = numero;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getTelefone() 
	{
		return telefone;
	}
	
	public void setTelefone(String telefone) 
	{
		this.telefone = telefone;
	}

	public int registar() 
	{
		return new ProprietarioDAO().registar(this);		
	}	
	
	public Proprietario getProprietario(int numero)
	{
		return new ProprietarioDAO().getProprietario(this, numero);
	}

	public ArrayList<Proprietario> getProprietarios() 
	{
		return new ProprietarioDAO().getProprietarios();
	}
}
