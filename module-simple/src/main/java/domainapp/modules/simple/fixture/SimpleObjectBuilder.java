package domainapp.modules.simple.fixture;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioServices;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SimpleObjectBuilder extends BuilderScriptWithResult<Vidrio> {

    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private int codigo;
    
    @Getter @Setter
    private double precio;

    @Override
    protected Vidrio buildResult(final ExecutionContext ec) {

        checkParam("name", ec, String.class);

        return wrap(vidrioServices).create(name, codigo, precio);
    }

    // -- DEPENDENCIES

    @Inject VidrioServices vidrioServices;

}
