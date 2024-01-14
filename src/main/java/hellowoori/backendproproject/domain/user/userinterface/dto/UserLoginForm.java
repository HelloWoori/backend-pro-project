package hellowoori.backendproproject.domain.user.userinterface.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserLoginForm {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
