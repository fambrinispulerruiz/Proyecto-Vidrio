package domainapp.modules.simple.dom.vidrio;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.vidrio.enumeradores.Antena;
import domainapp.modules.simple.dom.vidrio.enumeradores.SensorLluvia;
import domainapp.modules.simple.dom.vidrio.enumeradores.TipoVidrio;
import domainapp.modules.simple.types.Nombre;
import jdk.jfr.Label;


@Entity
@Table(
    schema="simple",
    name = "Vidrio",
    uniqueConstraints = {
        @UniqueConstraint(name = "Modelo_nombre__UNQ", columnNames = {"modelo_id", "nombre"})
    }
)
@NamedQueries({
    @NamedQuery(
            name = Vidrio.NAMED_QUERY__FIND_BY_LAST_NAME_LIKE,
            query = "SELECT so " +
                    "FROM Vidrio so " +
                    "WHERE so.nombre LIKE :nombre"
    )
})
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "vidrios.Vidrio", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Vidrio implements Comparable<Vidrio> {

	 static final String NAMED_QUERY__FIND_BY_LAST_NAME_LIKE = "Vidrio.findByLastNameLike";
	
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

    public static Vidrio withName(String nombre) {
        return withName(nombre);
    }
    

    public Vidrio(String nombre, String codigo, Modelo modelo, double precio, TipoVidrio tipoVidrio, Antena antena, SensorLluvia sensor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.modelo = modelo;
        this.precio = precio;
        this.tipoVidrio = tipoVidrio;
        this.antena = antena;
        this.sensor = sensor;
        this.activo = true;
    }

    public static Vidrio withName(String nombre, String codigo, Modelo modelo, double precio, TipoVidrio tipoVidrio, Antena antena, SensorLluvia sensor) {
        val vidrio = new Vidrio();
        vidrio.setNombre(nombre);
        vidrio.setCodigo(codigo);
        vidrio.setModelo(modelo);
        vidrio.setPrecio(precio);
        vidrio.setTipoVidrio(tipoVidrio);
        vidrio.setAntena(antena);
        vidrio.setSensor(sensor);
        vidrio.setActivo(true);
        return vidrio;
    }

    public String title() {
        return getNombre() + " (" + getModelo().getNombre() + ")";
    }

    @Column(name = "nombre", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "nombre", sequence = "1")
    private String nombre;
    
    @Column(name = "codigo", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "nombre", sequence = "1")
    private String codigo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "modelo_id")
    @PropertyLayout(fieldSetId = "nombre", sequence = "2")
    @Getter @Setter
    private Modelo modelo;
    
   
    @Column(name = "precio", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "2")
    private double precio;

    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoVidrio", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "1")
    private TipoVidrio tipoVidrio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "antena", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "3")
    @Label("¿Posee Antena?")
    private Antena antena;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sensor", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "4")
    @Label("¿Posee Sensor de Lluvia?")
    private SensorLluvia sensor;
    
    @Column(name = "activo", nullable = false)
    @Getter @Setter
    private boolean activo;
    
    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
	@ActionLayout(associateWith = "details")
	public Vidrio activar() {
		setActivo(true);
		return this;
	}
    
    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "nombre")
    public Vidrio updateName(
            		final String nombre,
            		final String codigo,
            		final double precio,
            		final TipoVidrio tipoVidrio,
            		final Antena antena,
            		final SensorLluvia sensor) {
        setNombre(nombre);
        setCodigo(codigo);
        setPrecio(precio);
        setTipoVidrio(tipoVidrio);
        setAntena(antena);
        setSensor(sensor);
        setActivo(true);
       
        return this;
    }
    
    public String default0UpdateName() {
        return getNombre();
    }
    public String default1UpdateName() {
        return getCodigo();
    }

    public double default2UpdateName() {
        return getPrecio();
    }
    public TipoVidrio default3UpdateName() {
        return getTipoVidrio();
    }
    public Antena default4UpdateName() {
        return getAntena();
    }
    public SensorLluvia default5UpdateName() {
        return getSensor();
    }

    private final static Comparator<Vidrio> comparator =
            Comparator.comparing(Vidrio::getModelo).thenComparing(Vidrio::getNombre);

    @Override
    public int compareTo(final Vidrio other) {
        return comparator.compare(this, other);
    }

}
