package domainapp.modules.simple.dom.vidrio.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.Empresa_delete;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;



@Service
public class Listener_eliminarVidrios {

    @EventListener(Empresa_delete.ActionEvent.class)
    public void on(Empresa_delete.ActionEvent ev) {
        switch(ev.getEventPhase()) {
            case EXECUTING:
                Empresa empresa = ev.getSubject();
                List<Vidrio> vidrios = vidrioRepository.findByEmpresa(empresa);
                vidrios.forEach(repositoryService::remove);
                break;
		default:
			break;
        }
    }

    @Inject VidrioRepository vidrioRepository;
    @Inject RepositoryService repositoryService;
}