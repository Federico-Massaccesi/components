package it.personal.Components.security;

import it.personal.Components.UserEntity.Roles;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RegisteredUserDTO {
    Long id;
    String username;
    String email;
    private List<Roles> roles;

    @Builder(setterPrefix = "with")
    public RegisteredUserDTO(Long id, String username, String email, List<Roles> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}