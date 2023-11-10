package domainapp.modules.simple.dom.empresa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import domainapp.modules.simple.dom.empresa.enumeradores.TipoEmpresa;
import domainapp.modules.simple.dom.reportes.Reportes;
import domainapp.modules.simple.types.Nombre;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.Empresas"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class EmpresaServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final EmpresaRepository empresaRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-plus")
    public Empresa CrearEmpresa(
            @Nombre final String nombre,
            final String domicilio,
            final String telefono,
            final String correo,
            final TipoEmpresa tipoEmpresa) {
        return repositoryService.persist(Empresa.withName(nombre, domicilio, telefono, correo, tipoEmpresa));
    }

    //Ocultado
//    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
//    public List<Empresa> findByNombreLike(
//            @Nombre final String nombre) {
//        return repositoryService.allMatches(
//                Query.named(Empresa.class, Empresa.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
//                     .withParameter("nombre", "%" + nombre + "%"));
//    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-search")
    public List<Empresa> buscarNombreDeLaEmpresa(
            @Nombre final String nombre
            ) {
        return empresaRepository.findByNombreContaining(nombre);
    }


    @Programmatic
    public Empresa findByNombreExact(final String nombre) {
        return empresaRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-list")
    public List<Empresa> VerEmpresas() {
        return empresaRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Empresa.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Empresa> q = entityManager.createQuery(
                        "SELECT p FROM Empresa p ORDER BY p.nombre",
                        Empresa.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }
    
  //REPORTE EMPRESA
    @Programmatic
    public Blob generarReporteEmpresa() throws JRException, IOException {
        List<Empresa> empresas = new ArrayList<Empresa>();
        Reportes reportes = new Reportes();
        empresas = repositoryService.allInstances(Empresa.class);
        return reportes.ListadoEmpresasPDF(empresas);
    }

}
