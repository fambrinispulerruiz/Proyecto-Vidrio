package domainapp.modules.simple.dom.vidrio;

import java.util.Comparator;

import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import static org.apache.isis.applib.annotation.SemanticsOf.IDEMPOTENT;
import static org.apache.isis.applib.annotation.SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import domainapp.modules.simple.types.Name;
import domainapp.modules.simple.types.Notes;


@javax.persistence.Entity
@javax.persistence.Table(
    schema="simple",
    uniqueConstraints = {
        @javax.persistence.UniqueConstraint(name = "Vidrio__name__UNQ", columnNames = {"NAME"})
    }
)
@javax.persistence.NamedQueries({
        @javax.persistence.NamedQuery(
                name = Vidrio.NAMED_QUERY__FIND_BY_NAME_LIKE,
                query = "SELECT so " +
                        "FROM Vidrio so " +
                        "WHERE so.name LIKE :name"
        )
})
@javax.persistence.EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "simple.Vidrio", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Vidrio implements Comparable<Vidrio> {

    static final String NAMED_QUERY__FIND_BY_NAME_LIKE = "Vidrio.findByNameLike";

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(name = "id", nullable = false)
    private Long id;

    @javax.persistence.Version
    @javax.persistence.Column(name = "version", nullable = false)
    @PropertyLayout(fieldSetId = "metadata", sequence = "999")
    @Getter @Setter
    private long version;

    public static Vidrio withName(final String name, final int codigo, final double precio) {
        val vidrio = new Vidrio();
        vidrio.setName(name);
        vidrio.setCodigo(codigo);
        vidrio.setPrecio(precio);
        return vidrio;
    }

    @Inject @javax.persistence.Transient RepositoryService repositoryService;
    @Inject @javax.persistence.Transient TitleService titleService;
    @Inject @javax.persistence.Transient MessageService messageService;



    @Title
    @Name
    @javax.persistence.Column(length = Name.MAX_LEN, nullable = false)
    @Getter @Setter @ToString.Include
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    private String name;
    
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    private int codigo;
    

    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "3")
    private double precio;

    @Notes
    @javax.persistence.Column(length = Notes.MAX_LEN, nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "name", sequence = "4")
    private String notes;


    @Action(semantics = IDEMPOTENT, commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @ActionLayout(associateWith = "name", promptStyle = PromptStyle.INLINE)
    public Vidrio updateName(
            @Name final String name) {
        setName(name);
        return this;
    }
    public String default0UpdateName() {
        return getName();
    }
    public String validate0UpdateName(String newName) {
        for (char prohibitedCharacter : "&%$!".toCharArray()) {
            if( newName.contains(""+prohibitedCharacter)) {
                return "Character '" + prohibitedCharacter + "' is not allowed.";
            }
        }
        return null;
    }


    @Action(semantics = NON_IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(
            associateWith = "name", position = ActionLayout.Position.PANEL,
            describedAs = "Deletes this object from the persistent datastore")
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }



    private final static Comparator<Vidrio> comparator =
            Comparator.comparing(Vidrio::getName);

    @Override
    public int compareTo(final Vidrio other) {
        return comparator.compare(this, other);
    }

}
