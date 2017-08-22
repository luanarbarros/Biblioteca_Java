package aplicacao;

import modelo.Administrador;
import modelo.Funcionario;
import modelo.Usuario;

public class TestesBasicos {

	public static void main(String[] args)
	{
		
		
		Usuario u_funcionario;
		Usuario u_administrador;
		
		u_funcionario = new Funcionario ("Neves", "NEVES123", "Recep��o");	
		u_administrador = new Administrador ("Admin", "Admin", "Diretoria");
		
		if (u_funcionario instanceof Funcionario)
			System.out.println("u_funcionario � instancia de Funcionario");
		if (u_funcionario instanceof Administrador)
			System.out.println("u_funcionario � instancia de Administrador");
		if (u_administrador instanceof Funcionario)
			System.out.println("u_administrador � instancia de Funcionario");
		if (u_administrador instanceof Administrador)
			System.out.println("u_administrador � instancia de Administrador");

	}

}
