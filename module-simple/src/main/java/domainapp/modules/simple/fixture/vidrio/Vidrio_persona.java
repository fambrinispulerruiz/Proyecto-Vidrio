package domainapp.modules.simple.fixture.vidrio;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import domainapp.modules.simple.fixture.modelo.Modelo_persona;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Vidrio_persona
implements PersonaWithBuilderScript<VidrioBuilder>, PersonaWithFinder<Vidrio> {
	
	CRISTAL("Cristal", "CR-001", Modelo_persona.FORD_MUSTANG, 100.00, TipoVidrio.Puerta_Delantera_I, Antena.Si, SensorLluvia.No),
	ANTIRREFLEJO("Antirreflejo", "AR-002", Modelo_persona.VOLKSWAGEN_JETTA, 150.00, TipoVidrio.Puerta_Trasera_I, Antena.No, SensorLluvia.No),
	SEGURIDAD("Vidrio de Seguridad", "VS-003", Modelo_persona.NISSAN_SENTRA, 120.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si),
	TINTADO("Vidrio Tintado", "VT-004", Modelo_persona.CHEVROLET_CRUZE,  130.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si),
	CRISTALH("Cristal", "CR-001", Modelo_persona.HONDA_CIVIC,  100.00, TipoVidrio.Puerta_Delantera_I, Antena.Si, SensorLluvia.No),
	ANTIRREFLEJON("Antirreflejo", "AR-002", Modelo_persona.NISSAN_SENTRA,  150.00, TipoVidrio.Puerta_Trasera_I, Antena.No, SensorLluvia.No),
	SEGURIDADN("Vidrio de Seguridad", "VS-003", Modelo_persona.NISSAN_SENTRA,  120.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si),
	TINTADOC("Vidrio Tintado", "VT-004", Modelo_persona.CHEVROLET_CRUZE, 130.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si),
	CRISTALV("Cristal", "CR-001", Modelo_persona.VOLKSWAGEN_JETTA, 100.00, TipoVidrio.Puerta_Delantera_I, Antena.Si, SensorLluvia.No),
	ANTIRREFLEJOF("Antirreflejo", "AR-002", Modelo_persona.FORD_MUSTANG, 150.00, TipoVidrio.Puerta_Trasera_I, Antena.No, SensorLluvia.No),
	SEGURIDADF("Vidrio de Seguridad", "VS-003", Modelo_persona.FORD_MUSTANG, 120.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si),
	TINTADON("Vidrio Tintado", "VT-004", Modelo_persona.NISSAN_SENTRA, 130.00, TipoVidrio.Parabrisa, Antena.Si, SensorLluvia.Si);
	
    @Getter private final String nombre;
    @Getter private final String codigo;
    @Getter private final Modelo_persona modelo_persona;
    @Getter private final double precio;
    @Getter private final TipoVidrio tipoVidrio;
    @Getter private final Antena antena;
    @Getter private final SensorLluvia sensor;

    @Override
    public VidrioBuilder builder() {
        return new VidrioBuilder()
                        .setNombre(nombre)
                        .setCodigo(codigo)
                        .setModelo_persona(modelo_persona)
                        .setPrecio(precio)
                        .setTipoVidrio(tipoVidrio)
                        .setAntena(antena)
                        .setSensor(sensor);
    }

    @Override
    public Vidrio findUsing(final ServiceRegistry serviceRegistry) {
        Modelo modelo = modelo_persona.findUsing(serviceRegistry);
        VidrioRepository vidrioRepository = serviceRegistry.lookupService(VidrioRepository.class).orElseThrow();
        return vidrioRepository.findByModeloAndNombre(modelo, nombre).orElse(null);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<Vidrio_persona, Vidrio> {
        public PersistAll() {
            super(Vidrio_persona.class);
        }
    }
}