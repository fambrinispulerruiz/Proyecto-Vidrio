package domainapp.modules.simple.dom.ordenes_trabajo.acciones.listeners;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.acciones.Modelo_delete;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;

@Service
public class Listener_modeloDelete {

	@EventListener(Modelo_delete.ActionEvent.class)
	public void on(Modelo_delete.ActionEvent ev) {
		switch (ev.getEventPhase()) {
		case DISABLE:
			Modelo modelo = ev.getSubject();
			List<Vidrio> vidrios = vidrioRepository.findByModelo(modelo);

			for (Vidrio vidrio : vidrios) {
				List<OrdenDeTrabajo> ordenes = ordenRepository.findByVidrioOrderByFechaDesc(vidrio);

				// Verifica si hay alguna orden con estado diferente de "Finalizado"
				boolean tieneOrdenesPendientes = ordenes.stream()
						.anyMatch(orden -> orden.getEstado() != Estado.Finalizado_y_Entregado);

				if (tieneOrdenesPendientes) {
					ev.disable(String.format("%s tiene órdenes de trabajo pendientes", titleService.titleOf(vidrio)));
					break; // No es necesario continuar verificando las demás órdenes
				}
			}
			break;
		}
	}

	@Inject
	TitleService titleService;
	@Inject
	OrdenDeTrabajoRepository ordenRepository;
	@Inject
	VidrioRepository vidrioRepository;
	@Inject
	RepositoryService repositoryService;
}
