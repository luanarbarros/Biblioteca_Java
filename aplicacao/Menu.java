package aplicacao;


import java.util.ArrayList;
import java.util.Scanner;

import fachada.Fachada;
import modelo.Autor;
import modelo.Usuario;
import modelo.Livro;
import modelo.Emprestimo;

public class Menu {
	
	
	public static void main(String[] args)
	{
			int opcao;
			Scanner teclado = new Scanner (System.in);
			String nome, senha;
			
			try
			{
				preCadastroLivros();
				precadastroUsuarios();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			
			exibirMenu();
			
			System.out.print("\n\n Opção: ");
			opcao = teclado.nextInt();
			switch (opcao)
			{
				case 1:
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
					break;
				case 2:
						
			}
			
	}
	
	public static void exibirMenu ()
	{
		String text1 = "\n\n1. login\n2. Logoff\n3. Listar Livros\n4. Buscar livro por titulo\n5. Buscar livro por autor\n6. Listar emprestimos\n";
		String text2 = text1 + "7. Emprestimo\n8. Devolução\n9. Listar meus emprestimos";
		if (Fachada.getLogado() == null)
			System.out.println(text1);
		else
			System.out.println(text2);
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
		Fachada.cadastrarUsuario("Luana", "lu1234");
		Fachada.cadastrarUsuario("Daltro", "D1234");
	}

}
