package domainapp.modules.simple.dom.vidrio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VidrioRepository extends JpaRepository<Vidrio, Long> {

    List<Vidrio> findByNameContaining(final String name);

    Vidrio findByName(final String name);

}
