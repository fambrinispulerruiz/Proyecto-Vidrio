package domainapp.webapp.application.services.homepage;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.acciones.Vidrio_agregarOrden;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Aseguradora;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Propio;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.TraeOrden;

import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.wrapper.WrapperFactory;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import lombok.RequiredArgsConstructor;

@Action
@RequiredArgsConstructor
public class HomePageViewModel_orden {

    final HomePageViewModel homePageViewModel;

    public Object act(
    		Modelo modelo,
    		Vidrio vidrio,
    		LocalDateTime fecha,
            String nombreAsegurado,
            String telefonoAsegurado,
            Aseguradora aseguradora,
            int nroSiniestro,
            TraeOrden orden,
            String patente,
            Propio propio,
            String observaciones,
            Estado estado, boolean mostrarOrden) {
        OrdenDeTrabajo ordentrabajo = wrapperFactory.wrapMixin(Vidrio_agregarOrden.class, vidrio).act(fecha, nombreAsegurado, telefonoAsegurado, aseguradora, nroSiniestro, orden, patente, propio, observaciones, estado);
        return mostrarOrden ? ordentrabajo : homePageViewModel;
    }

    public List<Modelo> autoComplete0Act(final String nombre) {
    	 return modeloRepository.findByNombreContaining(nombre);
    }
    public List<Vidrio> choices1Act(Modelo modelo) {
        if(modelo == null) return Collections.emptyList();
        return vidrioRepository.findByModelo(modelo);
    }
    public LocalDateTime default1Act(Modelo modelo, Vidrio vidrio) {
        if(vidrio == null) return null;
        return factoryService.mixin(Vidrio_agregarOrden.class, vidrio).default0Act();
    }
    public String validate2Act(Modelo modelo, Vidrio vidrio, LocalDateTime fecha){
         return factoryService.mixin(Vidrio_agregarOrden.class, vidrio).validate0Act(fecha);
    }

    @Inject ModeloRepository modeloRepository;
    @Inject VidrioRepository vidrioRepository;
    @Inject WrapperFactory wrapperFactory;
    @Inject FactoryService factoryService;
}
