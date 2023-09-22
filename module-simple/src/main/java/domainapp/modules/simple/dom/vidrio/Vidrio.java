package domainapp.modules.simple.dom.vidrio;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
import domainapp.modules.simple.dom.empresa.Empresa;
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
        @UniqueConstraint(name = "Empresa_nombre__UNQ", columnNames = {"empresa_id", "nombre"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "vidrios.Vidrio", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Vidrio implements Comparable<Vidrio> {

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


    public Vidrio(String nombre, String codigo, Empresa empresa, String modelo, double precio, TipoVidrio tipoVidrio, Antena antena, SensorLluvia sensor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.empresa = empresa;
        this.modelo = modelo;
        this.precio = precio;
        this.tipoVidrio = tipoVidrio;
        this.antena = antena;
        this.sensor = sensor;
    }


    public String title() {
        return getNombre() + " (" + getEmpresa().getNombre() + ", " + getModelo();
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
    @JoinColumn(name = "empresa_id")
    @PropertyLayout(fieldSetId = "nombre", sequence = "2")
    @Getter @Setter
    private Empresa empresa;
    
    @Column(name = "modelo", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "1")
    private String modelo;
    
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


    private final static Comparator<Vidrio> comparator =
            Comparator.comparing(Vidrio::getEmpresa).thenComparing(Vidrio::getNombre);

    @Override
    public int compareTo(final Vidrio other) {
        return comparator.compare(this, other);
    }

}
