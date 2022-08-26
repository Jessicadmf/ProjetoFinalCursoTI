package br.com.devti.gestaopedidodefarmacia.view.telas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import br.com.devti.gestaopedidodefarmacia.core.entity.ProdutoEntity;
import br.com.devti.gestaopedidodefarmacia.core.service.ProdutoService;
import br.com.devti.gestaopedidodefarmacia.core.util.exception.NegocioException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;


public class TelaCadastroProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldNomeProduto;
	private JTextField fieldEan;
	private JTextField FieldCodigo;
	private JTable table;
	private JLabel lblTitulo;
	private JTextField fieldPesquisa;
	private List<ProdutoEntity>produtos;
	private JTextField fieldFornecedor;
	private JButton btnVoltar;

	
	
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//					try {
//						TelaCadastroProduto frame = new TelaCadastroProduto();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public TelaCadastroProduto() {
		setTitle("Formulario de Produto");
		setBackground(Color.MAGENTA);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("CADASTRO DE PRODUTO");
		lblTitulo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Produto");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		fieldNomeProduto = new JTextField();
		fieldNomeProduto.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		fieldNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldNomeProduto.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ean");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldEan = new JTextField();
		fieldEan.setBorder(new LineBorder(Color.WHITE, 2, true));
		fieldEan.setColumns(10);
		
		FieldCodigo = new JTextField();
		FieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		FieldCodigo.setDisabledTextColor(Color.BLACK);
		FieldCodigo.setSelectedTextColor(Color.BLACK);
		FieldCodigo.setSelectionColor(Color.BLACK);
		FieldCodigo.setEditable(false);
		FieldCodigo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		FieldCodigo.setBackground(Color.LIGHT_GRAY);
		FieldCodigo.setForeground(Color.BLACK);
		FieldCodigo.setEnabled(false);
		FieldCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		FieldCodigo.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Código");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 191, 444, 111);
		scrollPane.setBackground(new Color(192, 192, 192));
		scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setBounds(10, 9, 78, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					 ProdutoEntity ate = new ProdutoEntity();
					ate.setNomeMedicamento(fieldNomeProduto.getText());
					ate.setEan(Long.parseLong(fieldEan.getText()));
					ate.setFornecedor(fieldFornecedor.getText());
					
					
					String msg = null;
					try {
						
						if(FieldCodigo.getText().equals("")) {
							msg = new ProdutoService().salvarProduto(ate);
						}else {
							ate.setCodigoProduto(Long.parseLong(FieldCodigo.getText()));
							msg = new ProdutoService().alterarProduto(ate);
						}
						limparCampos();
						popularTabela();
						lblTitulo.setText("CADASTRO DE PRODUTO");
						btnVoltar.setText("LIMPAR");
						contentPane.setBackground(new Color(220, 220, 220));
						JOptionPane.showMessageDialog(null, msg);
				}catch (NegocioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
					
	}
			
		});
		
		btnSalvar.setBackground(new Color(152, 251, 152));
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoEntity produtoSelecionado = produtos.get(table.getSelectedRow());
				carregarProdutoPorId(produtoSelecionado.getCodigoProduto());
				setVisible(true);
				
				contentPane.setBackground(new Color(119,136,153));
				
				
			}
			
		});
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnEditar.setEnabled(false);
		btnEditar.setBackground(new Color(100, 149, 237));
		
		
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBackground(new Color(240, 128, 128));
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoEntity produtoSelecionado = produtos.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Voce realmente deseja excluir o Produto de codigo " 
						+ produtoSelecionado.getCodigoProduto())== JOptionPane.OK_OPTION){
				try {
					new ProdutoService().excluirProduto(produtoSelecionado.getCodigoProduto());
					popularTabela();
				
					
				} catch (NegocioException e1) {
				JOptionPane.showMessageDialog(null, e1.getMensagemDeErro());
				}
			}
		}
	}
);
		btnExcluir.setEnabled(false);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOME PRODUTO", "EAN", "FORNECEDOR"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table);
		
		fieldPesquisa = new JTextField();
		fieldPesquisa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparCampos();
				popularTabela();
			}
		});
		fieldPesquisa.setText("Nome");
		fieldPesquisa.setBounds(350, 9, 116, 21);
		fieldPesquisa.setToolTipText("");
		fieldPesquisa.setForeground(Color.LIGHT_GRAY);
		fieldPesquisa.setHorizontalAlignment(SwingConstants.CENTER);
		fieldPesquisa.setFont(new Font("Century Gothic", Font.ITALIC, 11));
		fieldPesquisa.setColumns(10);
		
		btnVoltar = new JButton("LIMPAR");
		btnVoltar.setBounds(98, 9, 83, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				popularTabela();
				fieldPesquisa.setText("Nome");
				lblTitulo.setText("CADASTRO DE PRODUTO");
				btnVoltar.setText("LIMPAR");
				contentPane.setBackground(new Color(220, 220, 220));
			}
		});
		
		btnVoltar.setForeground(Color.BLACK);
		btnVoltar.setFont(new Font("Century Gothic", Font.BOLD, 11));
		btnVoltar.setBackground(new Color(255, 255, 0));
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(262, 11, 78, 19);
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoEntity produtoFiltro = new ProdutoEntity();
				produtoFiltro.setNomeMedicamento(fieldPesquisa.getText());
				
				popularTabelaFiltrada(produtoFiltro);
			}
		});
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fornecedor");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldFornecedor = new JTextField();
		fieldFornecedor.setColumns(10);
		fieldFornecedor.setBorder(new LineBorder(Color.WHITE, 2, true));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(83)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1_3)
							.addGap(91)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(FieldCodigo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldNomeProduto))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(fieldEan)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addComponent(fieldFornecedor, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(6, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(lblNewLabel_1)))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(FieldCodigo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldNomeProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldFornecedor, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldEan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addGap(8))
		);
		panel.setLayout(null);
		panel.add(btnSalvar);
		panel.add(btnVoltar);
		panel.add(btnBuscar);
		panel.add(fieldPesquisa);
		contentPane.setLayout(gl_contentPane);
		
		
		
		popularTabela();
	}
	
	
	private void limparCampos() {
		FieldCodigo.setText("");
		fieldNomeProduto.setText("");
		fieldEan.setText("");
		fieldFornecedor.setText("");
		fieldPesquisa.setText("");
		
	}
	private void popularTabela() {
		try {
			produtos = new ProdutoService().listarProduto();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (ProdutoEntity ProdutoEntity : produtos) {
				model.addRow(new Object[] {ProdutoEntity.getCodigoProduto(), ProdutoEntity.getNomeMedicamento(),
						ProdutoEntity.getEan(), ProdutoEntity.getFornecedor()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar produto do banco de dados" + e);	
		}
	}
	

	
	public void carregarProdutoPorId(long codigoProduto) {
		try {
			ProdutoEntity produtoEncontrado = new ProdutoService().buscarProdutoPorID(codigoProduto);
			
			if(produtoEncontrado == null) {
				JOptionPane.showMessageDialog(null, "O produto nao foi localizado", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				FieldCodigo.setText(""+produtoEncontrado.getCodigoProduto());
				fieldNomeProduto.setText(produtoEncontrado.getNomeMedicamento());
				fieldEan.setText(""+produtoEncontrado.getEan());
				fieldFornecedor.setText(produtoEncontrado.getFornecedor());				
			}
			
			lblTitulo.setText("ALTERAÇÃO DE PRODUTO");
			btnVoltar.setText("VOLTAR");
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void popularTabelaFiltrada(ProdutoEntity produtoFiltro) {
		try {
			produtos = new ProdutoService().buscarProdutoFiltrado(produtoFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (ProdutoEntity ProdutoEntity : produtos) {
				model.addRow(new Object[] {ProdutoEntity.getCodigoProduto(), ProdutoEntity.getNomeMedicamento(),
						ProdutoEntity.getEan(),ProdutoEntity.getFornecedor()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar produtos do banco de dados" + e);	
		}
	}
}
