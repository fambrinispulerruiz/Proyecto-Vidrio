package domainapp.modules.simple;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.vidrio.Vidrio;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan(basePackageClasses = {OrdenesModule.class})
public class OrdenesModule implements ModuleWithFixtures {

    @Override
    public FixtureScript getTeardownFixture() {
        return new FixtureScript() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                repositoryService.removeAll(Vidrio.class);
                repositoryService.removeAll(Empresa.class);
                repositoryService.removeAll(OrdenDeTrabajo.class);
            }
        };
    }
}
