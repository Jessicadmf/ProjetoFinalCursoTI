package br.com.devti.gestaopedidodefarmacia.view.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.devti.gestaopedidodefarmacia.core.entity.AtendenteEntity;

import br.com.devti.gestaopedidodefarmacia.core.service.AtendenteService;

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


public class TelaCadastroAtendente extends JFrame {

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
	private JLabel lblTitulo;
	private JTextField fieldPesquisa;
	private List<AtendenteEntity> atendente;
	private JTextField fieldCargo;
	private JButton btnVoltar;
	
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//					try {
//						TelaCadastroAtendente frame = new TelaCadastroAtendente();
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
	
	public TelaCadastroAtendente() {
		setTitle("Formulario de Atendente");
		setBackground(Color.MAGENTA);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("CADASTRO DE ATENDENTE");
		lblTitulo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Nome ");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		fieldNome = new JTextField();
		fieldNome.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cpf");
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
		btnSalvar.setBounds(10, 9, 78, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					 AtendenteEntity ate = new AtendenteEntity();
					ate.setNomeAtendente(fieldNome.getText());
					ate.setCpf(fieldCpf.getText());
					ate.setCargo(fieldCargo.getText());
					ate.setEmailAtendente(fieldEmail.getText());
					
					String msg = null;
					try {
						
						if(fieldCodigo.getText().equals("")) {
							msg = new AtendenteService().salvarAtendente(ate);
						}else {
							ate.setIdAtendente(Long.parseLong(fieldCodigo.getText()));
							msg = new AtendenteService().alterarAtendente(ate);
						}
						limparCampos();
						popularTabela();
						lblTitulo.setText("CADASTRO DE ATENDENTE");
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
				AtendenteEntity atendenteSelecionado = atendente.get(table.getSelectedRow());
				carregarAtendentePorId(atendenteSelecionado.getIdAtendente());
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
				AtendenteEntity atendenteSelecionado = atendente.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Voce realmente deseja excluir o Atendente de codigo " 
						+ atendenteSelecionado.getIdAtendente())== JOptionPane.OK_OPTION){
				try {
					new AtendenteService().excluirAtendente(atendenteSelecionado.getIdAtendente());
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
				"ID", "NOME", "EMAIL", "CARGO", "CPF"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
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
		btnVoltar.setBounds(98, 9, 85, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				popularTabela();
				fieldPesquisa.setText("Nome");
				lblTitulo.setText("CADASTRO DE ATENDENTE");
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
				AtendenteEntity atendenteFiltro = new AtendenteEntity();
				
				atendenteFiltro.setNomeAtendente(fieldPesquisa.getText());
				popularTabelaFiltrada(atendenteFiltro);
			
			}
		});
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cargo");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldCargo = new JTextField();
		fieldCargo.setColumns(10);
		fieldCargo.setBorder(new LineBorder(Color.WHITE, 2, true));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
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
					.addComponent(fieldCodigo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(lblNewLabel_1_2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldCargo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldEmail))
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)))
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
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fieldCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(fieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(fieldCargo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
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
		fieldCodigo.setText("");
		fieldNome.setText("");
		fieldCpf.setText("");
		fieldCargo.setText("");
		fieldEmail.setText("");
		fieldPesquisa.setText("");
		
	}
	private void popularTabela() {
		try {
			atendente = new AtendenteService().listarAtendente();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (AtendenteEntity AtendenteEntity : atendente) {
				model.addRow(new Object[] {AtendenteEntity.getIdAtendente(), AtendenteEntity.getNomeAtendente(),
						AtendenteEntity.getEmailAtendente(), AtendenteEntity.getCargo(),AtendenteEntity.getCpf()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar atendente do banco de dados" + e);	
		}
	}
	

	
	public void carregarAtendentePorId(long codigoAtendente) {
		try {
			AtendenteEntity atendenteEncontrado = new AtendenteService().buscarAtendentePorID(codigoAtendente);
			
			if(atendenteEncontrado == null) {
				JOptionPane.showMessageDialog(null, "O usuário nao foi localizado", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText(""+atendenteEncontrado.getIdAtendente());
				fieldNome.setText(atendenteEncontrado.getNomeAtendente());
				fieldCpf.setText(atendenteEncontrado.getCpf());
				fieldCargo.setText(atendenteEncontrado.getCargo());
				fieldEmail.setText(atendenteEncontrado.getEmailAtendente());				
			}
			
			lblTitulo.setText("ALTERAÇÃO DE ATENDENTE");
			btnVoltar.setText("VOLTAR");
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void popularTabelaFiltrada(AtendenteEntity atendenteFiltro) {
		try {
			atendente = new AtendenteService().buscarAtendenteFiltrado(atendenteFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (AtendenteEntity AtendenteEntity : atendente) {
				model.addRow(new Object[] {AtendenteEntity.getIdAtendente(), AtendenteEntity.getNomeAtendente(),
						AtendenteEntity.getEmailAtendente(),AtendenteEntity.getCargo(),AtendenteEntity.getCpf()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar atendente do banco de dados" + e);	
		}
	}
}
