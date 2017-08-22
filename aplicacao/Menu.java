package aplicacao;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import fachada.Fachada;
import modelo.Administrador;
import modelo.Aluno;
import modelo.Autor;
import modelo.Usuario;
import modelo.Livro;
import modelo.Emprestimo;
import modelo.Funcionario;

public class Menu {
		
	public static void main(String[] args)
	{
			int opcao;
			int id;
			Scanner teclado = new Scanner (System.in);
			String nome = "";
			String senha = "";
			String titulo = "";
			
			String fragmento = "Sinais"; // APENAS PARA TESTE - APAGAR 
			ArrayList<Livro> livrosListados= new ArrayList<Livro> ();
			
			Emprestimo emprestimo;
			
			try
			{
				preCadastroLivros();
				precadastroUsuarios();
				simularLogin("Daltro","D1234");
				simularEmprestimo("Sinais e Sistemas");
				simularDevolucao(1);
				simularLogoff();
				simularLogin("Admin","Admin");
				simularEmprestimo("Microeletronica");
								
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			do{
				//listarTodasInfomacoes ();
				exibirMenu();
				
				System.out.print("\n\nOpção: ");
				opcao = teclado.nextInt();
				teclado.nextLine();
				switch (opcao)
				{
					case 0:
						System.out.println("Bye!\n");
						break;
					case 1:
						if (Fachada.getLogado() == null)
						{
							System.out.print("Nome do usuario: ");
							nome = teclado.nextLine();
							System.out.println("\nSenha: ");
							senha = teclado.nextLine();
							try
							{
								Fachada.login(nome, senha);
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
							}
						}
						else
						{
							System.out.println("\nJá existe um usuário logado!\n");
						}
						break;
					case 2:
						try{
						Fachada.logoff();
						}
						catch (Exception e)
						{
							System.out.println(e.getMessage());
						}
						break;
					case 3:
						imprimirListaLivros ();
						break;
					case 4:
						System.out.print("Digite o título do livro: ");
						fragmento = teclado.nextLine();
						livrosListados = Fachada.listarLivrosPorFragmentoDoTitulo(fragmento);
						if (livrosListados == null)
							System.out.println("\nNenhum livro encontrado!\n");
						else
						{
							for (Livro l: livrosListados)
								System.out.println("\nTitulo: " + l.getTitulo() + "\n" + l.getNomeDosAutores());
						}
						livrosListados = null;
						break;
					case 5:
						System.out.print("Digite o nome do autor: ");
						fragmento = teclado.nextLine();
						livrosListados = Fachada.listarLivrosPorFragmentoDoAutor(fragmento);
						if (livrosListados == null)
							System.out.println("\nNenhum livro encontrado!\n");
						else
						{
							for (Livro l: livrosListados)
								System.out.println("\nTitulo: " + l.getTitulo() + "\n" + l.getNomeDosAutores());
						}
						livrosListados = null;
						break;
					case 6:
						imprimirListaUsuarios();
						break;
					case 7:
						imprimirEmprestimos();
						break;
					case 8:
						if (Fachada.getLogado() != null)
						{
							System.out.print("Digite o título do livro: ");
							titulo = teclado.nextLine();
							try{
								DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								emprestimo = Fachada.criarEmprestimo(titulo);
								LocalDate dataemp = LocalDate.parse(emprestimo.getDataemp(),formatador);
								LocalDate DataVencimento = dataemp.plusDays(Fachada.getLogado().getPrazo());
								String StringProvavelVencimento = DataVencimento.format(formatador);
								 
								System.out.println("ID: " + emprestimo.getId() + "\nProvavel data de Devolução: " + StringProvavelVencimento );
							}
							catch (Exception e){
								System.out.println(e.getMessage());
							}
							titulo = null;
							emprestimo = null;
						}
						else 
						{
							System.out.println("Não há usuários logados\nLogue para desbloquear essa opção!\n");
						}
						break;
					case 9:
						if (Fachada.getLogado() != null)
						{
							System.out.println("Digite o ID do empréstimo: ");
							id = teclado.nextInt();
							teclado.nextLine();
							try
							{
								emprestimo = Fachada.criarDevolucao(id); 
								System.out.println("ID: " + emprestimo.getId() + "\nMulta: " + emprestimo.getMulta() );
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
							}
							emprestimo = null;
						}
						else {
							System.out.println("Não há usuários logados\nLogue para desbloquear essa opção!\n");
						}
						break;
					case 10:
						if (Fachada.getLogado() != null)
						{
							ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo> ();
							emprestimos = Fachada.getLogado().getEmprestimos();
							if (!emprestimos.isEmpty())
							{	
								for (Emprestimo e: emprestimos)
								{
									System.out.println(e.toString() + "\n\n");
								}
							}
							else {
								System.out.println(Fachada.getLogado().getNome() +  " não possui emprestimos!\n");
							}
						}
						else 
						{
							System.out.println("Não há usuários logados\nLogue para desbloquear essa opção!\n");
						}
						break;
					case 11:
						  String departamentoCurso;
						  String tipo;
						if (Fachada.getLogado() instanceof Administrador)
						{
							System.out.print("Nome do usuario: ");
							nome = teclado.nextLine();
							System.out.println("\nSenha: ");
							senha = teclado.nextLine();
							System.out.println("\nTipo de usuário: ");
							tipo = teclado.nextLine();
							System.out.println("\nDepartamento ou Curso: ");
							departamentoCurso = teclado.nextLine();
							try {
							Fachada.cadastrarUsuario(nome, senha, tipo, departamentoCurso);
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
							}
							
						}
						else
						{
							System.out.println("Você não possui permições para executar essa tarefa");
						}
						break;
					case 12:
						if (Fachada.getLogado() instanceof Administrador)
						{
							System.out.print("Nome do usuario: ");
							nome = teclado.nextLine();
							try
							{
								Fachada.excluirUsuario(nome);
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
							}
							
						}
						else
						{
							System.out.println("Você não possui permições para executar essa tarefa");
						}
						break;
					default:
						System.out.println("Não é uma opção válida");
						break;
				}
			
			}while (opcao!=0);
	}
	
	public static void exibirMenu ()
	{
		String text1 = "\n\n1. login\n2. Logoff\n3. Listar Livros\n4. Buscar livro por titulo\n5. Buscar livro por autor\n6.Listagem de usuários\n7. Listar emprestimos\n";
		String text2 = text1 + "8. Emprestimo\n9. Devolução\n10. Listar meus emprestimos\n";
		String text3 = text2 + "11. Cadastrar usuário\n12. Excluir usuário";
		if (Fachada.getLogado() == null)
			System.out.println(text1 + "\n0. sair");
		else if ( Fachada.getLogado() instanceof Administrador )
		{
			
			System.out.println(text3 + "\n0. sair");
		}
		else if ( (Fachada.getLogado() instanceof Aluno) || (Fachada.getLogado() instanceof Funcionario) )  
		{
			
			System.out.println(text2 + "\n0. sair");
		}
		else
		{
			System.out.println("Erro! Tipo de usuário desconhecido!");
		}
	}
	
	public static void preCadastroLivros() throws Exception
	{
	
		Livro l;
		ArrayList<String> autores1 = new ArrayList<String>();
		autores1.add("Oppenhein, A.V.");
		autores1.add("Willsky, A.S.");
		l = Fachada.cadastrarLivros("Sinais e Sistemas", autores1, 2);
		
		ArrayList<String> autores2 = new ArrayList<String>();
		autores2.add("Oppenhein, A.V.");
		autores2.add("Schafer, R. W.");
		l = Fachada.cadastrarLivros("Processamento de Sinais em Tempo Discreto", autores2, 2);
		
		ArrayList<String> autores3 = new ArrayList<String>();
		autores3.add("Prestes, R.");
		autores3.add("Wolf, O.");
		l = Fachada.cadastrarLivros("Robotica Movel", autores3, 2);
		
		ArrayList<String> autores4 = new ArrayList<String>();
		autores4.add("Sedra, A.S.");
		autores4.add("Smith, K.C.");
		l = Fachada.cadastrarLivros("Microeletronica", autores4, 2);
		
		ArrayList<String> autores5 = new ArrayList<String>();
		autores5.add("Coelho, A.A.R.");
		autores5.add("Coelho, L.S.");
		l = Fachada.cadastrarLivros("Identificacao de Sistemas Dinamicos Lineares", autores5, 2);
					
	}
	
	public static void precadastroUsuarios() throws Exception 
	{
		//Fachada.cadastrarUsuario("Luana", "lu1234", "Aluno", "Eng. Elétrica");
		Fachada.cadastrarUsuario("Daltro", "D1234", "Aluno", "Eng. Elétrica");
		Fachada.cadastrarUsuario("Admin", "Admin", "Administrador", "Diretoria");
		//Fachada.cadastrarUsuario("Zeneide", "Z1234", "Funcionario", "Organizadora dos Livros");
	}
	
	public static void listarTodasInfomacoes()
	{
		imprimirListaLivros ();
		System.out.println("************************************************************");
		String texto;
		ArrayList<Autor> listaAutores = Fachada.listarAutores();
		texto = "Lista de Autores \n";
		if(listaAutores.isEmpty()){
			texto+= "Nao existem autores cadastrados \n";
		}
		else{
			for(Autor a: listaAutores)
				texto+= a.toString() + "\n";
		}
		System.out.println(texto);	
		System.out.println("************************************************************");
		
		ArrayList<Usuario> listaUsuarios = Fachada.listarUsuarios();
		texto = "Lista de Usuarios \n";
		if(listaUsuarios.isEmpty()){
			texto+= "Nao existem usuarios cadastrados \n";
		}
		else{
			for(Usuario a: listaUsuarios)
				texto+= a.toString() + "\n";
		}
		System.out.println(texto);
		System.out.println("************************************************************");
		
		
				
	}
	
	public static void imprimirEmprestimos (){
		
		String texto;
		ArrayList<Emprestimo> listaEmprestimos = Fachada.listarEmprestimos();
		texto = "Lista de Emprestimos da Biblioteca\n";
		if(listaEmprestimos.isEmpty()){
			texto+= "Nao há empréstimos no momento\n";
		}
		else{
			for(Emprestimo e: listaEmprestimos)
				//texto+= e.getLivro().getTitulo() + "\n" + "ID: "+ e.getId();
				texto+= e.toString() + "\n";
		}
		System.out.println(texto);	
	}
			
	public static void imprimirListaLivros ()
	{
		String texto;
		ArrayList<Livro> listaLivros = Fachada.listarLivros();
		texto = "Lista de Livros: \n";
		if(listaLivros.isEmpty()){
			texto+= "Nao existem livros cadastrados \n";
		}
		else{
			for(Livro l: listaLivros)
				texto+= l.toString() + "\n";
		}
		System.out.println(texto);
	}

	public static void imprimirListaUsuarios ()
	{
		ArrayList <Usuario> usuarios = Fachada.listarUsuarios();
		for (Usuario u: usuarios)
		{
			System.out.println( u.getNome() +  (( u instanceof  Funcionario )? " - Funcionário": " - Aluno") );
		}
	}

	public static void simularEmprestimo (String titulo) throws Exception
	{
		Fachada.criarEmprestimo(titulo);
	
	}

	public static void simularDevolucao (int id) throws Exception
	{
		Fachada.criarDevolucao(id); 
	}
	
	public static void simularLogin(String nome, String senha) throws Exception
	{
		Fachada.login(nome, senha);
	}

	public static void simularLogoff() throws Exception
	{
		Fachada.logoff();
	}
}
