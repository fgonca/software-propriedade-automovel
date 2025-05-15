package pa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pa.model.Arquivo;
import pa.model.Automovel;
import pa.model.Proprietario;

public class ArquivoDAO 
{
	public boolean baixarRegistos(Arquivo arq)
	{		
		boolean resultado = true;
		
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try 
		{
			// Criar a conexão
			con = new Conexao().getConnection();
			
			// Preparar a declaração
			final String query = "SELECT * FROM v_propriedade";
			pStatement = con.prepareStatement(query);
			
			// Executar a consulta
			ResultSet rs = pStatement.executeQuery();

			// Recolher e organizar o resultado
			ArrayList<Automovel> registos = new ArrayList<Automovel>();
			while(rs.next())
			{
				Automovel auto = new Automovel();
				auto.setMatricula (rs.getString("matricula"));
				auto.setMarca (rs.getString("marca"));
				auto.setCor (rs.getString("cor"));
				auto.setTipo (rs.getString("tipo"));

				Proprietario prop = new Proprietario();
				prop.setNumero(rs.getInt("numero"));
				prop.setNome(rs.getString("nome"));
				prop.setTelefone(rs.getString("telefone"));

				auto.setProprietario(prop);
				registos.add(auto);
			}
			arq.setRegistos(registos);			
		} 
		catch (Exception e) 
		{
			// Exibir mensagem por 1,5s se houver excepção
			Mensagem.erro("Erro ao consultar automóvel: " + e, 15, 1500);
			resultado = false;
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
				resultado = false;
			}
			
			try 
			{
				if(con != null ){ con.close(); }
			} 
			catch (Exception e) 
			{
				Mensagem.erro("Erro ao fechar a conexão: " + e, 15, 1500);
				resultado = false;
			}
		}
		
		return resultado;
	}
}
