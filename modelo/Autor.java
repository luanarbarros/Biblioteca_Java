package modelo;

import java.util.ArrayList;

public class Autor {
	private String nome;
	private ArrayList<Livro> livros = new ArrayList<Livro>();


	public Autor (String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTotalLivros(){
		return livros.size();
	}
	
	public void adicionarLivro(Livro l){
		livros.add(l);
		l.setAutor(this);
	}
	
	public String toString(){
		String texto = nome != null ? "autor=" + nome : "autor n�o cadastrado";
		if (nome!=null)
		{
			texto +="\n livros=";
			for(Livro l: livros)
				texto+= " "+ l.getTitulo() +",";
			return texto;
		}
		else
			return texto;
	}
	
}