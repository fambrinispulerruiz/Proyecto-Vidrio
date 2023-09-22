package domainapp.modules.simple.dom.vidrio.acciones;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import lombok.RequiredArgsConstructor;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "vidrios", sequence = "2")
@RequiredArgsConstructor
public class Empresa_eliminarVidrio {

    private final Empresa empresa;

    public Empresa act(final String nombre) {
        vidrioRepository.findByEmpresaAndNombre(empresa, nombre)
                .ifPresent(vidrio -> repositoryService.remove(vidrio));
        return empresa;
    }
    public String disableAct() {
        return vidrioRepository.findByEmpresa(empresa).isEmpty() ? "No hay vidrios." : null;
    }
    public List<String> choices0Act() {
        return vidrioRepository.findByEmpresa(empresa)
                .stream()
                .map(Vidrio::getNombre)
                .collect(Collectors.toList());
    }
    public String default0Act() {
        List<String> names = choices0Act();
        return names.size() == 1 ? names.get(0) : null;
    }

    @Inject VidrioRepository vidrioRepository;
    @Inject RepositoryService repositoryService;
}