package domainapp.modules.simple.fixture.vidrio;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import domainapp.modules.simple.dom.vidrio.acciones.Modelo_agregarVidrio;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import domainapp.modules.simple.fixture.modelo.Modelo_persona;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class VidrioBuilder extends BuilderScriptWithResult<Vidrio> {

    @Getter @Setter String nombre;
    @Getter @Setter String codigo;
    @Getter @Setter Modelo_persona modelo_persona;
    @Getter @Setter double precio;
    @Getter @Setter TipoVidrio tipoVidrio;
    @Getter @Setter Antena antena;
    @Getter @Setter SensorLluvia sensor;
    
    @Override
    protected Vidrio buildResult(final ExecutionContext ec) {

        checkParam("nombre", ec, String.class);
        checkParam("codigo", ec, String.class);
        checkParam("modelo_persona", ec, Modelo_persona.class);
        checkParam("precio", ec, double.class);
        checkParam("tipoVidrio", ec, TipoVidrio.class);
        checkParam("antena", ec, Antena.class);
        checkParam("sensor", ec, SensorLluvia.class);

        Modelo modelo = ec.executeChildT(this, modelo_persona.builder()).getObject();

        Vidrio vidrio = vidrioRepository.findByModeloAndNombre(modelo, nombre).orElse(null);
        
        if(vidrio == null) {
            wrapMixin(Modelo_agregarVidrio.class, modelo).act(nombre, codigo, precio, tipoVidrio, antena, sensor);
            vidrio = vidrioRepository.findByModeloAndNombre(modelo, nombre).orElseThrow();
        }

        return this.object = vidrio;
    }

    @Inject VidrioRepository vidrioRepository;
}
