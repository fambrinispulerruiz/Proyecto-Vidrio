package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.empresa.Empresa;
import lombok.Getter;
import lombok.Setter;

public class RepoModelo {
	
	@Getter @Setter
	private String nombre;
	
	@Getter @Setter
	private Empresa empresa;

	public RepoModelo(String nombre, Empresa empresa) {
		this.nombre = nombre;
		this.empresa = empresa;
	}
	
	public RepoModelo() {
		
	}
}
