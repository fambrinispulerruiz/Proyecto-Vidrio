package domainapp.modules.simple.dom.empresa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> buscarPorNombreLista(final String nombre);

    Empresa buscarPorNombre(final String nombre);

}
