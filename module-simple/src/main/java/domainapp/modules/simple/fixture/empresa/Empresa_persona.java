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

	JONES("Jones Motors", "123 Main St", "555-1234", "jones@example.com", TipoEmpresa.Cliente),
	FARRELL("Farrell Auto", "456 Elm St", "555-5678", "farrell@example.com", TipoEmpresa.Proveedor),
	UNDERHILL("Underhill Cars", "789 Oak St", "555-9876", "underhill@example.com", TipoEmpresa.Cliente),
	FORD("Ford Motor Company", "1 Ford Dr", "800-FORD-CAR", "ford@example.com", TipoEmpresa.Proveedor),
	YOUNGS("Youngs Automotive", "234 Birch St", "555-4321", "youngs@example.com", TipoEmpresa.Cliente),
	MAY("May Motors", "567 Pine St", "555-8765", "may@example.com", TipoEmpresa.Proveedor),
	GENGE("Genge Vehicles", "890 Cedar St", "555-2345", "genge@example.com", TipoEmpresa.Cliente),
	EWELS("Ewels Autos", "123 Spruce St", "555-6543", "ewels@example.com", TipoEmpresa.Proveedor),
	VUNIPOLA("Vunipola Cars", "456 Maple St", "555-7654", "vunipola@example.com", TipoEmpresa.Proveedor),
	ITOJE("Itoje Automotive", "789 Walnut St", "555-8765", "itoje@example.com", TipoEmpresa.Cliente);

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