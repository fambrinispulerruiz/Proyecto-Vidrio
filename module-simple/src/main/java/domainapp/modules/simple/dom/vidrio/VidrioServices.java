package domainapp.modules.simple.dom.vidrio;

import java.util.List;

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
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import domainapp.modules.simple.types.Nombre;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.VidrioServices"
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class VidrioServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final VidrioRepository vidrioRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-plus")
    public Vidrio crearVidrio(
            @Nombre final String nombre, final int codigo, final double precio, final TipoVidrio tipoVidrio) {
        return repositoryService.persist(Vidrio.withName(nombre, codigo, precio, tipoVidrio));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-search")
    public List<Vidrio> buscarVidrio(
            @Nombre final String nombre) {
        return repositoryService.allMatches(
                Query.named(Vidrio.class, Vidrio.NAMED_QUERY__FIND_BY_NAME_LIKE)
                     .withParameter("nombre", "%" + nombre + "%"));
    }


//    @Action(semantics = SemanticsOf.SAFE)
//    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, promptStyle = PromptStyle.DIALOG_SIDEBAR)
//    public List<Vidrio> findByNombre(
//            @Nombre final String nombre
//            ) {
//        return vidrioRepository.findByNombreContaining(nombre);
//    }
//
//
    @Programmatic
    public Vidrio findByNameExact(final String nombre) {
        return vidrioRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-list")
    public List<Vidrio> verVidrios() {
        return vidrioRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Vidrio.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Vidrio> q = entityManager.createQuery(
                        "SELECT p FROM SimpleObject p ORDER BY p.name",
                        Vidrio.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
