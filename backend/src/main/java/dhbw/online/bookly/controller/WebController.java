package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/customers")
    public User customers() {

        System.out.println("ID Token:" + ((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKeycloakSecurityContext().getIdToken());
        System.out.println("Name from Authentication: " + ((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName());
        /*KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = ((KeycloakPrincipal) authenticationToken.getPrincipal());
        IDToken idToken = kcprincipal.getKeycloakSecurityContext().getIdToken();
        AccessToken accessToken = kcprincipal.getKeycloakSecurityContext().getToken();

        System.out.println("User logged in: " + idToken.getPreferredUsername());*/
        return userService.getUser();
    }

}
