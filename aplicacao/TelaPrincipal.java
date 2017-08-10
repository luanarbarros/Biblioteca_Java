package aplicacao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;


public class TelaPrincipal {
	private JFrame frmPrincipal;
	private JMenu mnLog;
	private JMenuItem mntmLogoff;
	private JMenuItem mntmLogin;
	//private JMenu mnCadastrar;
	//private JMenuItem mntmAluno;
	//private JMenu mnListar;
	//private JMenuItem mntmEspionar;
	//private JMenuItem mntmCxSaida;
//	private JMenuItem mntmLer;
	//private JMenuItem mntmMensagem;
	//private JMenuItem mntmMsg_1;
	private JMenuItem mnBuscar;
	private JMenuItem mntmBuscarLivro;
	private JMenuItem mntmBuscarAutor;
	private JMenuItem mntmListarEmp;
	private JMenu mnUsuarios;
	private JMenuItem mntmEmprestimos;
	private JMenuItem mntmDevolucao;
	private JMenuItem mntmListarMeusEmp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		try {
			frmPrincipal.setContentPane(new FundoTela("library.jpg"));
		} catch (IOException e1) {
		}	

		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				JOptionPane.showMessageDialog(null, "sistema inicializado !");
				try {
					ArrayList<String> autores1 = new ArrayList<String>();
					autores1.add("Oppenhein, A.V.");
					autores1.add("Willsky, A.S.");
					Fachada.cadastrarLivros("Sinais e Sistemas", autores1, 2);
					
					ArrayList<String> autores2 = new ArrayList<String>();
					autores2.add("Oppenhein, A.V.");
					autores2.add("Schafer, R. W.");
					Fachada.cadastrarLivros("Processamento de Sinais em Tempo Discreto", autores2, 2);
					
					ArrayList<String> autores3 = new ArrayList<String>();
					autores3.add("Prestes, R.");
					autores3.add("Wolf, O.");
					Fachada.cadastrarLivros("Robotica Movel", autores3, 2);
					
					ArrayList<String> autores4 = new ArrayList<String>();
					autores4.add("Sedra, A.S.");
					autores4.add("Smith, K.C.");
					Fachada.cadastrarLivros("Microeletronica", autores4, 2);
					
					ArrayList<String> autores5 = new ArrayList<String>();
					autores5.add("Coelho, A.A.R.");
					autores5.add("Coelho, L.S.");
					Fachada.cadastrarLivros("Identificacao de Sistemas Dinamicos Lineares", autores5, 2);
					
					Fachada.cadastrarUsuario("Luana", "lu1234");
					Fachada.cadastrarUsuario("Daltro", "D1234");
					
					//Fachada.cadastrarPessoa("joao","joao@", "1");
					//Fachada.cadastrarPessoa("maria","maria@", "1");
					//Fachada.cadastrarPessoa("jose","jose@", "1");	
					//Fachada.cadastrarPessoa("admin","admin@", "1");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "cadastro incorreto !");
				}

			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "até breve !");

			}
		});
		frmPrincipal.setTitle("Biblioteca");
		frmPrincipal.setBounds(100, 100, 436, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		/*************************************************************************************/
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		/*************************************************************************************/
		mnLog = new JMenu("Logar");
		menuBar.add(mnLog);

		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin telalogin = new TelaLogin();
				telalogin.setVisible(true);
			}
		});
		mnLog.add(mntmLogin);

		mntmLogoff = new JMenuItem("Logoff");
		mntmLogoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Fachada.logoff();
					JOptionPane.showMessageDialog(null,"Até breve");
				}catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		mnLog.add(mntmLogoff);
		
		/*************************************************************************************/	
		
		mnBuscar = new JMenu("Buscar");
		menuBar.add(mnBuscar);
				
		mntmBuscarLivro = new JMenuItem("Buscar por Livro");
		mntmBuscarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fachada.logoff();
				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnBuscar.add(mntmBuscarLivro);
		
		mntmBuscarAutor = new JMenuItem("Buscar por Autor");
		mntmBuscarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fachada.logoff();
				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnBuscar.add(mntmBuscarAutor);
		
		mntmListarEmp = new JMenuItem("Listar Emprestimos");
		mntmListarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fachada.logoff();
				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnBuscar.add(mntmListarEmp);

		/*************************************************************************************/
		mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
				
		mntmEmprestimos = new JMenuItem("Emprestimos");
		mntmEmprestimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fachada.logoff();
				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnUsuarios.add(mntmEmprestimos);
		
		mntmDevolucao = new JMenuItem("Devolucao");
		mntmDevolucao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fachada.logoff();
				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnUsuarios.add(mntmDevolucao);
		
		mntmListarMeusEmp = new JMenuItem("Listar Meus Emprestimos");
		mntmListarMeusEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fachada.logoff();
				JOptionPane.showMessageDialog(null,"Até breve");
			}
		});
		mnUsuarios.add(mntmListarMeusEmp);
		/*************************************************************************************/
/*		mnCadastrar = new JMenu("Pessoas");
		menuBar.add(mnCadastrar);

		mntmAluno = new JMenuItem("Cadastrar Aluno");
		mntmAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	TelaCadastroAluno al = new TelaCadastroAluno();
			//	al.setVisible(true);
			}
		});
		mnCadastrar.add(mntmAluno);

		JMenuItem mntmPessoas = new JMenuItem("Listar");
		mnCadastrar.add(mntmPessoas);
		mntmPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	TelaListarPessoa tela = new TelaListarPessoa();
			//	tela.setVisible(true);
			}
		});*/
		
		/*************************************************************************************/
		/*
		mnListar = new JMenu("Mensagens");
		menuBar.add(mnListar);

		mntmMsg_1 = new JMenuItem("Enviar");
		mnListar.add(mntmMsg_1);
		mntmMsg_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	TelaEnviarMsg tela = new TelaEnviarMsg();
			//	tela.setVisible(true);
			}
		});

		mntmMensagem = new JMenuItem("Excluir");
		mntmMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Id:");
				try {
					//Fachada.excluirMensagem(Integer.parseInt(id));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"numero invalido");
				}


			}
		});
		mnListar.add(mntmMensagem);
		
		mntmCxSaida = new JMenuItem("Caixa de Entrada/Saida");
		mntmCxSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	TelaListarCaixa tela = new TelaListarCaixa();
			//	tela.setVisible(true);
			}
		});
		mnListar.add(mntmCxSaida);


		mntmEspionar = new JMenuItem("Espionar");
		mntmEspionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	TelaEspionagem tela = new TelaEspionagem();
			//	tela.setVisible(true);
			}
		});
		mnListar.add(mntmEspionar);
		*/
	}
}
