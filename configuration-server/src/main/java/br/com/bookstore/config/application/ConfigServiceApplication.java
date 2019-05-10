package br.com.bookstore.config.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *  Application de configuração.
 *
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {
 
	/**
	 * Método padrão.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServiceApplication.class).run(args);
    }
 
}
