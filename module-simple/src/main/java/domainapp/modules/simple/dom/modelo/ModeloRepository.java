package domainapp.modules.simple.dom.modelo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import domainapp.modules.simple.dom.empresa.Empresa;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

	List<Modelo> findByNombreContaining(final String nombre);

	Modelo findByNombre(final String nombre);

	List<Modelo> findByEmpresa(Empresa empresa);

	Optional<Modelo> findByEmpresaAndNombre(Empresa empresa, String nombre);
}
