package domainapp.modules.simple.dom.modelo.acciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.acciones.Empresa_delete;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;

	@Service
	public class Listener_eliminarModelo {

	    @EventListener(Empresa_delete.ActionEvent.class)
	    public void on(Empresa_delete.ActionEvent ev) {
	        switch(ev.getEventPhase()) {
	            case EXECUTING:
	                Empresa empresa = ev.getSubject();
	                List<Modelo> modelos = modeloRepository.findByEmpresa(empresa);
	                modelos.forEach(repositoryService::remove);
	                break;
			default:
				break;
	        }
	    }

	    @Inject ModeloRepository modeloRepository;
	    @Inject RepositoryService repositoryService;
	}