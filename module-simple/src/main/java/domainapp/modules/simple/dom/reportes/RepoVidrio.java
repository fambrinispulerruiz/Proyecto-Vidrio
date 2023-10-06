package domainapp.modules.simple.dom.reportes;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import lombok.Getter;
import lombok.Setter;

public class RepoVidrio {
	
	@Getter @Setter
	private String nombre;
	
	@Getter @Setter
	private String codigo;
	
	@Getter @Setter
	private Modelo modelo;
	
	@Getter @Setter
	private double precio;
	
	@Getter @Setter
	private TipoVidrio tipoVidrio;
	
	@Getter @Setter
	private Antena antena;
	
	@Getter @Setter
	private SensorLluvia sensor;

	public RepoVidrio(String nombre, String codigo, Modelo modelo, double precio, TipoVidrio tipoVidrio, Antena antena,
			SensorLluvia sensor) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.modelo = modelo;
		this.precio = precio;
		this.tipoVidrio = tipoVidrio;
		this.antena = antena;
		this.sensor = sensor;
	}
	
	public RepoVidrio() {
		
	}
}
