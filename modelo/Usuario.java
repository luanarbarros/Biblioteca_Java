package modelo;

import java.util.ArrayList;

public abstract class Usuario {
	
	private String nome;
	private String senha;
	private int prazo;
	
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo> ();
	
	public Usuario (String n, String s, int p)
	{
		nome = n;
		senha = s;
		prazo = p;
	}
	
	public Usuario (String n, String s)
	{
		nome = n;
		senha = s;
	}
	
	public final String getNome()
	{
		return nome;
	}
	
	public final String getSenha()
	{
		return senha;
	}
	
	public final int getPrazo()
	{
		return prazo;
	}
	
	public final ArrayList<Emprestimo> getEmprestimos(){
		return emprestimos;
	}
	
	public final void setNome  (String n)
	{
		nome = n;
	}
	public final void setSenha (String s)
	{
		senha = s;
	}
	public final void setPrazo (int p)
	{
		prazo = p;
	}
	
	public final void adicionarEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public final void removerEmprestimo (Emprestimo e)
	{
		emprestimos.remove(e);
	}
	
	public final Emprestimo localizarEmprestimo(int id)
	{
		for(Emprestimo e : emprestimos){
			if(e.getId() == id)
				return e;
		}
		return null;
	}
	
	public final Emprestimo localizarEmprestimoPorLivro(String tituloDoLivro)
	{
		for(Emprestimo e : emprestimos){
			if(e.getLivro().getTitulo().equals(tituloDoLivro))
				return e;
		}
		return null;
	}
	
	
	public String toString()
	{
		String text;
		text = "nome= " + nome + " senha= " + senha + " prazo= " + prazo + " ";
		text = text + "\nIDs dos Empestimos = ";
		if (emprestimos.isEmpty())
		{
			text = text + "Não há emprestimos!";
			return (text);
		}
		else
		{
			for (int i = 0; i < emprestimos.size(); i++)
			{
				text = text + emprestimos.get(i).getId() + ", ";
			}
			return text;
		}
	}
	
	
}
