package domainapp.modules.simple.dom.modelo.acciones;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import lombok.RequiredArgsConstructor;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "modelos", sequence = "1")
@RequiredArgsConstructor
public class Empresa_agregarModelo {

    private final Empresa empresa;

    public Empresa act(
            final String nombre
            ) {
          
        repositoryService.persist(new Modelo(nombre, empresa));
        return empresa;
    }
    public String validate0Act(final String nombre) {
        return modeloRepository.findByEmpresaAndNombre(empresa, nombre).isPresent()
                ? String.format("El Modelo con el nombre '%s' ya existe para esta empresa.", nombre)
                : null;
    }

    @Inject ModeloRepository modeloRepository;
    @Inject RepositoryService repositoryService;
}
