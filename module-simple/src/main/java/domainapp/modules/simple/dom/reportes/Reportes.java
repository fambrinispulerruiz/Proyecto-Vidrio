package domainapp.modules.simple.dom.reportes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.isis.applib.value.Blob;

import domainapp.modules.simple.dom.empresa.Empresa;
import domainapp.modules.simple.dom.modelo.Modelo;
import domainapp.modules.simple.dom.ordenes_trabajo.OrdenDeTrabajo;
import domainapp.modules.simple.dom.vidrio.Vidrio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reportes {
	
	public Blob ListadoEmpresasPDF(List<Empresa> empresas) throws JRException, IOException {
		
		List<RepoEmpresa> repoEmpresas = new ArrayList<RepoEmpresa>();
        repoEmpresas.add(new RepoEmpresa());

	        for (Empresa empresa : empresas) {
	            RepoEmpresa repoEmpresa = new RepoEmpresa(empresa.getNombre(),empresa.getDomicilio(),empresa.getTelefono(),empresa.getCorreo(), empresa.getTipoEmpresa());
	            repoEmpresas.add(repoEmpresa);
	        }
	
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoEmpresas);
	        return GenerarArchivoPDF("ReporteEmpresa.jrxml", "ListadoEmpresas.pdf", ds);
	    }

	public Blob ListadoEmpresasPDF() throws JRException, IOException {

        return GenerarArchivoPDF("ReporteEmpresa.jrxml", "ListadoEmpresas.pdf", null);
    }
	
	public Blob ListadoModelosPDF(List<Modelo> modelos) throws JRException, IOException {
		
		List<RepoModelo> repoModelos = new ArrayList<RepoModelo>();
        repoModelos.add(new RepoModelo());

	        for (Modelo modelo : modelos) {
	            RepoModelo repoModelo = new RepoModelo(modelo.getNombre(),modelo.getEmpresa());
	            repoModelos.add(repoModelo);
	        }
	
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoModelos);
	        return GenerarArchivoPDF("ReporteModelo.jrxml", "ListadoModelos.pdf", ds);
	    }

	public Blob ListadoModelosPDF() throws JRException, IOException {

        return GenerarArchivoPDF("ReporteModelo.jrxml", "ListadoModelos.pdf", null);
    }
	
	public Blob ListadoOrdenesDeTrabajoPDF(List<OrdenDeTrabajo> OrdenesDeTrabajo) throws JRException, IOException {
		
		List<RepoOrdenes_Trabajo> repoOrdenesDeTrabajo = new ArrayList<RepoOrdenes_Trabajo>();
		repoOrdenesDeTrabajo.add(new RepoOrdenes_Trabajo());

	        for (OrdenDeTrabajo ordendetrabajo: OrdenesDeTrabajo) {
	        	RepoOrdenes_Trabajo repoOrdenDeTrabajo = new RepoOrdenes_Trabajo(ordendetrabajo.getVidrio(),ordendetrabajo.getFecha(),ordendetrabajo.getNombreAsegurado(),ordendetrabajo.getTelefonoAsegurado(),ordendetrabajo.getAseguradora(),ordendetrabajo.getNroSiniestro(),ordendetrabajo.getPatente(),ordendetrabajo.getOrden(),ordendetrabajo.getPropio(),ordendetrabajo.getObservaciones(),ordendetrabajo.getEstado());
	        	repoOrdenesDeTrabajo.add(repoOrdenDeTrabajo);
	        }
	
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoOrdenesDeTrabajo);
	        return GenerarArchivoPDF("ReporteOrdenes_Trabajo.jrxml", "ListadoOrdenesDeTrabajo.pdf", ds);
	    }

	public Blob ListadoOrdenesDeTrabajoPDF() throws JRException, IOException {

        return GenerarArchivoPDF("ReporteOrdenes_Trabajo.jrxml", "ListadoOrdenesDeTrabajo.pdf", null);
    }
	
	public Blob ListadoVidriosPDF(List<Vidrio> vidrios) throws JRException, IOException {
		
		List<RepoVidrio> repoVidrios = new ArrayList<RepoVidrio>();
        repoVidrios.add(new RepoVidrio());

	        for (Vidrio vidrio : vidrios) {
	            RepoVidrio repoVidrio = new RepoVidrio(vidrio.getNombre(),vidrio.getCodigo(),vidrio.getModelo(),vidrio.getPrecio(),vidrio.getTipoVidrio(),vidrio.getAntena(),vidrio.getSensor());
	            repoVidrios.add(repoVidrio);
	        }
	
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(repoVidrios);
	        return GenerarArchivoPDF("ReporteVidrio.jrxml", "ListadoVidrios.pdf", ds);
	    }

	public Blob ListadoVidriosPDF() throws JRException, IOException {

        return GenerarArchivoPDF("ReporteVidrio.jrxml", "ListadoVidrios.pdf", null);
    }
	
	private Blob GenerarArchivoPDF(String archivoDesing, String nombreSalida, JRBeanCollectionDataSource ds) throws JRException, IOException{

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivoDesing);
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ds", ds);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
        byte[] contentBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return new Blob(nombreSalida, "application/pdf", contentBytes);
    }

}
