package modelo;

public class Aluno extends Usuario {
	
	private String curso;
	
	public Aluno (String nome, String senha, String curso )
	{
		super (nome, senha,20);
		this.curso = curso;
	}

	public void setCurso (String curso)
	{
		this.curso = curso;
	}
	
	public String getCurso ()
	{
		return curso;
	}
	
	public String toString ()
	{
		String[] fragmentos = super.toString().split("\n");
		return (fragmentos[0] + "Curso: " + curso + "\n" + fragmentos[1]);
	}
}
