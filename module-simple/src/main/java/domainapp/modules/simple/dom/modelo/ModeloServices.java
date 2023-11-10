package domainapp.modules.simple.dom.modelo;

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

import domainapp.modules.simple.dom.reportes.Reportes;
import domainapp.modules.simple.types.Nombre;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;


@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.Modelos"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class ModeloServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final ModeloRepository modeloRepository;

     //Ocultado
//    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
//    public Modelo create(
//            final String nombre) {
//        return repositoryService.persist(Modelo.withName(nombre));
//    }

     //Ocultado
//    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
//    public List<Modelo> findByNombreLike(
//            @Nombre final String nombre) {
//        return repositoryService.allMatches(
//                Query.named(Modelo.class, Modelo.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
//                     .withParameter("nombre", "%" + nombre + "%"));
//    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-search")
    public List<Modelo> buscarNombreDelModelo(
    			final String nombre
            ) {
        return modeloRepository.findByNombreContaining(nombre);
    }


    @Programmatic
    public Modelo findByNombreExact(final String nombre) {
        return modeloRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-list")
    public List<Modelo> verModelos() {
        return modeloRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Modelo.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Modelo> q = entityManager.createQuery(
                        "SELECT p FROM Modelo p ORDER BY p.nombre",
                        Modelo.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }
    
  //REPORTE MODELO
    @Programmatic
    public Blob generarReporteModelo() throws JRException, IOException {
        List<Modelo> modelos = new ArrayList<Modelo>();
        Reportes reportes = new Reportes();
        modelos = repositoryService.allInstances(Modelo.class);
        return reportes.ListadoModelosPDF(modelos);
    }

}
