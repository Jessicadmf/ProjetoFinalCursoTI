package br.com.devti.gestaopedidodefarmacia.core.service;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.bo.AtendenteBO;
import br.com.devti.gestaopedidodefarmacia.core.entity.AtendenteEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class AtendenteService {

	public String salvarAtendente (AtendenteEntity atendente) throws NegocioException{
		System.out.println("Entrando no Backend, camada service...");
		AtendenteBO bo = new AtendenteBO();
		
		return bo.salvarAtendente(atendente);
	}
	
	public List<AtendenteEntity>listarAtendente() throws NegocioException {
		return new AtendenteBO().listarAtendente();
	}
	
	public void excluirAtendente(Long codigoAtendente)throws NegocioException{
		new AtendenteBO().excluirAtendente(codigoAtendente);
	}
	
	public AtendenteEntity buscarAtendentePorID(Long codigoAtendente) throws NegocioException{
		return new AtendenteBO().buscarAtendentePorID(codigoAtendente);
	}
	
	public String alterarAtendente(AtendenteEntity atendente) throws NegocioException{
		return new AtendenteBO().alterarAtendente(atendente);
	}
	
	public List<AtendenteEntity> buscarAtendenteFiltrado(AtendenteEntity atendente) throws NegocioException{
		return new AtendenteBO().buscarAtendenteFiltrado(atendente);
	}
}
