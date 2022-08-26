package br.com.devti.gestaopedidodefarmacia.core.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.cj.xdevapi.Result;

import br.com.devti.gestaopedidodefarmacia.core.dao.connection.ConexaoMySQL;
import br.com.devti.gestaopedidodefarmacia.core.entity.ClienteEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class ClienteDAO {

	public String salvarCliente (ClienteEntity cliente) throws NegocioException{
		String sql = "INSERT INTO CLIENTE (NM_CLIENTE,CPF_CLIENTE,EMAIL_CLIENTE) VALUES(?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao cadastrar cliente");
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Cliente cadastrado com sucesso no banco de dados";
	}	
	
	public List<ClienteEntity> listarCliente() throws NegocioException{
		String sql = "SELECT ID_CLIENTE,NM_CLIENTE,CPF_CLIENTE,EMAIL_CLIENTE FROM CLIENTE";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ClienteEntity> clientes = new ArrayList<ClienteEntity>();
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				ClienteEntity cli = new ClienteEntity();
				cli.setId(rs.getLong("ID_CLIENTE"));
				cli.setNome(rs.getString("NM_CLIENTE"));
				cli.setCpf(rs.getString("CPF_CLIENTE"));
				cli.setEmail(rs.getString("EMAIL_CLIENTE"));
				clientes.add(cli);
			}
							
		} catch (SQLException e) {
		//	e.printStackTrace();
			throw new NegocioException("Erro ao listar os usuários");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return clientes;
	}
	
	public void excluirCliente(Long codigoCliente)throws NegocioException{
		
		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new NegocioException("Não foi possivel excluir o Cliente");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ClienteEntity buscarClientePorID(Long codigoCliente) throws NegocioException{
		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE,EMAIL_CLIENTE FROM CLIENTE WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoCliente);
			
			rs = ps.executeQuery();
			
			ClienteEntity clienteEncontrado = null;
			if(rs.next()) {
				clienteEncontrado = new ClienteEntity();
				clienteEncontrado.setId(rs.getLong("ID_CLIENTE"));
				clienteEncontrado.setNome(rs.getString("NM_CLIENTE"));
				clienteEncontrado.setCpf(rs.getString("CPF_CLIENTE"));
				clienteEncontrado.setEmail(rs.getString("EMAIL_CLIENTE"));	
			}
			return clienteEncontrado;
			
		} catch (SQLException e) {
			throw new NegocioException("Erro ao buscar o cliente");
		}finally {
			try {
				ps.close();
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String alterarCliente(ClienteEntity cliente) throws NegocioException{
		
		String sql = "UPDATE CLIENTE SET NM_CLIENTE = ?, CPF_CLIENTE = ?, EMAIL_CLIENTE = ? WHERE ID_CLIENTE = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setLong(4, cliente.getId());
			
			ps.execute();
		} catch (SQLException e) {
			throw new NegocioException("Ocorreu um erro ao atualizar os dados do Cliente");
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "O cliente foi alterado com sucesso";
	}
	
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws NegocioException{
		
		String sql = "SELECT ID_CLIENTE, NM_CLIENTE, CPF_CLIENTE, EMAIL_CLIENTE FROM CLIENTE";
		
		boolean adicionaWhere = true;
		
		List<ClienteEntity> resultado = new ArrayList<ClienteEntity>();
		
		if(cliente != null) {
			if(cliente.getId() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "ID_CLIENTE = ? ";
			}
			if(cliente.getNome() != null && ! cliente.getNome().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "NM_CLIENTE LIKE ? ";
			}
			if(cliente.getCpf() != null && ! cliente.getCpf().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "CPF_CLIENTE LIKE ? ";
			}
			if(cliente.getEmail() != null && ! cliente.getEmail().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "EMAIL_CLIENTE LIKE ? ";
			}
		}
		System.out.println(sql);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			
			int indice = 0;
			
			if(cliente != null) {
				if(cliente.getId() != null) {
					indice = indice + 1;
					ps.setLong(indice,cliente.getId());
				}
				if(cliente.getNome() != null && ! cliente.getNome().equals("")) {
					indice = indice + 1;
					ps.setString(indice, cliente.getNome() + "%");
				}
				if(cliente.getCpf() != null && ! cliente.getCpf().equals("")) {
					indice = indice + 1;
					ps.setString(indice, cliente.getCpf() + "%");
				}
				if(cliente.getEmail() != null && ! cliente.getEmail().equals("")) {
					indice = indice + 1;
					ps.setString(indice, cliente.getEmail() + "%");
				}
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ClienteEntity clienteResultado = new ClienteEntity();
				clienteResultado.setId(rs.getLong("ID_CLIENTE"));
				clienteResultado.setNome(rs.getString("NM_CLIENTE"));
				clienteResultado.setCpf(rs.getString("CPF_CLIENTE"));
				clienteResultado.setEmail(rs.getString("EMAIL_CLIENTE"));
				resultado.add(clienteResultado);
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