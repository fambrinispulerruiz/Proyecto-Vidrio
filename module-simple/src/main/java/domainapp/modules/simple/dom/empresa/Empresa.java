package domainapp.modules.simple.dom.empresa;

import java.util.Comparator;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import domainapp.modules.simple.dom.empresa.enumeradores.TipoEmpresa;
import domainapp.modules.simple.types.Nombre;


@Entity
@Table(
    schema="simple",
    name = "Empresa",
    uniqueConstraints = {
        @UniqueConstraint(name = "Empresa__nombre__UNQ", columnNames = {"nombre"})
    }
)
@NamedQueries({
        @NamedQuery(
                name = Empresa.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE,
                query = "SELECT so " +
                        "FROM Empresa so " +
                        "WHERE so.nombre LIKE :nombre"
        )
})
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "vidrios.Empresa", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Empresa implements Comparable<Empresa> {

    static final String NAMED_QUERY__FIND_BY_LAST_NAME_LIKE = "Empresa.findByLastNameLike";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "metadata", sequence = "1")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    @PropertyLayout(fieldSetId = "metadata", sequence = "999")
    @Getter @Setter
    private long version;

    public static Empresa withName(String nombre) {
        return withName(nombre, null, null, null, null);
    }

    public static Empresa withName(String nombre, String domicilio, String telefono, String correo, TipoEmpresa tipoEmpresa) {
        val empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setDomicilio(domicilio);
        empresa.setTelefono(telefono);
        empresa.setCorreo(correo);
        empresa.setTipoEmpresa(tipoEmpresa);
        empresa.setActivo(true);
        return empresa;
    }

    @Inject @Transient RepositoryService repositoryService;
    @Inject @Transient TitleService titleService;
    @Inject @Transient MessageService messageService;


    public String title() {
        return getNombre();
    }

    @Transient
    @PropertyLayout(fieldSetId = "nombre", sequence = "1")
    public String getName() {
        return getNombre();
    }

    @Nombre
    @Column(name = "nombre", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter @ToString.Include
    @Property(hidden = Where.EVERYWHERE)
    private String nombre;

    @Column(name = "domicilio", nullable = true)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "contactDetails", sequence = "1")
    @Property(hidden = Where.EVERYWHERE)
    private String domicilio;

    @Column(name = "telefono", nullable = true)
    @PropertyLayout(fieldSetId = "contactDetails", sequence = "2")
    @Getter @Setter
    private String telefono;

    @Column(name = "correo", nullable = true)
    @PropertyLayout(fieldSetId = "contactDetails", sequence = "3")
    @Getter @Setter
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoEmpresa", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "contactDetails", sequence = "4")
    private TipoEmpresa tipoEmpresa;

    @Column(name = "activo", nullable = false)
    @Getter @Setter
    private boolean activo;

    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "name")
    public Empresa updateName(
            @Nombre final String nombre,
            		final String domicilio,
            		final String telefono,
            		final String correo,
            		final TipoEmpresa tipoEmpresa) {
        setNombre(nombre);
        setDomicilio(domicilio);
        setTelefono(telefono);
        setCorreo(correo);
        setTipoEmpresa(tipoEmpresa);
        setActivo(true);
        return this;
    }
    public String default0UpdateName() {
        return getNombre();
    }
    public String default1UpdateName() {
        return getDomicilio();
    }
    public String default2UpdateName() {
        return getTelefono();
    }
    public String default3UpdateName() {
        return getCorreo();
    }
    public TipoEmpresa default4UpdateName() {
        return getTipoEmpresa();
    }



    private final static Comparator<Empresa> comparator =
            Comparator.comparing(Empresa::getNombre);

    @Override
    public int compareTo(final Empresa other) {
        return comparator.compare(this, other);
    }

}
