package domainapp.modules.simple.dom.vidrio.acciones;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.modelo.Modelo;
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
public class Modelo_agregarVidrio {

    private final Modelo modelo;

    public Modelo act(
            final String nombre,
            final String codigo,
            final double precio,
            final TipoVidrio tipoVidrio,
            final Antena antena,
            final SensorLluvia sensor
            ) {
          
        repositoryService.persist(new Vidrio(nombre, codigo, modelo, precio, tipoVidrio, antena, sensor));
        return modelo;
    }
    public String validate0Act(final String nombre) {
        return vidrioRepository.findByModeloAndNombre(modelo, nombre).isPresent()
                ? String.format("El Vidrio con el nombre '%s' ya existe registrado para este Modelo.", nombre)
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
