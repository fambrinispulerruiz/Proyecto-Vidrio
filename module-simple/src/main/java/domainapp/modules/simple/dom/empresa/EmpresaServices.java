package domainapp.modules.simple.dom.empresa;

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

import domainapp.modules.simple.dom.empresa.enumeradores.TipoEmpresa;
import domainapp.modules.simple.types.Nombre;
import lombok.RequiredArgsConstructor;

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
    public Empresa create(
            @Nombre final String nombre,
            final String domicilio,
            final String telefono,
            final String correo,
            final TipoEmpresa tipoEmpresa) {
        return repositoryService.persist(Empresa.withName(nombre, domicilio, telefono, correo, tipoEmpresa));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<Empresa> findByNombreLike(
            @Nombre final String nombre) {
        return repositoryService.allMatches(
                Query.named(Empresa.class, Empresa.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE)
                     .withParameter("nombre", "%" + nombre + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Empresa> findByNombre(
            @Nombre final String nombre
            ) {
        return empresaRepository.findByNombreContaining(nombre);
    }


    @Programmatic
    public Empresa findByNombreExact(final String nombre) {
        return empresaRepository.findByNombre(nombre);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Empresa> listAll() {
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


}
