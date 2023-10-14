package domainapp.modules.simple.dom.reportes;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.Blob;
import domainapp.modules.simple.dom.empresa.EmpresaServices;
import domainapp.modules.simple.dom.modelo.ModeloServices;
import net.sf.jasperreports.engine.JRException;

@DomainService(nature = NatureOfService.VIEW, logicalTypeName = "simple.Reportes")
public class ReportesGenerar {

	@Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Exportar Empresas PDF", sequence = "1")
    public Blob generarReporteEmpresa() throws JRException, IOException{
        return empresas.generarReporteEmpresa();
    }

    @Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, named = "Exportar Modelos PDF", sequence = "1")
    public Blob generarReporteModelos() throws JRException, IOException{
        return modelos.generarReporteModelo();
    }

    @Inject RepositoryService repositoryService;
    @Inject EmpresaServices empresas;
    @Inject ModeloServices modelos;
}
