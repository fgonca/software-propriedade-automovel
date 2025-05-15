package pa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pa.model.Proprietario;

public class ProprietarioDAO 
{
	public int registar(Proprietario prop) 
	{
		int numProp = 0;
		
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();
			
			// Preparar a declaração			
			final String query = "INSERT INTO proprietario (nome, telefone) VALUES(?,?)";
			pStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, prop.getNome());
			pStatement.setString(2, prop.getTelefone());

			// Executar a consulta
			int linhasAfetadas = pStatement.executeUpdate();

			// Recolher e processar o resultado
			if(linhasAfetadas > 0) 
			{
				ResultSet chavesGeradas = pStatement.getGeneratedKeys();
				if(chavesGeradas.next()) 
				{
					Mensagem.ok("Novo proprietário registado!", 15, 1500);
					numProp = chavesGeradas.getInt(1);  // Último Id Inserido
					prop.setNumero(numProp);
				}
			}
		}
		catch (Exception e) 
		{
			Mensagem.erro("Erro ao registar proprietário: " + e, 15, 1500);
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
		
		return numProp;
	}

	public Proprietario getProprietario(Proprietario prop, int numero) 
	{
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();

			// Preparar a declaração	
			final String query = "SELECT nome, telefone FROM proprietario WHERE numero = ?";
			pStatement = con.prepareStatement(query);
			pStatement.setInt(1, numero);
			
			// Executar a consulta
			ResultSet rs = pStatement.executeQuery();

			// Recolher e processar o resultado
			if(rs.next())
			{				
				prop.setNumero(numero);
				prop.setNome(rs.getString("nome"));
				prop.setTelefone(rs.getString("telefone"));
			}	
		} 
		catch (Exception e) 
		{
			Mensagem.erro("Erro ao obter proprietário: " + e, 15, 1500);
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
		
		return prop;
	}

	public ArrayList<Proprietario> getProprietarios() 
	{
		ArrayList<Proprietario> props = new ArrayList<Proprietario>();
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();

			// Preparar a declaração	
			final String query = "SELECT * FROM proprietario";
			pStatement = con.prepareStatement(query);
			
			// Executar a consulta
			ResultSet rs = pStatement.executeQuery();

			// Recolher e processar o resultado
			while(rs.next())
			{	
				Proprietario prop = new Proprietario();			
				prop.setNumero(rs.getInt("numero"));
				prop.setNome(rs.getString("nome"));
				prop.setTelefone(rs.getString("telefone"));
				props.add(prop);
			}	
		} 
		catch (Exception e) 
		{
			Mensagem.erro("Erro ao obter proprietários: " + e, 15, 1500);
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
		
		return props;
	}
}
