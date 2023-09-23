package domainapp.modules.simple.dom.ordenes_trabajo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Aseguradora;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Propio;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.TraeOrden;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.types.Notas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
    schema="simple",
    name = "OrdenDeTrabajo",
    uniqueConstraints = {
        @UniqueConstraint(name = "OrdenDeTrabajo__vidrio_fecha__UNQ", columnNames = {"empresa_id", "nombre"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "simple.OrdenDeTrabajo", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class OrdenDeTrabajo implements Comparable<OrdenDeTrabajo> {

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


    public OrdenDeTrabajo(Vidrio vidrio, LocalDateTime fecha, String nombreAsegurado, String telefonoAsegurado, Aseguradora aseguradora, int nroSiniestro, TraeOrden orden, String patente, Propio propio, String observaciones, Estado estado) {
        this.vidrio = vidrio;
        this.fecha = fecha;
        this.nombreAsegurado = nombreAsegurado;
        this.telefonoAsegurado = telefonoAsegurado;
        this.aseguradora = aseguradora;
        this.nroSiniestro = nroSiniestro;
        this.orden = orden;
        this.patente = patente;
        this.propio = propio;
        this.observaciones = observaciones;
        this.estado = estado;
    }


    public String title() {
        return titleService.titleOf(getVidrio()) + " @ " + getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "vidrio_id")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private Vidrio vidrio;

    @Column(name = "fecha", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    private LocalDateTime fecha;
    
    @Column(name = "nombre_asegurado", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "3")
    private String nombreAsegurado;
    
    @Column(name = "telefono_asegurado", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "4")
    private String telefonoAsegurado;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "aseguradora", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "5")
    private Aseguradora aseguradora;
    
    @Column(name = "nro_siniestro", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "6")
    private int nroSiniestro;
    
    @Column(name = "patente", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "6")
    private String patente;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "orden", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "6")
    private TraeOrden orden;

    @Enumerated(EnumType.STRING)
    @Column(name = "propio", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "6")
    private Propio propio;
    
    @Notas
    @Column(name = "observaciones", length = Notas.MAX_LEN, nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "details", sequence = "7")
    private String observaciones;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "6")
    private Estado estado;

    private final static Comparator<OrdenDeTrabajo> comparator =
            Comparator.comparing(OrdenDeTrabajo::getVidrio).thenComparing(OrdenDeTrabajo::getFecha);

    @Override
    public int compareTo(final OrdenDeTrabajo other) {
        return comparator.compare(this, other);
    }

    @Inject @Transient TitleService titleService;
}
