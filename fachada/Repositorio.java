package fachada;

import java.util.ArrayList;
import modelo.Autor;
import modelo.Emprestimo;
import modelo.Livro;
import modelo.Usuario;

public class Repositorio {
	private ArrayList<Autor> autores = new ArrayList<Autor>();
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	private ArrayList<Livro> livros = new ArrayList<Livro>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	//AUTOR
	public void adicionarAutor (Autor a)
	{
		autores.add(a);
	}
	
	public void removerAutor (Autor a)
	{
		autores.remove(a);
	}
	
	public Autor localizarAutor(String nome){
		for(Autor a : autores){
			if(a.getNome().equals(nome))
				return a;
		}
		return null;
	}
	public ArrayList<Autor> getAutores(){
		return autores;
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
		livros.add(l);
	}
	
	public void removerLivro(Livro l){
		livros.remove(l);
	}
	
	public Livro localizarLivro(String titulo){
		for(Livro l : livros){
			if(l.getTitulo() == titulo)
				return l;
		}
		return null;
	}
	
	public ArrayList<Livro> getLivros(){
		return livros;
	}
	
	//USUARIO
	public void adicionarUsuario(Usuario u){
		usuarios.add(u);
	}
	
	public void removerUsuario(Usuario u){
		usuarios.remove(u);
	}
	
	public Usuario localizarUsuario(String nome){
		for(Usuario u: usuarios){
			if(u.getNome() == nome)
				return u;
		}
		return null;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
}
