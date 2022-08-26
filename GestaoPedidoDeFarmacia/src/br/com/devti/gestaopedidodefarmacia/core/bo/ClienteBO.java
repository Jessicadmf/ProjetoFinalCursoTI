package br.com.devti.gestaopedidodefarmacia.core.bo;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.dao.ClienteDAO;
import br.com.devti.gestaopedidodefarmacia.core.entity.ClienteEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class ClienteBO {

	public String salvarCliente(ClienteEntity cliente) throws NegocioException{
		System.out.println("Camada BO, processando infos...");
		validarCliente(cliente);
		ClienteDAO cdao = new ClienteDAO();
		return cdao.salvarCliente(cliente);
	}
	
	public List<ClienteEntity>listarCliente() throws NegocioException {
		return new ClienteDAO().listarCliente();
	}
	
	public void excluirCliente(Long codigoCliente) throws NegocioException {
		new ClienteDAO().excluirCliente(codigoCliente);
	}
	
	public ClienteEntity buscarClientePorID (Long codigoCliente)throws NegocioException{
		return new ClienteDAO().buscarClientePorID(codigoCliente);
	}
	
	public String alterarCliente(ClienteEntity cliente) throws NegocioException{
		validarCliente(cliente);
		
		return new ClienteDAO().alterarCliente(cliente);
	}
	
	private void validarCliente(ClienteEntity cliente) throws NegocioException {
		if(cliente.getEmail() != null && cliente.getEmail().equals("")){
			throw new NegocioException( "O Email não foi preenchido, favor verificar");
		
		}if(!cliente.getEmail().contains("@")) {
			throw new NegocioException( "O Email está invalido, favor verificar");
				
		}if(cliente.getNome() != null && cliente.getNome().equals("")) {
			throw new NegocioException("O nome do cliente precisa ser preenchido");
		}if(cliente.getCpf() !=null && cliente.getCpf().equals("")) {
			throw new NegocioException("O CPF do cliente precisa ser preenchido!");
		
		}
	}
	public List<ClienteEntity> buscarClienteFiltrado(ClienteEntity cliente) throws NegocioException{
		return new ClienteDAO().buscarClienteFiltrado(cliente);
	}
}
