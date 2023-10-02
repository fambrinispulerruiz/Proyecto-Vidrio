package domainapp.modules.simple.dom.vidrio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import domainapp.modules.simple.dom.modelo.Modelo;


public interface VidrioRepository extends JpaRepository<Vidrio, Long> {

    List<Vidrio> findByModelo(Modelo modelo);
    Optional<Vidrio> findByModeloAndNombre(Modelo modelo, String nombre);
}
