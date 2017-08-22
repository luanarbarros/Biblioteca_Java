package fachada;

import java.util.ArrayList;
import java.util.TreeMap;

import modelo.Autor;
import modelo.Emprestimo;
import modelo.Livro;
import modelo.Usuario;

public class Repositorio {
	
	//private ArrayList<Autor> autores = new ArrayList<Autor>();
	private TreeMap <String,Autor> autores = new TreeMap <String,Autor> (); 
	
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
	//private ArrayList<Livro> livros = new ArrayList<Livro>();
	private TreeMap <String,Livro> livros = new TreeMap < String,Livro > ();
	
	//private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private TreeMap <String,Usuario> usuarios = new TreeMap <String,Usuario> ();
	
	//AUTOR
	public void adicionarAutor (Autor a)
	{
		//autores.add(a);
		autores.put(a.getNome(), a);
	}
	
	public void removerAutor (Autor a)
	{
		autores.remove(a.getNome());
	}
	
	public Autor localizarAutor(String nome){
		
		return autores.get(nome);
	}
	
	public ArrayList<Autor> getAutores(){

		return new ArrayList<Autor> (autores.values() ); 
	}
	
	//EMPRESTIMO
	public void adicionarEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public void removerEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public Emprestimo localizarEmprestimo(int id){
		for(Emprestimo e : emprestimos){
			if(e.getId() == id)
				return e;
		}
		return null;
	}
	
	public ArrayList<Emprestimo> getEmprestimos(){
		return emprestimos;
	}
	
	//LIVRO
	public void adicionarLivro (Livro l){
		
		livros.put(l.getTitulo(), l);
	}
	
	public void removerLivro(Livro l){
		
		livros.remove(l.getTitulo());
	}
	
	public Livro localizarLivro(String titulo){
		
		return livros.get(titulo);
	}
		
	public ArrayList<Livro> getLivros(){
		
		return new ArrayList<Livro> (livros.values() ); 
	}
	
	//USUARIO
	public void adicionarUsuario(Usuario u){
		
		usuarios.put(u.getNome(), u);
	}
	
	public void removerUsuario(Usuario u){
		usuarios.remove(u.getNome());
	}
	
	public Usuario localizarUsuario(String nome){
		
		return usuarios.get(nome);
		
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return new ArrayList<Usuario> (usuarios.values() ); 
	}
}