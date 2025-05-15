package pa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pa.model.Automovel;

public class AutomovelDAO 
{	
	public boolean matriculaExiste(String matricula) 
	{
		boolean resultado = false;
		
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();

			// Preparar a declaração	
			final String query = "SELECT matricula FROM automovel WHERE matricula = ?";
			pStatement = con.prepareStatement(query);			
			pStatement.setString(1, matricula);
			
			// Executar a consulta
			ResultSet rs = pStatement.executeQuery();				

			// Obter o resultado
			if(rs.next()) 
			{
				resultado = true;
			}
		} 
		catch (Exception e) 
		{
			// Exibir mensagem por 1,5s se houver excepção
			Mensagem.erro("Erro ao verificar a matrícula do automóvel: " + e, 15, 1500);
		}
		finally
		{
			// Fechar a declaração e a conexão
			// Exibir mensagem por 1,5s se houver excepção
			
			try 
			{
				if(pStatement != null){ pStatement.close();	}
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a declaração: " + e, 15, 1500);
			}
			
			try 
			{
				if(con != null ){ con.close(); }
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a conexão: " + e, 15, 1500);
			}
		}
		
		return resultado;
	}

	public int registar(Automovel automovel) 
	{
		int resposta = 0;
		
		Connection con = null;		
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();			

			// Preparar a declaração			
			final String query = "INSERT INTO automovel (matricula, marca, cor, tipo, proprietario) VALUES(?,?,?,?,?)";
			pStatement = con.prepareStatement(query);
			pStatement.setString(1, automovel.getMatricula());
			pStatement.setString(2, automovel.getMarca());
			pStatement.setString(3, automovel.getCor());
			pStatement.setString(4, automovel.getTipo());
			pStatement.setInt(5, automovel.getProprietario().getNumero());

			// Executar a consulta
			int r = pStatement.executeUpdate();
			
			if(r > 0) 
			{	
				resposta = r;
				// Exibir o resultado
				Mensagem.ok("Novo automóvel registado!", 15, 1500);
			}
		}
		catch (Exception e) 
		{
			// Exibir mensagem por 1,5s se houver excepção
			Mensagem.erro("Erro ao registar automóvel: " + e, 15, 1500);
		}
		finally
		{
			// Fechar a declaração e a conexão
			// Exibir mensagem por 1,5s se houver excepção
			
			try 
			{
				if(pStatement != null){ pStatement.close();	}
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a declaração: " + e, 15, 1500);
			}

			try 
			{
				if(con != null ){ con.close(); }
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a conexão: " + e, 15, 1500);
			}
		}
		
		return resposta;
	}

	public int transferir(int numProprietario, String matricula) 
	{
		int resposta = -3;
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();

			// Preparar a declaração	
			final String query = "UPDATE automovel SET proprietario = ? WHERE matricula = ?";
			pStatement = con.prepareStatement(query);
			pStatement.setInt(1, numProprietario);
			pStatement.setString(2, matricula);	
			
			// Executar a consulta
			int i = pStatement.executeUpdate();

			// Exibir o resultado
			if(i > 0) 
			{
				resposta = i;				
				Mensagem.ok("Automóvel transferido!", 15, 1500);
			}
		}
		catch (Exception e) 
		{
			// Exibir mensagem por 1,5s se houver excepção
			Mensagem.erro("Erro ao transferir automóvel: " + e, 15, 1500);
		}
		finally
		{
			// Fechar a declaração e a conexão 
			// Exibir mensagem por 1,5s se houver excepção
			
			try 
			{
				if(pStatement != null){ pStatement.close();	}
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a declaração: " + e, 15, 1500);
			}

			try 
			{
				if(con != null ){ con.close(); }
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a conexão: " + e, 15, 1500);
			}
		}
		
		return resposta;
	}
}
