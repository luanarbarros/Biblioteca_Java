package modelo;

public class Emprestimo {
	
	private int id;
	private String dataemp;
	private String datadev;
	private double multa;
	
	public Emprestimo (int i, String datae, String datad, double m)
	{
		id = i;
		dataemp = datae;
		datadev = datad;
		multa = m;
		
	}
	
	public double getId ()
	{
		return id;
	}

}
