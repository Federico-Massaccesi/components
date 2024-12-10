package it.personal.Components.security;

import it.personal.Components.UserEntity.Roles;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisterUserDTO {
    String username;
    String password;
    List<Roles> roles;
}