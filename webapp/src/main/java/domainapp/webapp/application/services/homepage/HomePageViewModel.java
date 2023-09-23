package domainapp.webapp.application.services.homepage;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.EmpresaRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        logicalTypeName = "empresa.HomePageViewModel"
        )
@HomePage
@DomainObjectLayout()
public class HomePageViewModel {

    public String title() {
        return getEmpresas().size() + " Empresas";
    }

    public List<Empresa> getEmpresas() {
        return empresas.findAll();
    }

    public List<Vidrio> getVidrios() {
        return vidrios.findAll();
    }
    
    @Inject EmpresaRepository empresas;
    @Inject VidrioRepository vidrios;
}
