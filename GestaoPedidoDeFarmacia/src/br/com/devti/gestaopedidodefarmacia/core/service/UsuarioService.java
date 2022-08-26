package br.com.devti.gestaopedidodefarmacia.core.service;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.bo.UsuarioBO;
import br.com.devti.gestaopedidodefarmacia.core.entity.UsuarioEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class UsuarioService {

	public String salvarUsuario(UsuarioEntity usuario) throws NegocioException{
		System.out.println("Entrando no Backend, camada service...");
		UsuarioBO bo = new UsuarioBO();
		return bo.salvarUsuario(usuario);
	}
	
	public List<UsuarioEntity>listarUsuario() throws NegocioException {
		return new UsuarioBO().listarUsuario();
	}
	
	public void excluirUsuario(Long codigoUsuario) throws NegocioException {
		new UsuarioBO().excluirUsuario(codigoUsuario);
	}
	public UsuarioEntity buscarUsuarioPorId(Long codigoUsuario) throws NegocioException{
		return new UsuarioBO().buscarUsuarioPorId(codigoUsuario);
	}
	
	public String alterarUsuario(UsuarioEntity usuario) throws NegocioException{
		return new UsuarioBO().alterarUsuario(usuario);
	}
	public List<UsuarioEntity> buscarUsuarioFiltrado(UsuarioEntity usuario) throws NegocioException{
		return new UsuarioBO().buscarUsuarioFiltrado(usuario);
	}
	
	public UsuarioEntity autenticar (String login, String senha) throws NegocioException{
		return new UsuarioBO().autenticar(login, senha);
	}
}
