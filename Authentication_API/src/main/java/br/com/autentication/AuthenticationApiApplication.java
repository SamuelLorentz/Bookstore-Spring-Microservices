package br.com.autentication;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.autentication.model.Customer;
import br.com.autentication.repository.CustomerRepository;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class AuthenticationApiApplication extends WebMvcConfigurationSupport implements CommandLineRunner  {

	@Autowired
	CustomerRepository customerRepository;
	
	public static void main(String[] args) {
        SpringApplication.run(AuthenticationApiApplication.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {
		Customer cus1 = new Customer(null, "Samuel", "email@email", "5555555555", "pwd123");
		customerRepository.saveAll(Arrays.asList(cus1));
	}

	 @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("br.com.autentication.controller"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(metaData());
	    }
	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Authentication-API")
	                .description("\"Estudo dirigido 3\"")
	                .version("1.0.0")
	                .build();
	    }
	    
	    @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");
	 
	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
}