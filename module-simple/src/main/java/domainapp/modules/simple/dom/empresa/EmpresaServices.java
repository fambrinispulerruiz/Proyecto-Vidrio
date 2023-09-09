package domainapp.modules.simple.dom.empresa;

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
        logicalTypeName = "simple.EmpresaServices"
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
@lombok.RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class EmpresaServices {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final EmpresaRepository vidrioRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-plus")
    public Empresa crearEmpresa(
            @Nombre final String nombre, final String domicilio, final long telefono, final TipoEmpresa tipoEmpresa) {
        return repositoryService.persist(Empresa.withName(nombre, domicilio, telefono, tipoEmpresa));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR, cssClassFa = "fa-search")
    public List<Empresa> buscarEmpresa(
            @Nombre final String nombre) {
        return repositoryService.allMatches(
                Query.named(Empresa.class, Empresa.NAMED_QUERY__FIND_BY_NAME_LIKE)
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
    public Empresa findByNameExact(final String nombre) {
        return vidrioRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT, cssClassFa = "fa-list")
    public List<Empresa> verEmpresas() {
        return vidrioRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Empresa.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Empresa> q = entityManager.createQuery(
                        "SELECT p FROM SimpleObject p ORDER BY p.name",
                        Empresa.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
