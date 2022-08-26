package br.com.devti.gestaopedidodefarmacia.core.service;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.bo.ClienteBO;
import br.com.devti.gestaopedidodefarmacia.core.entity.ClienteEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class ClienteService {

	public String salvarCliente(ClienteEntity cliente) throws NegocioException {
		System.out.println("Entrando no Backend, camada service...");
		ClienteBO bo = new ClienteBO();
		return bo.salvarCliente(cliente);
	}
	
	public List<ClienteEntity> listarCliente() throws NegocioException{
		return new ClienteBO().listarCliente();
	}
	
	public void excluirCliente(Long codigoCliente)throws NegocioException {
		new ClienteBO().excluirCliente(codigoCliente);
	}
	public ClienteEntity buscarClientePorID (Long codigoCliente)throws NegocioException{
		return new ClienteBO().buscarClientePorID(codigoCliente);
	}
	
	public String alterarCliente(ClienteEntity cliente) throws NegocioException{
		return new ClienteBO().alterarCliente(cliente);
	}
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws NegocioException{
		return new ClienteBO().buscarClienteFiltrado(cliente);
	}
}