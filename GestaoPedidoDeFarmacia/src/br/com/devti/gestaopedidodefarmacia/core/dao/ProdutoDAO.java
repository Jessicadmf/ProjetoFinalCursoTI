package br.com.devti.gestaopedidodefarmacia.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.devti.gestaopedidodefarmacia.core.dao.connection.ConexaoMySQL;
import br.com.devti.gestaopedidodefarmacia.core.entity.ProdutoEntity;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;

public class ProdutoDAO {

	public String salvarProduto(ProdutoEntity produto)throws NegocioException{
		String sql = "INSERT INTO PRODUTO (NM_PRODUTO,EAN_PRODUTO,FORNE_PRODUTO) VALUES(?,?,?)";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, produto.getNomeMedicamento());
			ps.setLong(2, produto.getEan());
			ps.setString(3, produto.getFornecedor());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NegocioException("Erro ao cadastrar produto");
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Produto cadastrado com sucesso no banco de dados";
	}	

	public List<ProdutoEntity> listarProduto() throws NegocioException{
		String sql = "SELECT ID_PRODUTO, NM_PRODUTO, EAN_PRODUTO, FORNE_PRODUTO FROM PRODUTO";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ProdutoEntity> produtos = new ArrayList<ProdutoEntity>();
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProdutoEntity pro = new ProdutoEntity();
				pro.setCodigoProduto(rs.getLong("ID_PRODUTO"));
				pro.setNomeMedicamento(rs.getString("NM_PRODUTO"));
				pro.setEan(rs.getLong("EAN_PRODUTO"));
				pro.setFornecedor(rs.getString("FORNE_PRODUTO"));
				produtos.add(pro);
			}
			
		} catch (SQLException e) {
			throw new NegocioException("Erro ao listar os produtos.");
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return produtos;
	}
	
	public void excluirProduto (Long codigoProduto)throws NegocioException{
		String sql = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ?";
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoProduto);
			
			ps.execute();
			
		} catch (SQLException e) {
		throw new NegocioException("Não foi possível excluir o Produto");
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ProdutoEntity buscarProdutoPorID(Long codigoProduto) throws NegocioException{
		String sql = "SELECT ID_PRODUTO, NM_PRODUTO, EAN_PRODUTO,FORNE_PRODUTO FROM PRODUTO WHERE ID_PRODUTO = ?";
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setLong(1, codigoProduto);
			
			rs = ps.executeQuery();
			
			ProdutoEntity produtoEncontrado = null;
			if(rs.next()) {
				produtoEncontrado = new ProdutoEntity();
				produtoEncontrado.setCodigoProduto(rs.getLong("ID_PRODUTO"));
				produtoEncontrado.setNomeMedicamento(rs.getString("NM_PRODUTO"));
				produtoEncontrado.setEan(rs.getLong("EAN_PRODUTO"));
				produtoEncontrado.setFornecedor(rs.getString("FORNE_PRODUTO"));
			}
			return produtoEncontrado;
			
		} catch (SQLException e) {
			throw new NegocioException("Erro ao buscar o produto");
		}finally {
			try {
				ps.close();
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String alterarProduto(ProdutoEntity produto) throws NegocioException{
		
		String sql = "UPDATE PRODUTO SET NM_PRODUTO = ?, EAN_PRODUTO = ?,FORNE_PRODUTO= ? WHERE ID_PRODUTO = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			ps.setString(1, produto.getNomeMedicamento());
			ps.setLong(2, produto.getEan());
			ps.setString(3, produto.getFornecedor());
			ps.setLong(4, produto.getCodigoProduto());
			
			ps.execute();
		} catch (SQLException e) {
			throw new NegocioException("Ocorreu um erro ao atualizar os dados do Produto");
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "O produto foi alterado com sucesso";
	}
	
	public List<ProdutoEntity> buscarProdutoFiltrado(ProdutoEntity produto) throws NegocioException{
		
		String sql = "SELECT ID_PRODUTO, NM_PRODUTO, EAN_PRODUTO, FORNE_PRODUTO FROM PRODUTO";
		
		boolean adicionaWhere = true;
		
		List<ProdutoEntity> resultado = new ArrayList<ProdutoEntity>();
		
		if(produto != null) {
			if(produto.getCodigoProduto() != null) {
				adicionaWhere = false;
				sql += " WHERE ";
				sql += "ID_PRODUTO = ? ";
			}
			if(produto.getNomeMedicamento() != null && ! produto.getNomeMedicamento().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "NM_PRODUTO LIKE ? ";
			}
			if(produto.getEan() != null && ! produto.getEan().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "EAN_PRODUTO LIKE ? ";
			}
			if(produto.getFornecedor() != null && ! produto.getFornecedor().equals("")) {
				if(adicionaWhere) {
					sql+= " WHERE ";
					adicionaWhere = false;
				}else {
					sql+= " AND ";
				}
				sql += "FORNE_PRODUTO LIKE ? ";
			}
		}
		System.out.println(sql);
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = ConexaoMySQL.getConexao().prepareStatement(sql);
			
			int indice = 0;
			
			if(produto != null) {
				if(produto.getCodigoProduto() != null) {
					indice = indice + 1;
					ps.setLong(indice,produto.getCodigoProduto());
				}
				if(produto.getNomeMedicamento() != null && ! produto.getNomeMedicamento().equals("")) {
					indice = indice + 1;
					ps.setString(indice, produto.getNomeMedicamento() + "%");
				}
				if(produto.getEan() != null && ! produto.getEan().equals("")) {
					indice = indice + 1;
					ps.setString(indice, produto.getEan() + "%");
				}
				if(produto.getFornecedor() != null && ! produto.getFornecedor().equals("")) {
					indice = indice + 1;
					ps.setString(indice, produto.getFornecedor() + "%");
				}
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProdutoEntity produtoResultado = new ProdutoEntity();
				produtoResultado.setCodigoProduto(rs.getLong("ID_PRODUTO"));
				produtoResultado.setNomeMedicamento(rs.getString("NM_PRODUTO"));
				produtoResultado.setEan(rs.getLong("EAN_PRODUTO"));
				produtoResultado.setFornecedor(rs.getString("FORNE_PRODUTO"));
				resultado.add(produtoResultado);
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
