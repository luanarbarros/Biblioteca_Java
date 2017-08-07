package fachada;

import java.util.ArrayList;

import modelo.Autor;
import modelo.Livro;
import modelo.Emprestimo;
import modelo.Usuario;

public class Fachada {

		// ATRIBUTOS DA CLASSE
		private static Repositorio repositorio = new Repositorio();
		private static Usuario logado = null; // armazena o usu�rio logado
		private static int idemprestimo=0; 
		
		//M�TODOS PARA FAZER LOGIN E LOGOFF
		
		public static Usuario login (String nome, String senha) throws Exception
		{
			Usuario u = repositorio.localizarUsuario(nome);
			if (u == null)
			{
				throw new Exception ("Erro: Usu�rio n�o cadastrado");
			}
			else if (logado != null)
			{
				throw new Exception ("Erro: J� existe um usu�rio logado");
			}
			else
			{
				logado = u;
			}
			return logado;
		}
		
		public static void logoff () throws Exception
		{
			if (logado == null)
			{
				throw new Exception ("N�o h� usu�rio logado");
			}
			else
			{
				logado = null;
			}
		}
		
		public static Usuario getLogado ()
		{
			return logado;
		}
		
		
		//M�TODOS DE CADASTRO
		
		public static Livro cadastrarLivros(String titulo, ArrayList<String>nomes, int quantidade) throws Exception{
			Livro l = repositorio.localizarLivro(titulo);
			if (l!=null)
				throw new Exception ("Livro ja cadastrado: " + titulo + "\n");
			
			//Impede autores duplicados
			for(int i=0; i < nomes.size(); i++){
				for(int j=0; j < nomes.size(); j++){
					if(i!=j){
						if(nomes.get(i) == nomes.get(j)){
							throw new Exception ("Erro: Autores duplicados:" + nomes.get(i)  + "\n");
						}
					}
				}
			}
			
			l = new Livro(titulo, quantidade);
			
			for (String n: nomes){
				Autor a = new Autor(n);
				l.adicionarAutor(a);
				a.adicionarLivro(l);
				if(repositorio.localizarAutor(n) == null)
					repositorio.adicionarAutor(a);	
				else{
					repositorio.localizarAutor(n).adicionarLivro(l);
				}
			}
			repositorio.adicionarLivro(l);
			return l;
		}
		
		
		public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
			Usuario u = repositorio.localizarUsuario(nome);
			if(u!=null){
				throw new Exception ("Usuario ja cadastrado");
			}
			else
			{
				u=new Usuario(nome, senha);
				repositorio.adicionarUsuario(u);
				return u;
			}
		}
		
		
		// M�TODOS DE LISTAGEM
		
		public static ArrayList<Livro> listarLivros(){
			return repositorio.getLivros();
		}
		
		public static ArrayList<Autor> listarAutores(){
			return repositorio.getAutores();
		}
		
		public static ArrayList<Emprestimo> listarEmprestimos(){
			return repositorio.getEmprestimos();
		}
		
		public static ArrayList<Usuario> listarUsuario(){
			return repositorio.getUsuarios();
		}
}
