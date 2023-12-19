package domainapp.modules.simple.dom.ordenes_trabajo.acciones;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Aseguradora;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Propio;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.TraeOrden;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import lombok.RequiredArgsConstructor;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "ordenes", sequence = "1")
@RequiredArgsConstructor
public class Vidrio_agregarOrden {

    private final Vidrio vidrio;

    public OrdenDeTrabajo act(
    		String fecha,
            final String nombreAsegurado,
            final String telefonoAsegurado,
            final Aseguradora aseguradora,
            final int nroSiniestro,
            final TraeOrden orden,
            final String patente,
            final Propio propio,
            final String observaciones,
            final Estado estado
            ) {
        return repositoryService.persist(new OrdenDeTrabajo(vidrio, fecha, nombreAsegurado, telefonoAsegurado, aseguradora, nroSiniestro, orden, patente, propio, observaciones, estado));
    }
    public String validate0Act(LocalDateTime fecha) {
        return clockService.getClock().nowAsLocalDateTime().isBefore(fecha)
                ? null
                : "Debe ser en el futuro.";
    }
    public LocalDateTime default0Act() {
        return clockService.getClock().nowAsLocalDateTime()
                .toLocalDate()
                .plusDays(1)
                .atTime(LocalTime.of(9, 0));
    }
    
    public Aseguradora default1Act() {
        return Aseguradora.NO_POSEE;
    }
    
    public TraeOrden default2Act() {
        return TraeOrden.Si;
    }
    
    public Propio default3Act() {
        return Propio.Propio;
    }
    
    public Estado default4Act() {
        return Estado.Sin_Atender;
    }

    @Inject ClockService clockService;
    @Inject RepositoryService repositoryService;
}
