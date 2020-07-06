package ru.orgunit.backend.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
public class UsersEntity extends BaseEntity {

    @Basic
    @Column(name = "login", nullable = true, length = 255)
    private String login;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "salt", nullable = false, length = 255)
    private String salt;

    @Basic
    @Column(name = "first_name", nullable = true, length = 255)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    private String lastName;

    @Basic
    @Column(name = "middle_name", nullable = true, length = 255)
    private String middleName;



    public UsersEntity() {
    }

    public UsersEntity(String login) {
        this.login = login;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return  Objects.equals(login, that.login) &&
//                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, firstName, lastName, middleName);
    }
}
