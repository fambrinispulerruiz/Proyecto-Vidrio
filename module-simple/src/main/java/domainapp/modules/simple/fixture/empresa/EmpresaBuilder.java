package domainapp.modules.simple.fixture.empresa;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.EmpresaServices;
import domainapp.modules.simple.dom.empresa.enumeradores.TipoEmpresa;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class EmpresaBuilder extends BuilderScriptWithResult<Empresa> {

    @Getter @Setter
    private String nombre;
   
    @Getter @Setter
    private String domicilio;
    
    @Getter @Setter
    private String telefono;
    
    @Getter @Setter
    private String correo;
    
    @Getter @Setter
    private TipoEmpresa tipoEmpresa;

    @Override
    protected Empresa buildResult(final ExecutionContext ec) {

        checkParam("nombre", ec, String.class);
        
        checkParam("domicilio", ec, String.class);
        
        checkParam("telefono", ec, String.class);
        
        checkParam("correo", ec, String.class);
        
        checkParam("tipoEmpresa", ec, TipoEmpresa.class);

        Empresa empresa = empresas.findByNombreExact(nombre);
        if(empresa == null) {
            empresa = wrap(empresas).create(nombre, domicilio, telefono, correo, tipoEmpresa);
        }
        return this.object = empresa;
    }

    @Inject EmpresaServices empresas;
}
