package dhbw.online.bookly.controller;

import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/customers")
    public String customers() {

        System.out.println(((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKeycloakSecurityContext().getIdToken());
        System.out.println(((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName());
        /*KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = ((KeycloakPrincipal) authenticationToken.getPrincipal());
        IDToken idToken = kcprincipal.getKeycloakSecurityContext().getIdToken();
        AccessToken accessToken = kcprincipal.getKeycloakSecurityContext().getToken();

        System.out.println("User logged in: " + idToken.getPreferredUsername());*/

        return "{ \"test\": \"hi\"}";
    }

}
