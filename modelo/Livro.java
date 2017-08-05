package modelo;

import java.util.ArrayList;

public class Livro {
	
	private String titulo;
	private int quantidade;
	private ArrayList<Autor> autores = new ArrayList<Autor> ();
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	
	
	public Livro(String t, int q)
	{
		titulo = t;
		quantidade = q;
	}
	
	public String getTitulo ()
	{
		return titulo;
	}
	
	public void setTitulo (String t)
	{
		titulo = t;
	}
	
	public int getQuantidade()
	{
		return quantidade;
	}
	
	public void setQuantidade (int q)
	{
		quantidade = q;
	}
	
	public ArrayList<Autor> getTodosAutores ()
	{
		return autores;
	}
	
	public ArrayList<Emprestimo> getTodosEmprestimos ()
	{
		return emprestimos;
	}
	
	public void adicionarAutor (Autor a)
	{
		autores.add(a);
	}
	
	public void adicionaEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public void removerAutor (Autor a)
	{
		autores.remove(a);
	}
	
	public void removerEmprestimo (E)
	
	
	public String toString()
	{
		String text;
		text = "Titulo= " + titulo + "quantidade" + quantidade;
		return text;
	}
	

}
