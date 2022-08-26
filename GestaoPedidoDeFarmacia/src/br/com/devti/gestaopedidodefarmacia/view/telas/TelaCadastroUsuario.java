package br.com.devti.gestaopedidodefarmacia.view.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import br.com.devti.gestaopedidodefarmacia.core.entity.UsuarioEntity;

import br.com.devti.gestaopedidodefarmacia.core.service.UsuarioService;
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




import javax.swing.JPasswordField;



public class TelaCadastroUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fieldNome;
	private JTextField fieldLogin;
	private JTextField fieldEmail;
	private JTextField fieldCodigo;
	private JTable table;
	private JLabel lblTitulo;
	private JTextField fieldPesquisa;
	private List<UsuarioEntity> usuarios;
	private List<UsuarioEntity> usuario;
	private JButton btnVoltar;


	private JPasswordField fieldSenha;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroUsuario frame = new TelaCadastroUsuario();
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
	
	public TelaCadastroUsuario() {
		setTitle("Formulario de Usuário");
		setBackground(new Color(0, 0, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblTitulo = new JLabel("CADASTRO DE USUÁRIO");
		lblTitulo.setBounds(88, 5, 341, 28);
		lblTitulo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Nome ");
		lblNewLabel_1.setBounds(145, 45, 107, 15);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		fieldNome = new JTextField();
		fieldNome.setBounds(145, 66, 348, 18);
		fieldNome.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		fieldNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Login");
		lblNewLabel_1_1.setBounds(15, 95, 91, 16);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldLogin = new JTextField();
		fieldLogin.setBounds(15, 117, 124, 18);
		fieldLogin.setBorder(new LineBorder(Color.WHITE, 2, true));
		fieldLogin.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setBounds(275, 95, 28, 16);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldEmail = new JTextField();
		fieldEmail.setBounds(275, 117, 218, 18);
		fieldEmail.setBorder(new LineBorder(Color.WHITE, 2, true));
		fieldEmail.setColumns(10);
		
		fieldCodigo = new JTextField();
		fieldCodigo.setBounds(15, 66, 124, 18);
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
		lblNewLabel_1_3.setBounds(15, 44, 39, 16);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 152, 478, 39);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 191, 444, 111);
		scrollPane.setBackground(new Color(192, 192, 192));
		scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setBounds(10, 9, 78, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					 UsuarioEntity usu = new UsuarioEntity();
					usu.setNome(fieldNome.getText());
					usu.setLogin(fieldLogin.getText());
					usu.setSenha(new String(fieldSenha.getPassword()));
					usu.setEmail(fieldEmail.getText());
					
					String msg = null;
					try {
						
						if(fieldCodigo.getText().equals("")) {
							msg = new UsuarioService().salvarUsuario(usu);
						}else {
							usu.setCodigo(Long.parseLong(fieldCodigo.getText()));
							msg = new UsuarioService().alterarUsuario(usu);
						}
						limparCampos();
						popularTabela();
						lblTitulo.setText("CADASTRO DE USUÁRIO");
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
		btnEditar.setBounds(305, 322, 91, 25);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEntity usuarioSelecionado = usuarios.get(table.getSelectedRow());
				carregarUsuarioPorID(usuarioSelecionado.getCodigo());
				setVisible(true);
				btnVoltar.setText("VOLTAR");
				contentPane.setBackground(new Color(119,136,153));
				
				
			}
			
		});
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnEditar.setEnabled(false);
		btnEditar.setBackground(new Color(100, 149, 237));
		
		
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(402, 322, 91, 25);
		btnExcluir.setBackground(new Color(240, 128, 128));
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEntity usuarioSelecionado = usuarios.get(table.getSelectedRow());
				if(JOptionPane.showConfirmDialog(null, "Voce realmente deseja excluir o Usuario de codigo " 
						+ usuarioSelecionado.getCodigo())== JOptionPane.OK_OPTION){
				try {
					new UsuarioService().excluirUsuario(usuarioSelecionado.getCodigo());
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
		scrollPane_1.setBounds(15, 197, 478, 102);
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOME", "LOGIN ", "SENHA", "EMAIL"
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
		btnVoltar.setBounds(98, 9, 78, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				popularTabela();
				lblTitulo.setText("CADASTRO DE USUÁRIO");
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
				UsuarioEntity usuarioFiltro = new UsuarioEntity();

				usuarioFiltro.setNome(fieldPesquisa.getText());
				popularTabelaFiltrada(usuarioFiltro);
				
			}
		});
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1.setBounds(145, 95, 91, 16);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
		
		fieldSenha = new JPasswordField();
		fieldSenha.setBounds(145, 117, 124, 18);
		fieldSenha.setBorder(new LineBorder(Color.WHITE));
		panel.setLayout(null);
		panel.add(btnSalvar);
		panel.add(btnVoltar);
		panel.add(btnBuscar);
		panel.add(fieldPesquisa);
		contentPane.setLayout(null);
		contentPane.add(lblTitulo);
		contentPane.add(lblNewLabel_1_3);
		contentPane.add(lblNewLabel_1);
		contentPane.add(fieldCodigo);
		contentPane.add(fieldNome);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(lblNewLabel_1_1_1);
		contentPane.add(scrollPane_1);
		contentPane.add(panel);
		contentPane.add(fieldLogin);
		contentPane.add(fieldSenha);
		contentPane.add(lblNewLabel_1_2);
		contentPane.add(fieldEmail);
		contentPane.add(btnEditar);
		contentPane.add(btnExcluir);
		
		
		
		popularTabela();
	}
	
	
	private void limparCampos() {
		fieldCodigo.setText("");
		fieldNome.setText("");
		fieldLogin.setText("");
		fieldSenha.setText("");
		fieldEmail.setText("");
		fieldPesquisa.setText("");
		
	}
	private void popularTabela() {
		try {
			usuarios = new UsuarioService().listarUsuario();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (UsuarioEntity UsuarioEntity : usuarios) {
				model.addRow(new Object[] {UsuarioEntity.getCodigo(), UsuarioEntity.getNome(),
							UsuarioEntity.getLogin(), UsuarioEntity.getSenha(),UsuarioEntity.getEmail()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar usuario do banco de dados" + e);	
		}
	}
	
	public void carregarUsuarioPorID(Long codigoUsuario) {
		try {
			UsuarioEntity usuarioEncontrado = new UsuarioService().buscarUsuarioPorId(codigoUsuario);
			
			if(usuarioEncontrado == null) {
				JOptionPane.showMessageDialog(null,"O usuario não foi localizado","Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText(""+ usuarioEncontrado.getCodigo());
				fieldNome.setText(usuarioEncontrado.getNome());
				fieldLogin.setText(usuarioEncontrado.getLogin());
				fieldSenha.setText(usuarioEncontrado.getSenha());
				fieldEmail.setText(usuarioEncontrado.getEmail());
			}
			lblTitulo.setText("ALTERAÇÃO DE USUÁRIO");
			
			
			
			
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(),"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public void carregarUsuarioPorId(long codigoUsuario) {
		try {
			UsuarioEntity usuarioEncontrado = new UsuarioService().buscarUsuarioPorId(codigoUsuario);
			
			if(usuarioEncontrado == null) {
				JOptionPane.showMessageDialog(null, "O usuário nao foi localizado", "Erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldCodigo.setText(""+usuarioEncontrado.getCodigo());
				fieldNome.setText(usuarioEncontrado.getNome());
				fieldLogin.setText(usuarioEncontrado.getLogin());
				fieldSenha.setText(usuarioEncontrado.getSenha());
				fieldEmail.setText(usuarioEncontrado.getEmail());				
			}
			
			lblTitulo.setText("ALTERAÇÃO DE USUARIO");
		
			
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemDeErro(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void popularTabelaFiltrada(UsuarioEntity usuarioFiltro) {
		try {
			usuario = new UsuarioService().buscarUsuarioFiltrado(usuarioFiltro);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();
			
			for (UsuarioEntity UsuarioEntity : usuario) {
				model.addRow(new Object[] {UsuarioEntity.getCodigo(), UsuarioEntity.getNome(),
						UsuarioEntity.getLogin(),UsuarioEntity.getSenha(),UsuarioEntity.getEmail()});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null,"Erro ao buscar usuario do banco de dados" + e);	
		}
	}	
}
