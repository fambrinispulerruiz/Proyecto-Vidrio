package domainapp.modules.simple.dom.vidrio.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.acciones.Modelo_delete;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;



@Service
public class Listener_eliminarVidrios {

    @EventListener(Modelo_delete.ActionEvent.class)
    public void on(Modelo_delete.ActionEvent ev) {
        switch(ev.getEventPhase()) {
            case EXECUTING:
                Modelo modelo = ev.getSubject();
                List<Vidrio> vidrios = vidrioRepository.findByModelo(modelo);
                vidrios.forEach(repositoryService::remove);
                break;
		default:
			break;
        }
    }

    @Inject VidrioRepository vidrioRepository;
    @Inject RepositoryService repositoryService;
}