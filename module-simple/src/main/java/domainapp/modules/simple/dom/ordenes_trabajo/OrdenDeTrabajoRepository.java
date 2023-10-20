package domainapp.modules.simple.dom.ordenes_trabajo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import domainapp.modules.simple.dom.vidrio.Vidrio;

public interface OrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {

	List<OrdenDeTrabajo> findByVidrioOrderByFechaDesc(Vidrio vidrio);
	
	List<OrdenDeTrabajo> findByNombreAseguradoContaining(final String nombreAsegurado);

	OrdenDeTrabajo findByNombreAsegurado(final String nombreAsegurado);
	
    List<OrdenDeTrabajo> findByVidrio(Vidrio vidrio);
    
    Optional<OrdenDeTrabajo> findByVidrioAndNombreAsegurado(Vidrio vidrio, String nombreAsegurado);

}
