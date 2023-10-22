package domainapp.modules.simple.dom.vidrio;

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
import domainapp.modules.simple.types.Nombre;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;


@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.Vidrios"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class VidrioServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final VidrioRepository vidrioRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public Vidrio create(
            final String nombre) {
        return repositoryService.persist(Vidrio.withName(nombre));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<Vidrio> findByNombreLike(
            @Nombre final String nombre) {
        return repositoryService.allMatches(
                Query.named(Vidrio.class, Vidrio.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
                     .withParameter("nombre", "%" + nombre + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Vidrio> findByNombre(
    			final String nombre
            ) {
        return vidrioRepository.findByNombreContaining(nombre);
    }


    @Programmatic
    public Vidrio findByNombreExact(final String nombre) {
        return vidrioRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Vidrio> listAll() {
        return vidrioRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Vidrio.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Vidrio> q = entityManager.createQuery(
                        "SELECT p FROM Vidrio p ORDER BY p.nombre",
                        Vidrio.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }
    
  //REPORTE VIDRIO
    @Programmatic
    public Blob generarReporteVidrio() throws JRException, IOException {
        List<Vidrio> vidrios = new ArrayList<Vidrio>();
        Reportes reportes = new Reportes();
        vidrios = repositoryService.allInstances(Vidrio.class);
        return reportes.ListadoVidriosPDF(vidrios);
    }

}
