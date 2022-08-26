package br.com.devti.gestaopedidodefarmacia.core.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.devti.gestaopedidodefarmacia.core.dao.connection.ConexaoMySQL;
import br.com.devti.gestaopedidodefarmacia.core.entity.UsuarioEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;
public class UsuarioDAO {

	public String salvarUsuario (UsuarioEntity usuario) throws NegocioException{
		String sql = "INSERT INTO USUARIO (NOME_USU, LOGIN_USU, SENHA_USU, EMAIL_USU) VALUES (?,?,?,?)";		
 		PreparedStatement ps = null;
 		
 		try {
 			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getEmail());
			
			ps.execute();
			
			
		} catch (SQLException e) {	
//			e.printStackTrace();
			throw new NegocioException("Erro ao cadastrar usuario");
			
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	
		return "Usuario cadastrado com sucesso no banco de dados";
	}
	
	public List<UsuarioEntity> listarUsuario() throws NegocioException{
		
		String sql = "SELECT ID_USUARIO, NOME_USU, LOGIN_USU, SENHA_USU, EMAIL_USU FROM USUARIO";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				UsuarioEntity usu = new UsuarioEntity();
				usu.setCodigo(rs.getLong("ID_USUARIO"));
				usu.setNome(rs.getString("NOME_USU"));
				usu.setLogin(rs.getString("LOGIN_USU"));
				usu.setSenha(rs.getString("SENHA_USU"));
				usu.setEmail(rs.getString("EMAIL_USU"));
				usuarios.add(usu);
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new NegocioException("Erro ao listar os usuários");
		} finally {
			try {
				ps.close();			
				rs.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return usuarios;
	}
	
	public void excluirUsuario(Long codigoUsuario) throws NegocioException{
		
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoUsuario);
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new NegocioException("Não foi possível excluir o Usuário");
		} finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public UsuarioEntity buscarUsuarioPorId(Long codigoUsuario) throws NegocioException{
		
		String sql = "SELECT ID_USUARIO, NOME_USU, LOGIN_USU, SENHA_USU, EMAIL_USU FROM USUARIO WHERE ID_USUARIO = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoUsuario);
			
			rs = ps.executeQuery();
			
			UsuarioEntity usuarioEncontrado = null;
			
			if(rs.next()) {
				usuarioEncontrado = new UsuarioEntity();
				usuarioEncontrado.setCodigo(rs.getLong("ID_USUARIO"));
				usuarioEncontrado.setNome(rs.getString("NOME_USU"));
				usuarioEncontrado.setLogin(rs.getString("LOGIN_USU"));
				usuarioEncontrado.setSenha(rs.getString("SENHA_USU"));
				usuarioEncontrado.setEmail(rs.getString("EMAIL_USU"));
			}
			
			return usuarioEncontrado;
			
		} catch (SQLException e) {
			throw new NegocioException("Houve um erro ao buscar o usuário");
		} finally {
			try {
				ps.close();
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String alterarUsuario(UsuarioEntity usuario) throws NegocioException{
		
		String sql = "UPDATE USUARIO SET NOME_USU = ?, LOGIN_USU = ?, SENHA_USU = ?, EMAIL_USU = ? WHERE ID_USUARIO = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getEmail());
			ps.setLong(5, usuario.getCodigo());
			
			ps.execute();
			
		} catch (SQLException e) {
			throw new NegocioException("Ocorreu um erro ao atualizar os dados de Usuário");
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "O Usuário foi alterado com sucesso";
	}
	
	public List<UsuarioEntity> buscarUsuarioFiltrado(UsuarioEntity usuario) throws NegocioException{
		
		String sql = "SELECT ID_USUARIO, NOME_USU, LOGIN_USU, SENHA_USU, EMAIL_USU FROM USUARIO";
		
		boolean adicionaWhere = true;
		
		List<UsuarioEntity> resultado = new ArrayList<UsuarioEntity>();
		
		if(usuario != null) {
			if(usuario.getCodigo() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "ID_USUARIO = ? ";
			}
			if(usuario.getNome() != null && ! usuario.getNome().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "NOME_USU LIKE ? ";
			}
			if(usuario.getLogin() != null && ! usuario.getLogin().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "LOGIN_USU LIKE ? ";
			}
			if(usuario.getSenha() != null && ! usuario.getSenha().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "SENHA_USU LIKE ? ";
			}
			
			if(usuario.getEmail() != null && ! usuario.getEmail().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "EMAIL_USU LIKE ? ";
			}
		}
		System.out.println(sql);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			
			int indice = 0;
			
			if(usuario != null) {
				if(usuario.getCodigo() != null) {
					indice = indice + 1;
					ps.setLong(indice,usuario.getCodigo());
				}
				if(usuario.getNome() != null && ! usuario.getNome().equals("")) {
					indice = indice + 1;
					ps.setString(indice, usuario.getNome() + "%");
				}
				if(usuario.getLogin() != null && ! usuario.getLogin().equals("")) {
					indice = indice + 1;
					ps.setString(indice, usuario.getLogin() + "%");
				}
				if(usuario.getSenha() != null && ! usuario.getSenha().equals("")) {
					indice = indice + 1;
					ps.setString(indice, usuario.getSenha() + "%");
				}
				
				if(usuario.getEmail() != null && ! usuario.getEmail().equals("")) {
					indice = indice + 1;
					ps.setString(indice, usuario.getEmail() + "%");
				}
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				UsuarioEntity usuarioResultado = new UsuarioEntity();
				usuarioResultado.setCodigo(rs.getLong("ID_USUARIO"));;
				usuarioResultado.setNome(rs.getString("NOME_USU"));
				usuarioResultado.setLogin(rs.getString("LOGIN_USU"));;
				usuarioResultado.setSenha(rs.getString("SENHA_USU"));
				usuarioResultado.setEmail(rs.getString("EMAIL_USU"));
				resultado.add(usuarioResultado);
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
	
	public UsuarioEntity autenticar (String login, String senha) throws NegocioException{
		
		String sql = "SELECT ID_USUARIO, NOME_USU, LOGIN_USU, SENHA_USU, EMAIL_USU FROM USUARIO " + "WHERE LOGIN_USU = ? AND SENHA_USU = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			
			UsuarioEntity usuarioAutenticado = null;
			
			if(rs.next()) {
				usuarioAutenticado = new UsuarioEntity();
				usuarioAutenticado.setCodigo(rs.getLong("ID_USUARIO"));
				usuarioAutenticado.setNome(rs.getString("NOME_USU"));
				usuarioAutenticado.setLogin(rs.getString("LOGIN_USU"));
				usuarioAutenticado.setSenha(rs.getString("SENHA_USU"));
				usuarioAutenticado.setEmail(rs.getString("EMAIL_USU"));
				
			}
			return usuarioAutenticado;
		} catch (SQLException e) {
			throw new NegocioException("Erro ao autenticar usuario");
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
		public boolean existeUsuarioPorLogin(String login) throws NegocioException{
			
			String sql = "SELECT COUNT(*) FROM USUARIO WHERE LOGIN_USU = ?";
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = ConexaoMySQL.getConexao().prepareStatement(sql);
				ps.setString(1, login);
				
				rs = ps.executeQuery();
				
				int quantidade = 0;
				
				if(rs.next()) {
					quantidade = rs.getInt(1);
				}
				if(quantidade > 0) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NegocioException("Erro ao buscar se existe login");		
			}finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
	}

}

