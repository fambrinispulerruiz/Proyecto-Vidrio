package domainapp.modules.simple.dom.empresa.acciones;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.empresa.Empresa;
import lombok.RequiredArgsConstructor;


@Action(
        domainEvent = Empresa_delete.ActionEvent.class,
        semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(
        associateWith = "name", position = ActionLayout.Position.PANEL,
        describedAs = "Eliminaras esta Empresa de la base de datos, y todo lo que esta contenga.")
@RequiredArgsConstructor
public class Empresa_delete {
	 public static class ActionEvent extends ActionDomainEvent<Empresa_delete>{}

	    private final Empresa empresa;

	    public void act() {
	        repositoryService.remove(empresa);
	        return;
	    }

	    @Inject RepositoryService repositoryService;
	}
