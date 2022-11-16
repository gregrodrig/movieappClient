package es.uah.movieappCliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientePeliculaApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientePeliculaApp.class, args);
    }
    @Bean
    public RestTemplate template() {
        RestTemplate template = new RestTemplate();
        return template;
    }
}
