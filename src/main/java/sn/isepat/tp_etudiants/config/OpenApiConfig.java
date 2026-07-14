package sn.isepat.tp_etudiants.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuration de la documentation Swagger/OpenAPI.
 * L'interface Swagger UI est accessible à l'adresse :
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI etudiantOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Gestion des Étudiants ISEP-AT")
                        .description("API permettant d'ajouter, modifier, supprimer, "
                                + "rechercher et lister les étudiants de l'ISEP-AT.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dr Samba SIDIBE")
                                .email("ssidibe@ept.edu.sn")));
    }

}