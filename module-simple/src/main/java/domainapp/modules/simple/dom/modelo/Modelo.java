package domainapp.modules.simple.dom.modelo;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;

import java.util.Comparator;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.types.Nombre;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;


@Entity
@Table(
    schema="simple",
    name = "Modelo",
    uniqueConstraints = {
        @UniqueConstraint(name = "Empresa_nombre__UNQ", columnNames = {"empresa_id", "nombre"})
    }
)
@NamedQueries({
    @NamedQuery(
            name = Modelo.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE,
            query = "SELECT so " +
                    "FROM Modelo so " +
                    "WHERE so.nombre LIKE :nombre"
    )
})
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "modelos.Modelo", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Modelo implements Comparable<Modelo> {

	 static final String NAMED_QUERY__FIND_BY_LAST_NAME_LIKE = "Modelo.findByLastNameLike";
	
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


    public static Modelo withName(String nombre) {
        return withName(nombre);
    }
    
    public Modelo(String nombre, Empresa empresa) {
    	 this.nombre = nombre;
         this.empresa = empresa;
         this.activo = true;
    }

    public static Modelo withName(String nombre, Empresa empresa) {
        val modelo = new Modelo();
        modelo.setNombre(nombre);
        modelo.setEmpresa(empresa);
        modelo.setActivo(true);
        return modelo;
    }

    @Inject @Transient RepositoryService repositoryService;
    @Inject @Transient TitleService titleService;
    @Inject @Transient MessageService messageService;


    public String title() {
    	  return getNombre() + " (" + getEmpresa().getNombre() + ")";
    }


    @Column(name = "nombre", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "nombre", sequence = "1")
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    @PropertyLayout(fieldSetId = "nombre", sequence = "2")
    @Getter @Setter
    private Empresa empresa;
    
    @Column(name = "activo", nullable = false)
    @Getter @Setter
    private boolean activo;

	@Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
	@ActionLayout(associateWith = "nombre")
	public Modelo activar() {
		setActivo(true);
		return this;
	}
    
    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "nombre")
    public Modelo updateName(
            		final String nombre) {
        setNombre(nombre);
       
        return this;
    }
    
    public String default0UpdateName() {
        return getNombre();
    }
 
    private final static Comparator<Modelo> comparator =
            Comparator.comparing(Modelo::getEmpresa).thenComparing(Modelo::getNombre);

    @Override
    public int compareTo(final Modelo other) {
        return comparator.compare(this, other);
    }

}
