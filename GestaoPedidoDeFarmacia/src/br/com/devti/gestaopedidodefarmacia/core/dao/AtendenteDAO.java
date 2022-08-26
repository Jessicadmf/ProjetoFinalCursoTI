package br.com.devti.gestaopedidodefarmacia.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.dao.connection.ConexaoMySQL;
import br.com.devti.gestaopedidodefarmacia.core.entity.AtendenteEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class AtendenteDAO {

	public String salvarAtendente (AtendenteEntity atendente) throws NegocioException{
		String sql = "INSERT INTO ATENDENTE (NM_ATENDENTE,EMAIL_ATENDENTE,CARGO_ATENDENTE,CPF_ATENDENTE) VALUES(?,?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, atendente.getNomeAtendente());
			ps.setString(2, atendente.getEmailAtendente());
			ps.setString(3, atendente.getCargo());
			ps.setString(4, atendente.getCpf());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao cadastrar atendente");
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Atendente cadastrado com sucesso no banco de dados";
	}	
	
	public List<AtendenteEntity> listarAtendente() throws NegocioException{
		String sql = "SELECT ID_ATENDENTE,NM_ATENDENTE,EMAIL_ATENDENTE,CARGO_ATENDENTE,CPF_ATENDENTE FROM ATENDENTE";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AtendenteEntity> atendente = new ArrayList<AtendenteEntity>();
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				AtendenteEntity ate = new AtendenteEntity();
				ate.setIdAtendente(rs.getLong("ID_ATENDENTE"));
				ate.setNomeAtendente(rs.getString("NM_ATENDENTE"));
				ate.setEmailAtendente(rs.getString("EMAIL_ATENDENTE"));
				ate.setCargo(rs.getString("CARGO_ATENDENTE"));
				ate.setCpf(rs.getString("CPF_ATENDENTE"));
				atendente.add(ate);
			}
							
		} catch (SQLException e) {
			throw new NegocioException("Erro ao listar os atendentes");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		return atendente;
	}
	
	public void excluirAtendente(Long codigoAtendente)throws NegocioException{
		
		String sql = "DELETE FROM ATENDENTE WHERE ID_ATENDENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoAtendente);
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new NegocioException("Não foi possivel excluir o Atendente");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public AtendenteEntity buscarAtendentePorID(Long codigoAtendente) throws NegocioException{
		String sql = "SELECT ID_ATENDENTE, NM_ATENDENTE, EMAIL_ATENDENTE, CARGO_ATENDENTE,CPF_ATENDENTE FROM ATENDENTE WHERE ID_ATENDENTE = ?";
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoAtendente);
			
			rs = ps.executeQuery();
			
			AtendenteEntity atendenteEncontrado = null;
			if(rs.next()) {
				atendenteEncontrado = new AtendenteEntity();
				atendenteEncontrado.setIdAtendente(rs.getLong("ID_ATENDENTE"));
				atendenteEncontrado.setNomeAtendente(rs.getString("NM_ATENDENTE"));
				atendenteEncontrado.setEmailAtendente(rs.getString("EMAIL_ATENDENTE"));
				atendenteEncontrado.setCargo(rs.getString("CARGO_ATENDENTE"));	
				atendenteEncontrado.setCpf(rs.getString("CPF_ATENDENTE"));
			}
			return atendenteEncontrado;
			
		} catch (SQLException e) {
			throw new NegocioException("Erro ao buscar o atendente");
		}finally {
			try {
				ps.close();
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String alterarAtendente(AtendenteEntity atendente) throws NegocioException{
		
		String sql = "UPDATE ATENDENTE SET NM_ATENDENTE = ?, EMAIL_ATENDENTE = ?, CARGO_ATENDENTE = ?, CPF_ATENDENTE = ? WHERE ID_ATENDENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, atendente.getNomeAtendente());
			ps.setString(2, atendente.getEmailAtendente());
			ps.setString(3, atendente.getCargo());
			ps.setString(4, atendente.getCpf());
			ps.setLong(5, atendente.getIdAtendente());
			
			ps.execute();
		} catch (SQLException e) {
			throw new NegocioException("Ocorreu um erro ao atualizar os dados do Atendente");
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "O atendente foi alterado com sucesso";
	}
	
	public List<AtendenteEntity> buscarAtendenteFiltrado(AtendenteEntity atendente) throws NegocioException{
		
		String sql = "SELECT ID_ATENDENTE, NM_ATENDENTE, EMAIL_ATENDENTE, CARGO_ATENDENTE, CPF_ATENDENTE FROM ATENDENTE";
		
		boolean adicionaWhere = true;
		
		List<AtendenteEntity> resultado = new ArrayList<AtendenteEntity>();
		
		if(atendente != null) {
			if(atendente.getIdAtendente() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "ID_ATENDENTE = ? ";
			}
			if(atendente.getNomeAtendente() != null && ! atendente.getNomeAtendente().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "NM_ATENDENTE LIKE ? ";
			}
			if(atendente.getEmailAtendente() != null && ! atendente.getEmailAtendente().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "EMAIL_CLIENTE LIKE ? ";
			}
			if(atendente.getCargo() != null && ! atendente.getCargo().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "CARGO_ATENDENTE LIKE ? ";
			}
			if(atendente.getCpf() != null && ! atendente.getCpf().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "CPF_ATENDENTE LIKE ? ";
		}
	}
		System.out.println(sql);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			
			int indice = 0;
			
			if(atendente != null) {
				if(atendente.getIdAtendente() != null) {
					indice = indice + 1;
					ps.setLong(indice,atendente.getIdAtendente());
				}
				if(atendente.getNomeAtendente() != null && ! atendente.getNomeAtendente().equals("")) {
					indice = indice + 1;
					ps.setString(indice, atendente.getNomeAtendente() + "%");
				}
				if(atendente.getEmailAtendente() != null && ! atendente.getEmailAtendente().equals("")) {
					indice = indice + 1;
					ps.setString(indice, atendente.getEmailAtendente() + "%");
				}
				if(atendente.getCargo() != null && ! atendente.getCargo().equals("")) {
					indice = indice + 1;
					ps.setString(indice, atendente.getCargo() + "%");
				}
				if(atendente.getCpf() != null && ! atendente.getCpf().equals("")) {
					indice = indice + 1;
					ps.setString(indice, atendente.getCpf() + "%");
				}
				
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				AtendenteEntity atendenteResultado = new AtendenteEntity();
				atendenteResultado.setIdAtendente(rs.getLong("ID_ATENDENTE"));
				atendenteResultado.setNomeAtendente(rs.getString("NM_ATENDENTE"));
				atendenteResultado.setEmailAtendente(rs.getString("EMAIL_ATENDENTE"));
				atendenteResultado.setCargo(rs.getString("CARGO_ATENDENTE"));	
				atendenteResultado.setCpf(rs.getString("CPF_ATENDENTE"));
				resultado.add(atendenteResultado);
			}
			
		} catch (SQLException e) {
			throw new NegocioException("Busca filtrada com erro");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return resultado;
	}
	
}
