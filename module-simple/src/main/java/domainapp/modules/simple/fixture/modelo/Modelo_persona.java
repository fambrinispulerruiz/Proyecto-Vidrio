package domainapp.modules.simple.fixture.modelo;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import domainapp.modules.simple.fixture.empresa.Empresa_persona;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Modelo_persona
implements PersonaWithBuilderScript<ModeloBuilder>, PersonaWithFinder<Modelo> {
	
	TOYOTA_COROLLA("Corolla", Empresa_persona.TOYOTA),
	VOLKSWAGEN_JETTA("Jetta", Empresa_persona.VOLKSWAGEN),
	FORD_MUSTANG("Mustang", Empresa_persona.FORD),
	HONDA_CIVIC("Civic", Empresa_persona.HONDA),
	NISSAN_SENTRA("Sentra", Empresa_persona.NISSAN),
	BMW_X5("X5", Empresa_persona.BMW),
	HYUNDAI_ELANTA("Elantra", Empresa_persona.HYUNDAI),
	MERCEDES_BENZ_CLA("CLA", Empresa_persona.MERCEDES),
	AUDI_A4("A4", Empresa_persona.AUDI),
	CHEVROLET_CRUZE("Cruze", Empresa_persona.CHEVROLET);

    @Getter private final String nombre;
    @Getter private final Empresa_persona empresa_persona;

    @Override
    public ModeloBuilder builder() {
        return new ModeloBuilder()
                        .setNombre(nombre)
                        .setEmpresa_persona(empresa_persona);
    }

    @Override
    public Modelo findUsing(final ServiceRegistry serviceRegistry) {
        Empresa empresa = empresa_persona.findUsing(serviceRegistry);
        ModeloRepository modeloRepository = serviceRegistry.lookupService(ModeloRepository.class).orElseThrow();
        return modeloRepository.findByEmpresaAndNombre(empresa, nombre).orElse(null);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<Modelo_persona, Modelo> {
        public PersistAll() {
            super(Modelo_persona.class);
        }
    }
}