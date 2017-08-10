package modelo;

public class Emprestimo {
	
	private int id;
	private String dataemp;
	private String datadev;
	private double multa;
	
	private Usuario usuario;
	private Livro livro;
	
	public Emprestimo (int id, String dataemp, String datadev, double multa)
	{
		this.id = id;
		this.dataemp = dataemp;
		this.datadev = datadev;
		this.multa = multa;
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}

	public String getDataemp(){
		return dataemp;
	}
	
	public void setDataemp(String dataemp){
		this.dataemp=dataemp;	
	}
	
	public String getDatadev(){
		return datadev;
	}
	
	public void setDatadev(String datadev){
		this.datadev=datadev;	
	}

	public double getMulta(){
		return multa;
	}
	
	public void setMulta(double multa){
		this.multa=multa;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario=usuario;
	}
	
	public Livro getLivro ()
	{
		return livro;
	}
	
	public void setLivro (Livro livro)
	{
		this.livro = livro;
	}
	
	public String toString(){
		return "\nEmprestimo numero ="+ id 
				+ "\nData de Emprestimo = " + dataemp
				+ "\nData de Devolução = " + datadev
				+ "\nUsuario = " + usuario.getNome()
				+ "\nLivro = " + livro.getTitulo()
				+ "\nMulta = " + multa;
	}
}
