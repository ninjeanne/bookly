package dhbw.online.bookly.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(description = "Class representing a user tracked by the application.")
public class User implements UserDetails {
    @Id
    @ApiModelProperty(notes = "The username is extracted by the authentication\n" +
            "Unique identifier of the user to log in. No two users can have the same username. " +
            "The username can't be changed once an account has been created. " +
            "The username is extracted by the authentication",
            example = "maxmustermann",
            position = 0)
    private String username;

    @ApiModelProperty(notes = "mail address of the user.",
            example = "max@bookly.online",
            position = 1)
    private String mail;

    @ApiModelProperty(notes = "first name of the user",
            example = "max",
            position = 2)
    private String first_name;

    @ApiModelProperty(notes = "last name of the user.",
            example = "mustermann",
            position = 3)
    private String last_name;

    @JsonIgnore
    private String password;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
