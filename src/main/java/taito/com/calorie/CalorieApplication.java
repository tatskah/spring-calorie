package taito.com.calorie;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = CalorieApplication.class)
public class CalorieApplication extends SpringBootServletInitializer {
	
	private static final Logger logger = Logger.getLogger(CalorieApplication.class);
	
	public static void main(String[] args) {
		System.out.println(org.hibernate.Version.getVersionString());
		
		BasicConfigurator.configure();
		
		SpringApplication.run(CalorieApplication.class, args);

	}

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		String configLocation = System.getProperty("global.appconf.dir");

	    String configPath = configLocation + File.separator + "springApplication"  + File.separator + "application.yml"; //set the configpath of this application instance exclusively

	    logger.info("Configpath: " + configPath);
	    logger.info("Starting to run Spring boot app...");

	    if(configLocation != null && !configLocation.isEmpty()) {
	    Properties props = new Properties();
	    props.setProperty("spring.config.location", configPath);
	    builder.application().setDefaultProperties(props);
	    }else{
	    	logger.info("No global.appconf.dir property found, starting with default on classpath");
	    }
		
		return builder.sources(CalorieApplication.class);
	}

	
}
