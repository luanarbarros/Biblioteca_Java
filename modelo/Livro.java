package modelo;

public class Livro {
	
	private String titulo;
	private int quantidade;
	private ArrayList<Autor> autores = new Array
	
	
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
	
	public String toString()
	{
		String text;
		text = "Titulo= " + titulo + "quantidade" + quantidade;
		return text;
	}
	

}
