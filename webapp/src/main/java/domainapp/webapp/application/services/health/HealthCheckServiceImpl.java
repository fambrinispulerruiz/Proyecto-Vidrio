package domainapp.webapp.application.services.health;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import domainapp.modules.simple.dom.empresa.EmpresaServices;

import org.apache.isis.applib.services.health.Health;
import org.apache.isis.applib.services.health.HealthCheckService;

@Service
@Named("domainapp.HealthCheckServiceImpl")
public class HealthCheckServiceImpl implements HealthCheckService {

    private final EmpresaServices empresas;

    @Inject
    public HealthCheckServiceImpl(EmpresaServices empresas) {
        this.empresas = empresas;
    }

    @Override
    public Health check() {
        try {
        	empresas.ping();
            return Health.ok();
        } catch (Exception ex) {
            return Health.error(ex);
        }
    }
}
