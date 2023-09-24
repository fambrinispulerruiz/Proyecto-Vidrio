package domainapp.webapp.application.services.homepage;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.empresa.EmpresaRepository;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajoRepository;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        logicalTypeName = "simple.HomePageViewModel"
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
    
    @CollectionLayout(named = "Vidrios Disponibles") 
    public List<Vidrio> getVidrios() {
        return vidrios.findAll();
    }
    
    @CollectionLayout(named = "Ordenes de Trabajo") 
    public List<OrdenPlusEmpresa> getOrdenPlusEmpresa() {
        return ordenRepository.findAll()
                .stream()
                .map(OrdenPlusEmpresa::new)
                .collect(Collectors.toList());
    }
    
    @Inject EmpresaRepository empresas;
    @Inject VidrioRepository vidrios;
    @Inject OrdenDeTrabajoRepository ordenRepository;
}
