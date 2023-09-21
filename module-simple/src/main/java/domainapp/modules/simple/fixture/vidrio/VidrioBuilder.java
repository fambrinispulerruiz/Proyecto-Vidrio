package domainapp.modules.simple.fixture.vidrio;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import domainapp.modules.simple.dom.vidrio.acciones.Empresa_agregarVidrio;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import domainapp.modules.simple.fixture.empresa.Empresa_persona;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class VidrioBuilder extends BuilderScriptWithResult<Vidrio> {

    @Getter @Setter String nombre;
    @Getter @Setter String codigo;
    @Getter @Setter Empresa_persona empresa_persona;
    @Getter @Setter String modelo;
    @Getter @Setter double precio;
    @Getter @Setter TipoVidrio tipoVidrio;
    @Getter @Setter Antena antena;
    @Getter @Setter SensorLluvia sensor;
    
    @Override
    protected Vidrio buildResult(final ExecutionContext ec) {

        checkParam("nombre", ec, String.class);
        checkParam("codigo", ec, String.class);
        checkParam("empresa_persona", ec, Empresa_persona.class);
        checkParam("modelo", ec, String.class);
        checkParam("precio", ec, double.class);
        checkParam("tipoVidrio", ec, TipoVidrio.class);
        checkParam("antena", ec, Antena.class);
        checkParam("sensor", ec, SensorLluvia.class);

        Empresa empresa = ec.executeChildT(this, empresa_persona.builder()).getObject();

        Vidrio vidrio = vidrioRepository.buscarPorEmpresaYNombre(empresa, nombre).orElse(null);
        
        if(vidrio == null) {
            wrapMixin(Empresa_agregarVidrio.class, empresa).act(nombre, codigo, empresa, modelo, precio, tipoVidrio, antena, sensor);
            vidrio = vidrioRepository.buscarPorEmpresaYNombre(empresa, nombre).orElseThrow();
        }

        return this.object = vidrio;
    }

    @Inject VidrioRepository vidrioRepository;
}
