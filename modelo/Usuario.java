package modelo;

import java.util.ArrayList;

public class Usuario {
	
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
	
	public String getNome()
	{
		return nome;
	}
	
	public String getSenha()
	{
		return senha;
	}
	
	public int getPrazo()
	{
		return prazo;
	}
	
	public void setNome  (String n)
	{
		nome = n;
	}
	public void setSenha (String s)
	{
		senha = s;
	}
	public void setPrazo (int p)
	{
		prazo = p;
	}
	
	public void adicionarEmprestimo (Emprestimo e)
	{
		emprestimos.add(e);
	}
	
	public void removerEmprestimo (Emprestimo e)
	{
		emprestimos.remove(e);
	}
	
	public Emprestimo localizarEmprestimo(int id)
	{
		for(Emprestimo e : emprestimos){
			if(e.getId() == id)
				return e;
		}
		return null;
	}
	
	public String toString ()
	{
		String text;
		text = "nome= " + nome + " senha= " + senha + " prazo= " + prazo + " ";
		text = text + "\nEmpestimos = ";
		if (emprestimos == null)
		{
			return text;
		}
		else
		{
			for (int i = 0; i < emprestimos.size(); i++)
			{
				
			}
		}
	}
	
	
}
