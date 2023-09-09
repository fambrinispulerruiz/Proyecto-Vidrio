package domainapp.modules.simple.fixture;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioServices;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SimpleObject_persona
implements PersonaWithBuilderScript<SimpleObjectBuilder>, PersonaWithFinder<Vidrio> {

    FOO("Foo"),
    BAR("Bar"),
    BAZ("Baz"),
    FRODO("Frodo"),
    FROYO("Froyo"),
    FIZZ("Fizz"),
    BIP("Bip"),
    BOP("Bop"),
    BANG("Bang"),
    BOO("Boo");

    private final String nombre;

    @Override
    public SimpleObjectBuilder builder() {
        return new SimpleObjectBuilder().setNombre(nombre);
    }

    @Override
    public Vidrio findUsing(final ServiceRegistry serviceRegistry) {
        VidrioServices simpleObjects = serviceRegistry.lookupService(VidrioServices.class).orElse(null);
        return simpleObjects.findByNameExact(nombre);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<SimpleObject_persona, Vidrio> {

        public PersistAll() {
            super(SimpleObject_persona.class);
        }
    }
}
