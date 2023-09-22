package domainapp.modules.simple.dom.vidrio.tabla;


import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import domainapp.modules.simple.dom.vidrio.VidrioRepository;
import lombok.RequiredArgsConstructor;

@Collection
@CollectionLayout(defaultView = "table")
@RequiredArgsConstructor
public class Empresa_vidrios {

	 private final Empresa empresa;

	    public List<Vidrio> coll() {
	        return vidrioRepository.findByEmpresa(empresa);
	    }

	    @Inject VidrioRepository vidrioRepository;
}
