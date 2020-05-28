package dhbw.online.bookly.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KeycloakConfiguration {
    @Value("${keycloak.auth-server-url}")
    public String keycloakUrl;

    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Value("${admin.keycloak.resource}")
    public String keycloakResource;

    @Value("${admin.keycloak.credentials}")
    public String keycloakCredentials;

    @Value("${admin.keycloak.user}")
    public String keycloakUser;

    @Value("${admin.keycloak.user.password}")
    public String keycloakUserPassword;
}
