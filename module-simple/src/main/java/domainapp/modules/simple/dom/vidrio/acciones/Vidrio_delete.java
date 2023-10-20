package domainapp.modules.simple.dom.vidrio.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import lombok.RequiredArgsConstructor;

@Action(
        domainEvent = Vidrio_delete.ActionEvent.class,
        semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(
        associateWith = "name", position = ActionLayout.Position.PANEL,
        describedAs = "Eliminaras este Vidrio de la base de datos, y las ordenes de trabajo que sean de este Vidrio.")
@RequiredArgsConstructor
public class Vidrio_delete {
	 public static class ActionEvent extends ActionDomainEvent<Vidrio_delete>{}

	    private final Vidrio vidrio;

	    public void act() {

            // Buscar órdenes de trabajo dentro del vidrio
            List<OrdenDeTrabajo> ordenesDeTrabajo = ordenesRepository.findByVidrioOrderByFechaDesc(vidrio);
            for (OrdenDeTrabajo ordenDeTrabajo : ordenesDeTrabajo) {
                // Marcar la orden de trabajo como inactiva
                ordenDeTrabajo.setActivo(false);
                repositoryService.persist(ordenDeTrabajo);
            }
	    	
	    	 // Cambia el estado "activo" del vidrio a false en lugar de eliminarlo
	    	vidrio.setActivo(false);
	        repositoryService.persist(vidrio);
	    }

	    @Inject OrdenDeTrabajoRepository ordenesRepository;
	    @Inject RepositoryService repositoryService;
	}
