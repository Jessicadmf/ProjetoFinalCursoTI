package br.com.devti.gestaopedidodefarmacia.core.bo;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.dao.AtendenteDAO;
import br.com.devti.gestaopedidodefarmacia.core.entity.AtendenteEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class AtendenteBO {

	public String salvarAtendente (AtendenteEntity atendente) throws NegocioException{
		System.out.println("Camada BO, processando infos...");
		validarAtendente(atendente);
		AtendenteDAO adao = new AtendenteDAO();
		return adao.salvarAtendente(atendente);
	}
	
	private void validarAtendente(AtendenteEntity atendente) throws NegocioException{
		if(atendente.getEmailAtendente() != null && atendente.getEmailAtendente().equals("")){
			throw new NegocioException( "O Email não foi preenchido, favor verificar");
		
		}if(!atendente.getEmailAtendente().contains("@")) {
			throw new NegocioException( "O Email está invalido, favor verificar");
				
		}if(atendente.getNomeAtendente() != null && atendente.getNomeAtendente().equals("")) {
			throw new NegocioException("O nome do atendente precisa ser preenchido");
			
		}if(atendente.getCargo()!=null && atendente.getCargo().equals("")) {
			throw new NegocioException("O Cargo do atendente precisa ser preenchido!");
		
		}if(atendente.getCpf() !=null && atendente.getCpf().equals("")) {
			throw new NegocioException("O Cpf do atendente precisa ser preenchido!");
		}
		
	}
	public List<AtendenteEntity>listarAtendente() throws NegocioException {
		return new AtendenteDAO().listarAtendente();
	}
	
	public void excluirAtendente(Long codigoAtendente)throws NegocioException{
		new AtendenteDAO().excluirAtendente(codigoAtendente);
	}
	
	public AtendenteEntity buscarAtendentePorID(Long codigoAtendente) throws NegocioException{
		return new AtendenteDAO().buscarAtendentePorID(codigoAtendente);
	}
	
	public String alterarAtendente(AtendenteEntity atendente) throws NegocioException{
		validarAtendente(atendente);
		return new AtendenteDAO().alterarAtendente(atendente);
	}
	public List<AtendenteEntity> buscarAtendenteFiltrado(AtendenteEntity atendente) throws NegocioException{
		return new AtendenteDAO().buscarAtendenteFiltrado(atendente);
	}
}
