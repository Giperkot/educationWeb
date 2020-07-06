package ru.orgunit.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

@Entity
@Table(name = "user_token", schema = "public", catalog = "sir",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"session_id"})}
)
public class UserTokenEntity extends BaseEntity {

    @Column(name = "telephone_code")
    private String telephoneCode;

    @Column(name = "session_id", unique = true)
//    @Column(name="session_id",columnDefinition="bpchar")
    private UUID sessionId;

    public UserTokenEntity() {
    }

    public UserTokenEntity(String telephoneCode, UUID sessionId) {
        this.telephoneCode = telephoneCode;
        this.sessionId = sessionId;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }
}
