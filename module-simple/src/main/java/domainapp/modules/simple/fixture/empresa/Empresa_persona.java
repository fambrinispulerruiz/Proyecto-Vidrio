package domainapp.modules.simple.fixture.empresa;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.EmpresaServices;
import domainapp.modules.simple.dom.empresa.enumeradores.TipoEmpresa;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Empresa_persona implements PersonaWithBuilderScript<EmpresaBuilder>, PersonaWithFinder<Empresa> {

	TOYOTA("Toyota Motor Corporation", "1 Toyota Ave", "800-TOYOTA", "toyota@example.com", TipoEmpresa.Proveedor),
	VOLKSWAGEN("Volkswagen Group", "123 Volkswagen St", "800-VW-CARS", "vw@example.com", TipoEmpresa.Proveedor),
	FORD("Ford Motor Company", "1 Ford Dr", "800-FORD-CAR", "ford@example.com", TipoEmpresa.Proveedor),
	HONDA("Honda Motor Co., Ltd.", "456 Honda St", "800-HONDA", "honda@example.com", TipoEmpresa.Proveedor),
	NISSAN("Nissan Motor Co., Ltd.", "789 Nissan St", "800-NISSAN", "nissan@example.com", TipoEmpresa.Proveedor),
	BMW("Bayerische Motoren Werke AG", "123 BMW Strasse", "800-BMW-CARS", "bmw@example.com", TipoEmpresa.Proveedor),
	HYUNDAI("Hyundai Motor Company", "456 Hyundai St", "800-HYUNDAI", "hyundai@example.com", TipoEmpresa.Proveedor),
	MERCEDES("Mercedes-Benz AG", "789 Mercedes Strasse", "800-MERCEDES", "mercedes@example.com", TipoEmpresa.Proveedor),
	AUDI("Audi AG", "123 Audi Strasse", "800-AUDI-CAR", "audi@example.com", TipoEmpresa.Proveedor),
	CHEVROLET("Chevrolet", "456 Chevy St", "800-CHEVY", "chevrolet@example.com", TipoEmpresa.Proveedor);


	@Getter
	private final String nombre;

	@Getter
	private final String domicilio;

	@Getter
	private final String telefono;

	@Getter
	private final String correo;

	@Getter
	private final TipoEmpresa tipoEmpresa;

	@Override
	public EmpresaBuilder builder() {
		return new EmpresaBuilder()
				.setNombre(nombre)
				.setDomicilio(domicilio)
				.setTelefono(telefono)
				.setCorreo(correo)
				.setTipoEmpresa(tipoEmpresa);
	}

	@Override
	public Empresa findUsing(final ServiceRegistry serviceRegistry) {
		EmpresaServices empresas = serviceRegistry.lookupService(EmpresaServices.class).orElse(null);
		return empresas.findByNombreExact(nombre);
	}

	public static class PersistAll extends PersonaEnumPersistAll<Empresa_persona, Empresa> {

		public PersistAll() {
			super(Empresa_persona.class);
		}
	}
}