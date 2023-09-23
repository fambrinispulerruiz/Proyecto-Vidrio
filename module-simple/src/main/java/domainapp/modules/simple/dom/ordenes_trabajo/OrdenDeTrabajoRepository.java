package domainapp.modules.simple.dom.ordenes_trabajo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import domainapp.modules.simple.dom.vidrio.Vidrio;

public interface OrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {

	List<OrdenDeTrabajo> findByVidrioOrderByFechaDesc(Vidrio vidrio);

}
