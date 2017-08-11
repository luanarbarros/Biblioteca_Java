package modelo;

import java.util.ArrayList;

public class Autor {
	private String nome;
	private ArrayList<Livro> livros = new ArrayList<Livro>();


	public Autor (String nome){
		nome = nome.toUpperCase();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		nome = nome.toUpperCase();
		this.nome = nome;
	}
	
	public int getTotalLivros(){
		return livros.size();
	}
	
	public ArrayList<Livro> getLivros(){
		return livros;
	}
	
	public void adicionarLivro(Livro l){
		livros.add(l);
	}
	
	public void removerLivro(Livro l){
		livros.remove(l);
		l.removerAutor(null);
	}
	
	public Livro localizarLivro(String titulo){
		for(Livro l : livros){
			if(l.getTitulo().equals(titulo))
				return l;
		}
		return null;
	}
	
	public String toString(){

		String texto = nome != null ? "Autor: " + nome : "autor não cadastrado";
		if (nome!=null)
		{
			texto +="\nLivros: ";
			for(Livro l: livros)
				texto+= l.getTitulo() + "\n";
			return texto;
		}
		else
			return texto;
	}
	
}