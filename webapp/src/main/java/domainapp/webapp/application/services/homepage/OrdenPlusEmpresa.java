package domainapp.webapp.application.services.homepage;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Projecting;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;


import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Aseguradora;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Estado;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.Propio;
import domainapp.modules.simple.dom.ordenes_trabajo.enumeradores.TraeOrden;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DomainObject(nature=Nature.VIEW_MODEL, logicalTypeName = "simple.OrdenPlusEmpresa")
@DomainObjectLayout(named = "OrdenDeTrabajo")
@XmlRootElement
@NoArgsConstructor
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdenPlusEmpresa {

    @Property(
            projecting = Projecting.PROJECTED,
            hidden = Where.EVERYWHERE
    )
    @Getter
    private OrdenDeTrabajo ordenT;

    OrdenPlusEmpresa(OrdenDeTrabajo ordenT) {this.ordenT = ordenT;}

    public Vidrio getVidrio() {return ordenT.getVidrio();}
    public LocalDateTime getFecha() {return ordenT.getFecha();}
    public String getNombreAsegurado() {return ordenT.getNombreAsegurado();}
    public String getTelefonoAsegurado() {return ordenT.getTelefonoAsegurado();}
    public Aseguradora getAseguradora() {return ordenT.getAseguradora();}
    public int getNroSiniestro() {return ordenT.getNroSiniestro();}
    public TraeOrden getOrden() {return ordenT.getOrden();}
    public String getPatente() {return ordenT.getPatente();}
    public Propio getPropio() {return ordenT.getPropio();}
    public String getObservaciones() {return ordenT.getObservaciones();}
    public Estado getEstado() {return ordenT.getEstado();}

    public Empresa getEmpresa() {
        return getVidrio().getEmpresa();
    }
}
