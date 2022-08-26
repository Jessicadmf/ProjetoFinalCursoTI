package br.com.devti.gestaopedidodefarmacia.core.service;

import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.bo.ProdutoBO;
import br.com.devti.gestaopedidodefarmacia.core.entity.ProdutoEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class ProdutoService {

	public String salvarProduto(ProdutoEntity produto)throws NegocioException{
		System.out.println("Entrando no Backend, camada service...");
		ProdutoBO pbo = new ProdutoBO();
		return pbo.salvarProduto(produto);
	}
	
	public List<ProdutoEntity> listarProduto() throws NegocioException{
		return new ProdutoBO().listarProduto();
	}
	
	public void excluirProduto (Long codigoProduto)throws NegocioException{
		new ProdutoBO().excluirProduto(codigoProduto);
	}
	
	public ProdutoEntity buscarProdutoPorID(Long codigoProduto) throws NegocioException{
		return new ProdutoBO().buscarProdutoPorID(codigoProduto);
	}
	
	public String alterarProduto(ProdutoEntity produto) throws NegocioException{
		return new ProdutoBO().alterarProduto(produto);	
	}
	
	public List<ProdutoEntity> buscarProdutoFiltrado(ProdutoEntity produto) throws NegocioException{
		return new ProdutoBO().buscarProdutoFiltrado(produto);
	}
	
}
