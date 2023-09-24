package domainapp.webapp.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import domainapp.modules.simple.OrdenesModule;

@Configuration
@Import(OrdenesModule.class)
@ComponentScan
public class ApplicationModule {

}
