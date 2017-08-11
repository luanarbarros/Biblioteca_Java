package fachada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import modelo.Autor;
import modelo.Livro;
import modelo.Emprestimo;
import modelo.Usuario;

public class Fachada {

		// ATRIBUTOS DA CLASSE
		private static Repositorio repositorio = new Repositorio();
		private static Usuario logado = null; 
		private static int idemprestimo=0; 
		
		//MÉTODOS PARA FAZER LOGIN E LOGOFF
		
		public static Usuario login (String nome, String senha) throws Exception
		{
			Usuario u = repositorio.localizarUsuario(nome);
			if (u == null)
			{
				throw new Exception ("Erro: Usuário não cadastrado");
			}
			else if (!u.getSenha().equals(senha))
			{
				throw new Exception ("Senha incorreta");
			}
			else if (logado != null)
			{
				throw new Exception ("Erro: Já existe um usuário logado");
			}
			else
			{
				logado = u;
			}
			return logado;
		}
		
		public static void logoff () throws Exception
		{
			if (logado == null)
			{
				throw new Exception ("Não há usuário logado");
			}
			else
			{
				logado = null;
			}
		}
		
		public static Usuario getLogado ()
		{
			return logado;
		}
		
		
		//MÉTODOS DE CADASTRO  DE LIVROS E USUÁRIOS
		
		public static Livro cadastrarLivros(String titulo, ArrayList<String>nomes, int quantidade) throws Exception{
			Livro l = repositorio.localizarLivro(titulo);
			if (l!=null)
				throw new Exception ("Livro ja cadastrado: " + titulo + "\n");
			
			//Impede autores duplicados
			for(int i=0; i < nomes.size(); i++){
				for(int j=0; j < nomes.size(); j++){
					if(i!=j){
						if(nomes.get(i).equals(nomes.get(j))){
							throw new Exception ("Erro: Autores duplicados:" + nomes.get(i)  + "\n");
						}
					}
				}
			}
			
			l = new Livro(titulo, quantidade);
			
			for (String n: nomes){
				Autor a = new Autor(n);
				l.adicionarAutor(a);
				a.adicionarLivro(l);
				if(repositorio.localizarAutor(n) == null)
					repositorio.adicionarAutor(a);	
				else{
					repositorio.localizarAutor(n).adicionarLivro(l);
				}
			}
			repositorio.adicionarLivro(l);
			return l;
		}
		
		
		public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
			Usuario u = repositorio.localizarUsuario(nome);
			if(u!=null){
				throw new Exception ("Erro: Usuario ja cadastrado");
			}
			else
			{
				u=new Usuario(nome, senha, 10);
				repositorio.adicionarUsuario(u);
				return u;
			}
		}
		
		// MÉTODOS RELACIONADOS AO EMPRÉSTIMO 
		
		public static Emprestimo criarEmprestimo (String titulo) throws Exception
		{
			Livro l = repositorio.localizarLivro(titulo);
						
			if (l == null)
				throw new Exception ("Emprestimo não efetuado: Livro não encontrado!");
			else if( (logado.localizarEmprestimoPorLivro(titulo)!=null) && (logado.localizarEmprestimoPorLivro(titulo).getDatadev().equals("")) )
				throw new Exception ("Voce ja possui uma unidade emprestada deste livro");				
			else if (l.getQuantidade() < 1)
				throw new Exception ("Emprestimo não efetuado: Não há exemplares desponíveis para empréstimo!");
			else
			{
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate DataEmprestimo = LocalDate.now();
				String StringDataEmprestimo = DataEmprestimo.format (formatador);			
				String StringVencimento = "";
				
				
				Emprestimo emprestimo = new Emprestimo (++idemprestimo,StringDataEmprestimo,StringVencimento,0);
				
				emprestimo.setUsuario(logado);
				logado.adicionarEmprestimo(emprestimo);
				
				l.setQuantidade(l.getQuantidade()-1);
				emprestimo.setLivro(l);
				l.adicionarEmprestimo(emprestimo);
				
				repositorio.adicionarEmprestimo(emprestimo);
				return emprestimo;
				
			}
						
			
		}
		
		public static Emprestimo criarDevolucao(int id) throws Exception
		{
			long diasDif;
			Emprestimo emprestimo = repositorio.localizarEmprestimo(id);
			
			if (emprestimo == null)
				throw new Exception ("Empréstrimo não encontrado!");
			else if  ( !( emprestimo.getUsuario().getNome().equals(logado.getNome())) )
			{
				throw new Exception ("Esse emprestimo pertence a outro usuário!\n");
			}
			else
			{
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate datadev = LocalDate.now();
				//datadev=datadev.plusDays(12); // ALTERANDO O DIA DE HOJE - A MULTA DEVE DÁ 2 REAIS
				
				String StringDataEmprestimo = datadev.format (formatador);	
				LocalDate dataemp = LocalDate.parse(emprestimo.getDataemp(),formatador); 
				emprestimo.setDatadev(StringDataEmprestimo);
				diasDif=ChronoUnit.DAYS.between(dataemp.plusDays(emprestimo.getUsuario().getPrazo()),datadev);
				
				if (diasDif > 0)
					emprestimo.setMulta(diasDif);
				else
					emprestimo.setMulta (0);
				
				return emprestimo;
			}			
		}
		
		
		
		// MÉTODOS DE LISTAGEM 
		
		public static ArrayList<Livro> listarLivros(){
			return repositorio.getLivros();
		}
		
		public static ArrayList<Livro> listarLivrosPorFragmentoDoTitulo (String fragmento)
		{
			ArrayList<Livro> livros = repositorio.getLivros();
			ArrayList<Livro> livrosComFragmento = new ArrayList<Livro> ();
			for (Livro l: livros)
			{
				if (l.getTitulo().contains(fragmento))
					livrosComFragmento.add(l);
			}
			if (livrosComFragmento.isEmpty())
				return null;
			else
				return livrosComFragmento;
		}
		
		public static ArrayList<Autor> listarAutores(){
			return repositorio.getAutores();
		}
		
		public static ArrayList<Livro> listarLivrosPorFragmentoDoAutor (String fragmento)
		{
			ArrayList<Autor> autores = repositorio.getAutores();
			ArrayList<Livro> livrosComFragmento = new ArrayList<Livro> ();
			for (Autor a: autores){
				if(a.getNome().contains(fragmento)){
					for(Livro l: a.getLivros())
						livrosComFragmento.add(l);
				}
			}
			if (livrosComFragmento.isEmpty())
				return null;
			else
				return livrosComFragmento;
		}
		
		
		public static ArrayList<Emprestimo> listarEmprestimos(){
			return repositorio.getEmprestimos();
		}
		
		public static ArrayList<Usuario> listarUsuarios(){
			return repositorio.getUsuarios();
		}
}
