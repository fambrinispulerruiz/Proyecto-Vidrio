package domainapp.modules.simple.dom.ordenes_trabajo.tabla;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;

import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import lombok.RequiredArgsConstructor;

@Collection
@CollectionLayout(defaultView = "table")
@RequiredArgsConstructor
public class Vidrio_ordenes {

    private final Vidrio vidrio;

    public List<OrdenDeTrabajo> coll() {
        return ordenRepository.findByVidrioOrderByFechaDesc(vidrio);
    }

    @Inject OrdenDeTrabajoRepository ordenRepository;
}
