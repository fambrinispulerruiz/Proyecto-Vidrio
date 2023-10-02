package domainapp.modules.simple.dom.modelo;

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
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import domainapp.modules.simple.types.Nombre;
import lombok.RequiredArgsConstructor;


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


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public Modelo create(
            final String nombre) {
        return repositoryService.persist(Modelo.withName(nombre));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<Modelo> findByNombreLike(
            @Nombre final String nombre) {
        return repositoryService.allMatches(
                Query.named(Modelo.class, Modelo.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
                     .withParameter("nombre", "%" + nombre + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Modelo> findByNombre(
    			final String nombre
            ) {
        return modeloRepository.findByNombreContaining(nombre);
    }


    @Programmatic
    public Modelo findByNombreExact(final String nombre) {
        return modeloRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Modelo> listAll() {
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


}
