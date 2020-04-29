package dhbw.online.bookly.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    @Value("${keycloak.auth-server-url}")
    public static String KEYCLOAK_URL;

    @Value("${keycloak.realml}")
    public static String KEYCLOAK_REALM;

    @Value("${admin.keycloak.resource}")
    public static String KEYCLOAK_RESOURCE;

    @Value("${admin.keycloak.credentials}")
    public static String KEYCLOAK_CREDENTIALS;

    @Value("${admin.keycloak.user}")
    public static String KEYCLOAK_USER;

    @Value("${admin.keycloak.user.password}")
    public static String KEYCLOAK_USER_PASSWORD;
}
