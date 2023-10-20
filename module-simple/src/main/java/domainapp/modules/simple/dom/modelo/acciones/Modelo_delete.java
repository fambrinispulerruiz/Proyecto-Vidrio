package domainapp.modules.simple.dom.modelo.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import lombok.RequiredArgsConstructor;

@Action(domainEvent = Modelo_delete.ActionEvent.class, semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
@ActionLayout(associateWith = "name", position = ActionLayout.Position.PANEL, describedAs = "Eliminaras este Modelo de la base de datos, y todo lo que este contenga.")
@RequiredArgsConstructor
public class Modelo_delete {
	public static class ActionEvent extends ActionDomainEvent<Modelo_delete> {
	}

	private final Modelo modelo;

	public void act() {

		// Buscar vidrios dentro del modelo
		List<Vidrio> vidrios = vidrioRepository.findByModelo(modelo);
		for (Vidrio vidrio : vidrios) {
			// Marcar el vidrio como inactivo
			vidrio.setActivo(false);
			repositoryService.persist(vidrio);

			// Buscar órdenes de trabajo dentro del vidrio
			List<OrdenDeTrabajo> ordenesDeTrabajo = ordenesRepository.findByVidrioOrderByFechaDesc(vidrio);
			for (OrdenDeTrabajo ordenDeTrabajo : ordenesDeTrabajo) {
				// Marcar la orden de trabajo como inactiva
				ordenDeTrabajo.setActivo(false);
				repositoryService.persist(ordenDeTrabajo);
			}
		}
		// Cambia el estado "activo" del modelo a false en lugar de eliminarlo
		modelo.setActivo(false);
		repositoryService.persist(modelo);
		return;
	}

	@Inject
	VidrioRepository vidrioRepository;
	@Inject
	OrdenDeTrabajoRepository ordenesRepository;
	@Inject
	RepositoryService repositoryService;
}
