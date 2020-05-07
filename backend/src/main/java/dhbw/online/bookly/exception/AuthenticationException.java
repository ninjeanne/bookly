package dhbw.online.bookly.exception;

import lombok.Data;

@Data
public class AuthenticationException extends RuntimeException {
    private String principal;
    public AuthenticationException(String principal, String message) {
        super(message);
        setPrincipal(principal);
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
