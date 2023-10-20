package domainapp.modules.simple.dom.ordenes_trabajo.acciones.listeners;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.acciones.Empresa_delete;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;

@Service
public class Listener_empresaDelete {

	@EventListener(Empresa_delete.ActionEvent.class)
	public void on(Empresa_delete.ActionEvent ev) {
		switch (ev.getEventPhase()) {
		case DISABLE:
			Empresa empresa = ev.getSubject();

			List<Modelo> modelos = modeloRepository.findByEmpresa(empresa);

			for (Modelo modelo : modelos) {

				List<Vidrio> vidrios = vidrioRepository.findByModelo(modelo);

				for (Vidrio vidrio : vidrios) {
					List<OrdenDeTrabajo> ordenes = ordenRepository.findByVidrioOrderByFechaDesc(vidrio);

					// Verifica si hay alguna orden con estado diferente de "Finalizado"
					boolean tieneOrdenesPendientes = ordenes.stream()
							.anyMatch(orden -> orden.getEstado() != Estado.Finalizado_y_Entregado);

					if (tieneOrdenesPendientes) {
						ev.disable(
								String.format("%s tiene órdenes de trabajo pendientes", titleService.titleOf(vidrio)));
						break; // No es necesario continuar verificando las demás órdenes
					}
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
	ModeloRepository modeloRepository;
	@Inject
	VidrioRepository vidrioRepository;
	@Inject
	RepositoryService repositoryService;
}