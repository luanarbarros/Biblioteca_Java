package fachada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import modelo.Administrador;
import modelo.Aluno;
import modelo.Autor;
import modelo.Livro;
import modelo.Emprestimo;
import modelo.Usuario;
import modelo.Funcionario;

public class Fachada {

		// ATRIBUTOS DA CLASSE
		private static Repositorio repositorio = new Repositorio();
		private static Usuario logado = null; 
		private static int idemprestimo=0; 
		
		//M�TODOS PARA FAZER LOGIN E LOGOFF
		
		public static Usuario login (String nome, String senha) throws Exception
		{
			Usuario u = repositorio.localizarUsuario(nome);
			if (u == null)
			{
				throw new Exception ("Erro: Usu�rio n�o cadastrado");
			}
			else if (!u.getSenha().equals(senha))
			{
				throw new Exception ("Senha incorreta");
			}
			else if (logado != null)
			{
				throw new Exception ("Erro: J� existe um usu�rio logado");
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
				throw new Exception ("N�o h� usu�rio logado");
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
		
		
		//M�TODOS DE CADASTRO  DE LIVROS E USU�RIOS
		
		public static Livro cadastrarLivros(String titulo, ArrayList<String>nomes, int quantidade) throws Exception{
			titulo = titulo.toUpperCase();
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
		
		//M�todo para cadastrar o usu�rio
		public static Usuario cadastrarUsuario(String nome, String senha, String tipo, String departamentoCurso) throws Exception{
			Usuario u = repositorio.localizarUsuario(nome);
			if(u!=null){
				throw new Exception ("Erro: Usuario ja cadastrado");
			}
			else
			{
				tipo = tipo.toUpperCase();
				if (tipo.equals("FUNCIONARIO"))
				{
					u = new Funcionario (nome, senha, departamentoCurso);
					repositorio.adicionarUsuario(u);
					return u;
				}
				else if (tipo.equals("ALUNO"))
				{
					u = new Aluno (nome, senha, departamentoCurso);
					repositorio.adicionarUsuario(u);
					return u;
				}
				else if (tipo.equals("ADMINISTRADOR"))
				{
					u = new Administrador (nome, senha, departamentoCurso);
					repositorio.adicionarUsuario(u);
					return u;
				}
				else
				{
					throw new Exception ("Tipo de usu�rio desconhecido");
				}
			}
		}
		
		//M�todo para excluir o usu�rio
		public static Usuario excluirUsuario (String nome) throws Exception
		{
			Usuario u  = repositorio.localizarUsuario(nome);
				
			if (!(logado instanceof Administrador))
			{
				throw new Exception ("Voc� n�o possui permi��o para realizar essa opera��o!");
			}
			else if (u == null)
			{
				throw new Exception ("ERRO! Usu�rio n�o encontrado!");
			}
			else 
			{
				ArrayList<Emprestimo> emprestimosDeU = u.getEmprestimos();
				for (Emprestimo emprestimo: emprestimosDeU)
				{
					if (emprestimo.getDatadev().equals(""))
						throw new Exception ("Erro! O usu�rio ainda possui empr�stimos pendentes");
				}
				
				//remover o usu�rio
				//remover todos seus empr�stimos
				
				// remover rela��o do empr�stimo com o livro 
				for ( Emprestimo emprestimo: emprestimosDeU)
				{
					//emprestimo.getLivro().removerEmprestimo(emprestimo); // remove os empr�stimos do objeto livro remove o livro do objeto empr�stimo
					for (Livro l: repositorio.getLivros() )
					{
						if (l.equals(emprestimo.getLivro()))
						{
							l.removerEmprestimo(emprestimo);
						}
					}
							
					if  (repositorio.getLivros().indexOf(emprestimo.getLivro()) != -1)
						throw new Exception ("Erro ao desvinclular o emprestimo do livro");
					
					emprestimo.setLivro(null);
					emprestimo.setUsuario(null); // remover rela��o do empr�stimo com o usu�rio
					//emprestimo = null; 
					repositorio.removerEmprestimo(emprestimo);
					
				}
				
				repositorio.removerUsuario(u);	
				
				return u;
			}
		}
		
		// M�TODOS RELACIONADOS AO EMPR�STIMO 
		
		public static Emprestimo criarEmprestimo (String titulo) throws Exception
		{
			titulo = titulo.toUpperCase();
			Livro l = repositorio.localizarLivro(titulo);
						
			if (l == null)
				throw new Exception ("Emprestimo n�o efetuado: Livro n�o encontrado!");
			else if( (logado.localizarEmprestimoPorLivro(titulo)!=null) && (logado.localizarEmprestimoPorLivro(titulo).getDatadev().equals("")) )
				throw new Exception ("Voce ja possui uma unidade emprestada deste livro");				
			else if (l.getQuantidade() < 1)
				throw new Exception ("Emprestimo n�o efetuado: N�o h� exemplares despon�veis para empr�stimo!");
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
				throw new Exception ("Empr�strimo n�o encontrado!");
			else if  ( !( emprestimo.getUsuario().getNome().equals(logado.getNome())) )
			{
				throw new Exception ("Esse emprestimo pertence a outro usu�rio!\n");
			}
			else
			{
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate datadev = LocalDate.now();
				//datadev=datadev.plusDays(12); // ALTERANDO O DIA DE HOJE - A MULTA DEVE D� 2 REAIS
				
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
				
		// M�TODOS DE LISTAGEM 
		
		public static ArrayList<Livro> listarLivros(){
			return repositorio.getLivros();
		}
		
		public static ArrayList<Livro> listarLivrosPorFragmentoDoTitulo (String fragmento)
		{
			fragmento = fragmento.toUpperCase();
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
			fragmento = fragmento.toUpperCase();
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
