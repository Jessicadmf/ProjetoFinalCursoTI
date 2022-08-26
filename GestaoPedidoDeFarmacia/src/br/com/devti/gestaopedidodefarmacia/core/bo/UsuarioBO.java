package br.com.devti.gestaopedidodefarmacia.core.bo;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.dao.UsuarioDAO;
import br.com.devti.gestaopedidodefarmacia.core.entity.UsuarioEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class UsuarioBO {
	
	public String salvarUsuario(UsuarioEntity usuario) throws NegocioException{
		System.out.println("Camada BO, processando infos...");
		validarUsuario(usuario);
		UsuarioDAO cdao = new UsuarioDAO();
		return cdao.salvarUsuario(usuario);
	}
	
	public List<UsuarioEntity>listarUsuario() throws NegocioException {
		return new UsuarioDAO().listarUsuario();
	}
	
	public void excluirUsuario(Long codigoUsuario) throws NegocioException {
		new UsuarioDAO().excluirUsuario(codigoUsuario);
	}
	
	public UsuarioEntity buscarUsuarioPorId(Long codigoUsuario) throws NegocioException{
		return new UsuarioDAO().buscarUsuarioPorId(codigoUsuario);
	}
	
	public String alterarUsuario(UsuarioEntity usuario) throws NegocioException{
		validarUsuario(usuario);
		
		return new UsuarioDAO().alterarUsuario(usuario);
	}
	
	private void validarUsuario(UsuarioEntity usuario) throws NegocioException {
		UsuarioDAO dao = new UsuarioDAO();
		
		if(dao.existeUsuarioPorLogin(usuario.getLogin())) {
			throw new NegocioException("Login já existente, preencher com outros dados");
		}
		
		if(usuario.getEmail() != null && usuario.getEmail().equals("")){
			throw new NegocioException( "O Email não foi preenchido, favor verificar");
		
		}if(!usuario.getEmail().contains("@")) {
			throw new NegocioException( "O Email está invalido, favor verificar");
				
		}if(usuario.getNome() != null && usuario.getNome().equals("")) {
			throw new NegocioException("O nome do usuario precisa ser preenchido");
		}if(usuario.getLogin() != null && usuario.getLogin() .equals("")) {
			throw new NegocioException("O Login do usuario precisa ser preenchido!");
		}if(usuario.getSenha() != null && usuario.getSenha().equals("")) {
			throw new NegocioException("A senha do usuario precisa ser preenchida!");
		}
	}
	public List<UsuarioEntity> buscarUsuarioFiltrado(UsuarioEntity usuario) throws NegocioException{
		return new UsuarioDAO().buscarUsuarioFiltrado(usuario);
	}
	
	public UsuarioEntity autenticar (String login, String senha) throws NegocioException{
		if(login.equals("admin") && senha.equals("admin")) {
			UsuarioEntity usuAdmin = new UsuarioEntity();
			usuAdmin.setNome("Administrador do Sistema");
			usuAdmin.setLogin("admin");
			usuAdmin.setSenha("admin");
			return usuAdmin;
		}
		
		return new UsuarioDAO().autenticar(login, senha);
	}
}