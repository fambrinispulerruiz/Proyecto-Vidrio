package domainapp.modules.simple.dom.vidrio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VidrioRepository extends JpaRepository<Vidrio, Long> {

    List<Vidrio> findByNombreContaining(final String nombre);

    Vidrio findByNombre(final String nombre);

}
