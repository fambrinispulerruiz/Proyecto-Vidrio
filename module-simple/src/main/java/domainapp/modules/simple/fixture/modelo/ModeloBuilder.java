package domainapp.modules.simple.fixture.modelo;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import domainapp.modules.simple.dom.modelo.acciones.Empresa_agregarModelo;
import domainapp.modules.simple.fixture.empresa.Empresa_persona;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class ModeloBuilder extends BuilderScriptWithResult<Modelo> {

    @Getter @Setter String nombre;
    @Getter @Setter Empresa_persona empresa_persona;
    
    @Override
    protected Modelo buildResult(final ExecutionContext ec) {

        checkParam("nombre", ec, String.class);
        checkParam("empresa_persona", ec, Empresa_persona.class);

        Empresa empresa = ec.executeChildT(this, empresa_persona.builder()).getObject();

        Modelo modelo = modeloRepository.findByEmpresaAndNombre(empresa, nombre).orElse(null);
        
        if(modelo == null) {
            wrapMixin(Empresa_agregarModelo.class, empresa).act(nombre);
            modelo = modeloRepository.findByEmpresaAndNombre(empresa, nombre).orElseThrow();
        }

        return this.object = modelo;
    }

    @Inject ModeloRepository modeloRepository;
}
