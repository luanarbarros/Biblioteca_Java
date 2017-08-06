package modelo;

public class Emprestimo {
	
	private int id;
	private String dataemp;
	private String datadev;
	private double multa;
	
	public Emprestimo (int id, String dataemp, String datadev, double multa)
	{
		this.id = id;
		this.dataemp = dataemp;
		this.datadev = datadev;
		this.multa = multa;
		
	}
	
	public double getId()	{
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}

}
