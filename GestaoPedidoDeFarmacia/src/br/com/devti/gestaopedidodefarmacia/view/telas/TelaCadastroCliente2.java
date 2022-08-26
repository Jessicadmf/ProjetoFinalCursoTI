package br.com.devti.gestaopedidodefarmacia.view.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import br.com.devti.gestaopedidodefarmacia.core.entity.ClienteEntity;
import br.com.devti.gestaopedidodefarmacia.core.service.ClienteService;
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



public class TelaCadastroCliente2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldNome;
	private JTextField fieldCpf;
	private JTextField fieldEmail;
	private JTextField fieldCodigo;
	private JTable table;
	private List<ClienteEntity> clientes;
	private JLabel lblTitulo;
	private JTextField fieldPesquisa;
	private JButton btnVoltar;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroCliente2 frame = new TelaCadastroCliente2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public TelaCadastroCliente2() {
		setTitle("Formulario de Clientes");
		setBackground(new Color(0, 0, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("CADASTRO DE CLIENTE");
		lblTitulo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Nome ");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		fieldNome = new JTextField();
		fieldNome.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldNome.setHorizontalAlignment(SwingConstants.LEFT);
		fieldNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldCpf = new JTextField();
		fieldCpf.setBorder(new LineBorder(Color.WHITE, 2, true));
		fieldCpf.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldEmail = new JTextField();
		fieldEmail.setBorder(new LineBorder(Color.WHITE, 2, true));
		fieldEmail.setColumns(10);
		
		fieldCodigo = new JTextField();
		fieldCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		fieldCodigo.setDisabledTextColor(Color.BLACK);
		fieldCodigo.setSelectedTextColor(Color.BLACK);
		fieldCodigo.setSelectionColor(Color.BLACK);
		fieldCodigo.setEditable(false);
		fieldCodigo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		fieldCodigo.setBackground(Color.LIGHT_GRAY);
		fieldCodigo.setForeground(Color.BLACK);
		fieldCodigo.setEnabled(false);
		fieldCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		fieldCodigo.setColumns(10);
		
		JLabel lblNewLabel_1_3 = new JLabel("Código");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 191, 444, 111);
		scrollPane.setBackground(new Color(192, 192, 192));
		scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					ClienteEntity cli = new ClienteEntity();
					cli.setNome(fieldNome.getText());
					cli.setCpf(fieldCpf.getText());
					cli.setEmail(fieldEmail.getText());
					
					String msg = null;
					try {
						
						if(fieldCodigo.getText().equals("")) {
							msg = new ClienteService().salvarCliente(cli);
						}else {
							cli.setId(Long.parseLong(fieldCodigo.getText()));
							msg = new ClienteService().alterarCliente(cli);
						}
						limparCampos();
						popularTabela();
						lblTitulo.setText("CADASTRO DE CLIENTE");
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
		btnSalvar.setFont(new Font("Century Gothic", Font.BOLD, 12));
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				carregarClientePorID(clienteSelecionado.getId());
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
				ClienteEntity clienteSelecionado = clientes.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Voce realmente deseja excluir o Cliente de codigo " 
						+ clienteSelecionado.getId())== JOptionPane.OK_OPTION){
				try {
					new ClienteService().excluirCliente(clienteSelecionado.getId());
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
				"ID CLIENTE", "NOME CLIENTE", "CPF CLIENTE", "EMAIL CLIENTE"
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
		
		btnVoltar = new JButton("LIMPAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				popularTabela();
				lblTitulo.setText("CADASTRO DE CLIENTE");
				btnVoltar.setText("LIMPAR");
				contentPane.setBackground(new Color(220, 220, 220));
			}
		});
		
		btnVoltar.setForeground(Color.BLACK);
		btnVoltar.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnVoltar.setBackground(new Color(255, 255, 0));
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteEntity clienteFiltro = new ClienteEntity();

				clienteFiltro.setNome(fieldPesquisa.getText());
				popularTabelaFiltrada(clienteFiltro);
				
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		
		fieldPesquisa = new JTextField();
		fieldPesquisa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparCampos();
				popularTabela();
			}
		});
		
		fieldPesquisa.setToolTipText("");
		fieldPesquisa.setText("Nome");
		fieldPesquisa.setHorizontalAlignment(SwingConstants.CENTER);
		fieldPesquisa.setForeground(Color.LIGHT_GRAY);
		fieldPesquisa.setFont(new Font("Century Gothic", Font.ITALIC, 11));
		fieldPesquisa.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(fieldPesquisa, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(fieldPesquisa, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSalvar)
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1_3)
					.addGap(95)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(fieldNome, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(fieldEmail, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
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
						.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		popularTabela();
	}
	
	
	private void limparCampos() {
		fieldCodigo.setText("");
		fieldNome.setText("");
		fieldCpf.setText("");
		fieldEmail.setText("");
		fieldPesquisa.setText("");
		
	}
	private void popularTabela() {
		try {
			clientes = new ClienteService().listarCliente();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (ClienteEntity clienteEntity : clientes) {
				model.addRow(new Object[] {clienteEntity.getId(), clienteEntity.getNome(),
							clienteEntity.getCpf(),clienteEntity.getEmail()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar clientes do banco de dados" + e);	
		}
	}
	
	public void carregarClientePorID(long codigoCliente) {
		try {
			ClienteEntity clienteEncontrado = new ClienteService().buscarClientePorID(codigoCliente);
			
			if(clienteEncontrado == null) {
				JOptionPane.showMessageDialog(null,"O cliente não foi localizado","Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText(""+ clienteEncontrado.getId());
				fieldNome.setText(clienteEncontrado.getNome());
				fieldCpf.setText(clienteEncontrado.getCpf());
				fieldEmail.setText(clienteEncontrado.getEmail());
			}
			lblTitulo.setText("ALTERAÇÃO DE CLIENTE");
			btnVoltar.setText("VOLTAR");
			
			
			
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(),"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void popularTabelaFiltrada(ClienteEntity clienteFiltro) {
		try {
			clientes = new ClienteService().buscarClienteFiltrado(clienteFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (ClienteEntity clienteEntity : clientes) {
				model.addRow(new Object[] {clienteEntity.getId(), clienteEntity.getNome(),
							clienteEntity.getCpf(),clienteEntity.getEmail()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar clientes do banco de dados" + e);	
		}
	}
		

}
