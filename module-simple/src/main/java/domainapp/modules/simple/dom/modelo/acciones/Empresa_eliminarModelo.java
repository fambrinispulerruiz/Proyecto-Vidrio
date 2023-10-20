package domainapp.modules.simple.dom.modelo.acciones;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
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
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "modelos", sequence = "2")
@RequiredArgsConstructor
public class Empresa_eliminarModelo {

    private final Empresa empresa;

    public Empresa act(final String nombre) {
        Modelo modelo = modeloRepository.findByNombre(nombre);
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
        return empresa;
    }
    public String disableAct() {
        return modeloRepository.findByEmpresa(empresa).isEmpty() ? "No hay modelos." : null;
    }
    public List<String> choices0Act() {
        return modeloRepository.findByEmpresa(empresa)
                .stream()
                .map(Modelo::getNombre)
                .collect(Collectors.toList());
    }
    public String default0Act() {
        List<String> names = choices0Act();
        return names.size() == 1 ? names.get(0) : null;
    }

    @Inject ModeloRepository modeloRepository;
    @Inject VidrioRepository vidrioRepository;
    @Inject OrdenDeTrabajoRepository ordenesRepository;
    @Inject RepositoryService repositoryService;
}