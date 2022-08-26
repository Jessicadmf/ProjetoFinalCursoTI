package br.com.devti.gestaopedidodefarmacia.core.bo;



import java.util.List;



import br.com.devti.gestaopedidodefarmacia.core.dao.ProdutoDAO;
import br.com.devti.gestaopedidodefarmacia.core.entity.ProdutoEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class ProdutoBO {
	
	public String salvarProduto(ProdutoEntity produto)throws NegocioException{
		System.out.println("Camada BO, processando infos...");
		validarProduto(produto);
		ProdutoDAO pro = new ProdutoDAO();
		return pro.salvarProduto(produto);
	}
	
	public List<ProdutoEntity> listarProduto() throws NegocioException{
		return new ProdutoDAO().listarProduto();
	}
	
	public void excluirProduto (Long codigoProduto)throws NegocioException{
		new ProdutoDAO().excluirProduto(codigoProduto);
	}
	
	public ProdutoEntity buscarProdutoPorID(Long codigoProduto) throws NegocioException{
		return new ProdutoDAO().buscarProdutoPorID(codigoProduto);
	}
	
	public String alterarProduto(ProdutoEntity produto) throws NegocioException{
		validarProduto(produto);
		return new ProdutoDAO().alterarProduto(produto);	
	}
	
	public List<ProdutoEntity> buscarProdutoFiltrado(ProdutoEntity produto) throws NegocioException{
		return new ProdutoDAO().buscarProdutoFiltrado(produto);
	}
	
	private void validarProduto(ProdutoEntity produto)throws NegocioException{
			
		String obj = "";
		if(produto.getNomeMedicamento() != null && produto.getNomeMedicamento().equals(obj)) {
			throw new NegocioException ("O nome do produto precisa ser preenchido");
		}
		
		if(produto.getEan() != null && produto.getEan().equals(obj)) {
			throw new NegocioException("O EAN do produto precisa ser preenchido");
		}
		
		if(produto.getFornecedor() !=null && produto.getFornecedor().equals(obj)) {
			throw new NegocioException (" O Fornecedor do produto precisa ser preenchido");
		}
		
		
	}
}
