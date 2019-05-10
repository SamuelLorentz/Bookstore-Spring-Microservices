package br.com.bookstore.config.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *  Application de configura��o.
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {
 
	/**
	 * M�todo padr�o.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServiceApplication.class).run(args);
    }
 
}
