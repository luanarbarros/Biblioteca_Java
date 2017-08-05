package modelo;

import java.util.ArrayList;

public class Autor {
	private String nome;
	private ArrayList<Livro> produtos = new ArrayList<Livro>();


	public Autor (String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		return nome != null ? "autor=" + nome + ", " : "autor não cadastrado";
	}
	
}