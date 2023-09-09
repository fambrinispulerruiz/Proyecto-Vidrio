package domainapp.modules.simple.dom.vehiculo;

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



@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "simple.VehiculoServices"
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class VehiculoServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final VehiculoRepository vidrioRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-plus")
    public Vehiculo crearVehiculo(
    		final String marca, final String modelo) {
        return repositoryService.persist(Vehiculo.withName(marca, modelo));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-search")
    public List<Vehiculo> buscarVehiculo(
             final String marca) {
        return repositoryService.allMatches(
                Query.named(Vehiculo.class, Vehiculo.NAMED_QUERY__FIND_BY_NAME_LIKE)
                     .withParameter("marca", "%" + marca + "%"));
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
    public Vehiculo findByNameExact(final String marca) {
        return vidrioRepository.findByMarca(marca);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-list")
    public List<Vehiculo> verVehiculos() {
        return vidrioRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Vehiculo.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Vehiculo> q = entityManager.createQuery(
                        "SELECT p FROM SimpleObject p ORDER BY p.name",
                        Vehiculo.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
