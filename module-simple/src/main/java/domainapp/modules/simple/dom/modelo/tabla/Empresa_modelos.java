package domainapp.modules.simple.dom.modelo.tabla;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.modelo.ModeloRepository;
import lombok.RequiredArgsConstructor;


@Collection
@CollectionLayout(defaultView = "table")
@RequiredArgsConstructor
public class Empresa_modelos {

	 private final Empresa empresa;

	    public List<Modelo> coll() {
	        return modeloRepository.findByEmpresa(empresa);
	    }

	    @Inject ModeloRepository modeloRepository;
}
