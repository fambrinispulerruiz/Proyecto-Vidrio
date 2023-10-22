package domainapp.modules.simple.dom.ordenes_trabajo;

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
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.reportes.Reportes;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.types.Nombre;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;



@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.OrdenDeTrabajos"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class OrdenDeTrabajoServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final OrdenDeTrabajoRepository ordenRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public OrdenDeTrabajo create(
            final Vidrio vidrio) {
        return repositoryService.persist(OrdenDeTrabajo.withName(vidrio));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<OrdenDeTrabajo> findByNombreAseguradoLike(
            @Nombre final String nombreAsegurado) {
        return repositoryService.allMatches(
                Query.named(OrdenDeTrabajo.class, OrdenDeTrabajo.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
                     .withParameter("nombreAsegurado", "%" + nombreAsegurado + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<OrdenDeTrabajo> findByNombreAsegurado(
    			final String nombreAsegurado
            ) {
        return ordenRepository.findByNombreAseguradoContaining(nombreAsegurado);
    }


    @Programmatic
    public OrdenDeTrabajo findByNombreAseguradoExact(final String nombreAsegurado) {
        return ordenRepository.findByNombreAsegurado(nombreAsegurado);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<OrdenDeTrabajo> listAll() {
        return ordenRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Vidrio.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<OrdenDeTrabajo> q = entityManager.createQuery(
                        "SELECT p FROM OrdenDeTrabajo p ORDER BY p.nombreAsegurado",
                        OrdenDeTrabajo.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }
    
  //REPORTE ORDEN DE TRABAJO
    @Programmatic
    public Blob generarReporteOrdenDeTrabajo() throws JRException, IOException {
        List<OrdenDeTrabajo> ordenesdetrabajo = new ArrayList<OrdenDeTrabajo>();
        Reportes reportes = new Reportes();
        ordenesdetrabajo = repositoryService.allInstances(OrdenDeTrabajo.class);
        return reportes.ListadoOrdenesDeTrabajoPDF(ordenesdetrabajo);
    }

}