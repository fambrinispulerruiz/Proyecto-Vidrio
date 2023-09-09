package domainapp.modules.simple.dom.vehiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByMarcaContaining(final String marca);

    Vehiculo findByMarca(final String marca);

}
