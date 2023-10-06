package domainapp.modules.simple.dom.reportes;

import java.time.LocalDateTime;

import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Aseguradora;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Propio;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.TraeOrden;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import lombok.Getter;
import lombok.Setter;

public class RepoOrdenes_Trabajo {
	
	@Getter @Setter
	private Vidrio vidrio;
	
	@Getter @Setter
	private LocalDateTime fecha;
	
	@Getter @Setter
	private String nombreAsegurado;
	
	@Getter @Setter
	private String telefonoAsegurado;
	
	@Getter @Setter
	private Aseguradora aseguradora;
	
	@Getter @Setter
	private int nroSiniestro;
	
	@Getter @Setter
	private String patente;
	
	@Getter @Setter
	private TraeOrden orden;
	
	@Getter @Setter
	private Propio propio;
	
	@Getter @Setter
	private String observaciones;
	
	@Getter @Setter
	private Estado estado;

	public RepoOrdenes_Trabajo(Vidrio vidrio, LocalDateTime fecha, String nombreAsegurado, String telefonoAsegurado,
			Aseguradora aseguradora, int nroSiniestro, String patente, TraeOrden orden, Propio propio,
			String observaciones, Estado estado) {
		this.vidrio = vidrio;
		this.fecha = fecha;
		this.nombreAsegurado = nombreAsegurado;
		this.telefonoAsegurado = telefonoAsegurado;
		this.aseguradora = aseguradora;
		this.nroSiniestro = nroSiniestro;
		this.patente = patente;
		this.orden = orden;
		this.propio = propio;
		this.observaciones = observaciones;
		this.estado = estado;
	}
	
	public RepoOrdenes_Trabajo() {
		
	}
}
