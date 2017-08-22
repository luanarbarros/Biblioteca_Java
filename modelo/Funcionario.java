package modelo;

public class Funcionario extends Usuario{
	
	private String departamento;
	
	public Funcionario (String nome, String senha, String departamento)
	{
		super (nome,senha,30);
		this.departamento = departamento;
	}
	
	public void setDepartamento (String departamento)
	{
		this.departamento = departamento;
	}
	
	public String getDepartamento ()
	{
		return departamento;
	}
	
	public String toString ()
	{
		String[] fragmentos = super.toString().split("\n");
		return (fragmentos[0] + "Departamento: " + departamento + "\n" + fragmentos[1] + "\n\n");
	}
	
	

}
