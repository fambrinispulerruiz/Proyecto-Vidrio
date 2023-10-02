package domainapp.modules.simple.dom.modelo.acciones;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.modelo.Modelo;
import lombok.RequiredArgsConstructor;

@Action(
        domainEvent = Modelo_delete.ActionEvent.class,
        semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(
        associateWith = "name", position = ActionLayout.Position.PANEL,
        describedAs = "Eliminaras este Modelo de la base de datos, y todo lo que este contenga.")
@RequiredArgsConstructor
public class Modelo_delete {
	 public static class ActionEvent extends ActionDomainEvent<Modelo_delete>{}

	    private final Modelo modelo;

	    public void act() {
	        repositoryService.remove(modelo);
	        return;
	    }

	    @Inject RepositoryService repositoryService;
	}
