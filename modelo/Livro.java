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
	
	public void adicionarEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public void removerAutor (Autor a)
	{
		autores.remove(a);
	}
	
	public void removerEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public Autor localizarAutor(String nome){
		for(Autor a : autores){
			if(a.getNome().equals(nome))
				return a;
		}
		return null;
	}
	
	public Emprestimo localizarEmprestimo(int id){
		for(Emprestimo e : emprestimos){
			if(e.getId() == id)
				return e;
		}
		return null;
	}
	
	
	public String toString()
	{
		String text;
		text = "Titulo= " + titulo + "quantidade" + quantidade;
		text += "\nAutores = ";
		for (int i = 0; i < autores.size(); i++)
		{
			text += autores.get(i).getNome() + " ";
		}
		
		text = text + "\nEmprestimos = ";
		
		for (int i = 0; i < emprestimos.size(); i++)
		{
			text += emprestimos.get(i).getId() + " ";
		}
		return text;
	}
	

}
