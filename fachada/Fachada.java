package fachada;

import java.util.ArrayList;

import modelo.Autor;
import modelo.Livro;
import modelo.Emprestimo;
import modelo.Usuario;

public class Fachada {

		private static Repositorio repositorio = new Repositorio();
		//private static int idemprestimo=0;
		
		public static Livro cadastrarLivros(String titulo, ArrayList<String>nomes, int quantidade) throws Exception{
			Livro l = repositorio.localizarLivro(titulo);
			if (l!=null)
				throw new Exception ("Livro ja cadastrado: " + titulo);
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
