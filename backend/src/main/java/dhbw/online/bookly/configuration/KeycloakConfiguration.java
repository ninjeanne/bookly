package dhbw.online.bookly.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KeycloakConfiguration {
    @Value("${keycloak.auth-server-url}")
    public String KEYCLOAK_URL;

    @Value("${keycloak.realml}")
    public String KEYCLOAK_REALM;

    @Value("${admin.keycloak.resource}")
    public String KEYCLOAK_RESOURCE;

    @Value("${admin.keycloak.credentials}")
    public String KEYCLOAK_CREDENTIALS;

    @Value("${admin.keycloak.user}")
    public String KEYCLOAK_USER;

    @Value("${admin.keycloak.user.password}")
    public String KEYCLOAK_USER_PASSWORD;
}
