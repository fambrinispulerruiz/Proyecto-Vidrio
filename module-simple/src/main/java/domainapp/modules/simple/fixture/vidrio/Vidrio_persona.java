package domainapp.modules.simple.fixture.vidrio;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import domainapp.modules.simple.fixture.empresa.Empresa_persona;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Vidrio_persona
implements PersonaWithBuilderScript<VidrioBuilder>, PersonaWithFinder<Vidrio> {
	
	 CRISTAL("Cristal", "CR-001", Empresa_persona.FARRELL, "Modelo Estándar", 100.00, TipoVidrio.Puerta_Delantera_I, Antena.Si, SensorLluvia.No),
	 ANTIRREFLEJO("Antirreflejo", "AR-002", Empresa_persona.VUNIPOLA, "Modelo Premium", 150.00, TipoVidrio.Puerta_Trasera_I, Antena.No, SensorLluvia.No),
	 SEGURIDAD("Vidrio de Seguridad", "VS-003", Empresa_persona.FORD, "Modelo Seguro", 120.00, TipoVidrio.Parabrisa,  Antena.Si, SensorLluvia.Si),
	 TINTADO("Vidrio Tintado", "VT-004", Empresa_persona.GENGE, "Modelo Oscuro", 130.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si);

    @Getter private final String nombre;
    @Getter private final String codigo;
    @Getter private final Empresa_persona empresa_persona;
    @Getter private final String modelo;
    @Getter private final double precio;
    @Getter private final TipoVidrio tipoVidrio;
    @Getter private final Antena antena;
    @Getter private final SensorLluvia sensor;

    @Override
    public VidrioBuilder builder() {
        return new VidrioBuilder()
                        .setNombre(nombre)
                        .setCodigo(codigo)
                        .setEmpresa_persona(empresa_persona)
                        .setModelo(modelo)
                        .setPrecio(precio)
                        .setTipoVidrio(tipoVidrio)
                        .setAntena(antena)
                        .setSensor(sensor);
    }

    @Override
    public Vidrio findUsing(final ServiceRegistry serviceRegistry) {
        Empresa empresa = empresa_persona.findUsing(serviceRegistry);
        VidrioRepository vidrioRepository = serviceRegistry.lookupService(VidrioRepository.class).orElseThrow();
        return vidrioRepository.buscarPorEmpresaYNombre(empresa, nombre).orElse(null);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<Vidrio_persona, Vidrio> {
        public PersistAll() {
            super(Vidrio_persona.class);
        }
    }
}