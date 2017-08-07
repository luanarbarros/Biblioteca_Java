package aplicacao;


import java.util.ArrayList;

import fachada.Fachada;
import modelo.Autor;
import modelo.Usuario;
import modelo.Livro;
import modelo.Emprestimo;

public class Teste {
	
	public Teste(){
		cadastrarLivro();
		listar();
		cadastrarUsuario();
	}
	
	public void cadastrarLivro(){
		try{
			Livro l;
			ArrayList<String> autores1 = new ArrayList<String>();
			autores1.add("Oppenhein, A.V.");
			autores1.add("Willsky, A.S.");
			l = Fachada.cadastrarLivros("Sinais e Sistemas", autores1, 2);
			
			ArrayList<String> autores2 = new ArrayList<String>();
			autores2.add("Oppenhein, A.V.");
			autores2.add("Schafer, R. W.");
			l = Fachada.cadastrarLivros("Processamento de Sinais em Tempo Discreto", autores2, 2);
			
			ArrayList<String> autores3 = new ArrayList<String>();
			autores3.add("Prestes, R.");
			autores3.add("Wolf, O.");
			l = Fachada.cadastrarLivros("Robotica Movel", autores3, 2);
			
			ArrayList<String> autores4 = new ArrayList<String>();
			autores4.add("Sedra, A.S.");
			autores4.add("Smith, K.C.");
			l = Fachada.cadastrarLivros("Microeletronica", autores4, 2);
			
			ArrayList<String> autores5 = new ArrayList<String>();
			autores5.add("Coelho, A.A.R.");
			autores5.add("Coelho, L.S.");
			l = Fachada.cadastrarLivros("Identificacao de Sistemas Dinamicos Lineares", autores5, 2);
			
			ArrayList<String> autores6 = new ArrayList<String>();
			autores6.add("Luana");
			autores6.add("Luana");
			l = Fachada.cadastrarLivros("The best person in the world: me", autores6, 2);
			
			
				
		}catch (Exception e){
			
			System.out.println(e.getMessage());
		}
	}
	
	public void listar(){
		String texto;
		ArrayList<Livro> listaLivros = Fachada.listarLivros();
		texto = "Lista de Livros: \n";
		if(listaLivros.isEmpty()){
			texto+= "Nao existem livros cadastrados \n";
		}
		else{
			for(Livro l: listaLivros)
				texto+= l.toString() + "\n";
		}
		System.out.println(texto);
		System.out.println("****************************");
		
		ArrayList<Autor> listaAutores = Fachada.listarAutores();
		texto = "Lista de Autores \n";
		if(listaAutores.isEmpty()){
			texto+= "Nao existem autores cadastrados \n";
		}
		else{
			for(Autor a: listaAutores)
				texto+= a.toString() + "\n";
		}
		System.out.println(texto);	
				
	}
	
	public void cadastrarUsuario(){
		
		Usuario usuario = new Usuario("Luana", "lu1234");
		
	}
	
//***********************MAIN***********************
	public static void main(String[] args) 
	{	

		new Teste();
	}

}
