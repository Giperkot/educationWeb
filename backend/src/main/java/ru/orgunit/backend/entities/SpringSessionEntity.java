package ru.orgunit.backend.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "spring_session", schema = "public", catalog = "sir")
public class SpringSessionEntity {

    @Id
    @Column(name = "primary_id", columnDefinition="bpchar")
    private String primaryId;

    @Basic
    @Column(name = "session_id", columnDefinition="bpchar")
    private String sessionId;

    @Basic
    @Column(name = "creation_time", nullable = false)
    private long creationTime;

    @Basic
    @Column(name = "last_access_time", nullable = false)
    private long lastAccessTime;

    @Basic
    @Column(name = "max_inactive_interval", nullable = false)
    private int maxInactiveInterval;

    @Basic
    @Column(name = "expiry_time", nullable = false)
    private long expiryTime;

    @Basic
    @Column(name = "principal_name", nullable = true, length = 100)
    private String principalName;

    @Column(name="user_id")
    private UUID userId;


    public String getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringSessionEntity that = (SpringSessionEntity) o;
        return creationTime == that.creationTime &&
                lastAccessTime == that.lastAccessTime &&
                maxInactiveInterval == that.maxInactiveInterval &&
                expiryTime == that.expiryTime &&
                Objects.equals(primaryId, that.primaryId) &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(principalName, that.principalName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(primaryId, sessionId, creationTime, lastAccessTime, maxInactiveInterval, expiryTime, principalName);
    }
}
