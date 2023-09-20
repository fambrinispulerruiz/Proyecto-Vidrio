package domainapp.modules.simple.dom.vidrio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import domainapp.modules.simple.dom.empresa.Empresa;


public interface VidrioRepository extends JpaRepository<Vidrio, Long> {

    List<Vidrio> buscarPorEmpresa(Empresa empresa);
    Optional<Vidrio> buscarPorEmpresaYNombre(Empresa empresa, String nombre);
}
