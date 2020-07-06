package ru.orgunit.backend.dto;

import org.springframework.lang.Nullable;
import ru.orgunit.backend.entities.UsersEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull
    @Size(min = 6)
    private String login;

    @NotNull
    @Size(min = 6)
    private String password;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String middleName;

    public UserDto() {
    }

    public UserDto (UsersEntity user) {
        login = user.getLogin();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        middleName = user.getMiddleName();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
