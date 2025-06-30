package com.thomazcollet.simple_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    public void addCorsMappings(@NonNull CorsRegistry registry){

        registry.addMapping("/**")  
        // Permite CORS para todos os caminhos/endpoints da aplicação (ex: /user, /post, etc.)

        .allowedOrigins("*")
        // Permite que requisições sejam feitas de qualquer origem (domínio). 
        // Ex: http://localhost:3000, https://meusite.com, etc.
        // Usar "*" significa que qualquer frontend (independente do domínio) pode acessar sua API.

        .allowedMethods("*")
        // Permite todos os métodos HTTP: GET, POST, PUT, DELETE, PATCH, OPTIONS...
        // Útil em APIs REST que trabalham com diferentes operações.

        .allowedHeaders("*");
        // Permite que qualquer cabeçalho (header) seja enviado na requisição.
        // Inclui Authorization, Content-Type, Accept, etc.


    }

}
