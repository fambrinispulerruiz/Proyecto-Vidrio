package domainapp.webapp.application.fixture.scenarios;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixturesService;

import domainapp.modules.simple.fixture.empresa.Empresa_persona;
import domainapp.modules.simple.fixture.vidrio.*;

public class DomainAppDemo extends FixtureScript {

    @Override
    protected void execute(final ExecutionContext ec) {
        ec.executeChildren(this, moduleWithFixturesService.getTeardownFixture());
        ec.executeChild(this, new Empresa_persona.PersistAll());
        ec.executeChild(this, new Vidrio_persona.PersistAll());
    }

    @Inject ModuleWithFixturesService moduleWithFixturesService;

}
