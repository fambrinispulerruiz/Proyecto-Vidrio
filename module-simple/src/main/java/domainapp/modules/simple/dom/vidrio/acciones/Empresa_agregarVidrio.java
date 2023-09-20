package domainapp.modules.simple.dom.vidrio.acciones;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import lombok.RequiredArgsConstructor;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "vidrios", sequence = "1")
@RequiredArgsConstructor
public class Empresa_agregarVidrio {

    private final Empresa empresa;

    public Empresa act(
            final String nombre,
            final String codigo,
            final Empresa empresa,
            final String modelo,
            final double precio,
            final TipoVidrio tipoVidrio,
            final Antena antena,
            final SensorLluvia sensor
            ) {
          
        repositoryService.persist(new Vidrio(nombre, codigo, empresa, modelo, precio, tipoVidrio, antena, sensor));
        return empresa;
    }
    public String validate0Act(final String nombre) {
        return vidrioRepository.findByEmpresaAndNombre(empresa, nombre).isPresent()
                ? String.format("El Vidrio con el nombre '%s' ya existe para esta empresa.", nombre)
                : null;
    }
    public TipoVidrio default1Act() {
        return TipoVidrio.Parabrisa;
    }
    
    public Antena default2Act() {
        return Antena.No;
    }
    
    public SensorLluvia default3Act() {
        return SensorLluvia.No;
    }

    @Inject VidrioRepository vidrioRepository;
    @Inject RepositoryService repositoryService;
}
