package domainapp.modules.simple.dom.empresa.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import lombok.RequiredArgsConstructor;


@Action(
        domainEvent = Empresa_delete.ActionEvent.class,
        semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(
        associateWith = "name", position = ActionLayout.Position.PANEL,
        describedAs = "Desactivaras esta Empresa de la base de datos, y todo lo que esta contenga.")
@RequiredArgsConstructor
public class Empresa_delete {
	 public static class ActionEvent extends ActionDomainEvent<Empresa_delete>{}

	    private final Empresa empresa;

	    public void act() {
	    	
	        List<Modelo> modelos = modeloRepository.findByEmpresa(empresa);
	        for (Modelo modelo : modelos) {
	            // Marcar el modelo como inactivo
	            modelo.setActivo(false);
	            repositoryService.persist(modelo);

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
	        }
	    	 // Cambia el estado "activo" de la empresa a false en lugar de eliminarla
	        empresa.setActivo(false);
	        repositoryService.persist(empresa);
	        return;
	    }

	    @Inject ModeloRepository modeloRepository;
	    @Inject VidrioRepository vidrioRepository;
	    @Inject OrdenDeTrabajoRepository ordenesRepository;
	    @Inject RepositoryService repositoryService;
	}
