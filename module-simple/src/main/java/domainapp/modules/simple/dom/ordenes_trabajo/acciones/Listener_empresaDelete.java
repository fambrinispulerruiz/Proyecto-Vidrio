package domainapp.modules.simple.dom.ordenes_trabajo.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.title.TitleService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.acciones.Empresa_delete;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;

@Service
public class Listener_empresaDelete {

    @EventListener(Empresa_delete.ActionEvent.class)
    public void on(Empresa_delete.ActionEvent ev) {
        switch(ev.getEventPhase()) {
            case DISABLE:
                Empresa empresa = ev.getSubject();
                List<Vidrio> vidrios = vidrioRepository.findByEmpresa(empresa);
                for (Vidrio vidrio : vidrios) {
                    List<OrdenDeTrabajo> ordenes = ordenRepository.findByVidrioOrderByFechaDesc(vidrio);
                    int numVisits = ordenes.size();
                    if(numVisits > 0) {
                        ev.disable(String.format("%s tiene %d orden%s de trabajo",
                                titleService.titleOf(vidrio),
                                numVisits,
                                numVisits != 1 ? "es" : ""));
                    }
                }
                break;
        }
    }

    @Inject TitleService titleService;
    @Inject OrdenDeTrabajoRepository ordenRepository;
    @Inject VidrioRepository vidrioRepository;
}
