package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.empresa.enumeradores.TipoEmpresa;
import lombok.Getter;
import lombok.Setter;

public class RepoEmpresa {

	@Getter @Setter
	private String nombre;
	
	@Getter @Setter
	private String domicilio;
	
	@Getter @Setter
	private String telefono;
	
	@Getter @Setter
	private String correo;
	
	@Getter @Setter
	private TipoEmpresa tipoEmpresa;
	
	public RepoEmpresa(String nombre, String domicilio, String telefono, String correo, TipoEmpresa tipoEmpresa) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.correo = correo;
		this.tipoEmpresa = tipoEmpresa;
	}

	public RepoEmpresa() {
		
	}
}
