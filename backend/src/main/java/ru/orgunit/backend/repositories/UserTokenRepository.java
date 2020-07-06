package ru.orgunit.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.orgunit.backend.entities.EntityStatus;
import ru.orgunit.backend.entities.UserTokenEntity;

import java.util.UUID;

public interface UserTokenRepository extends CrudRepository<UserTokenEntity, Long> {

    public UserTokenEntity getBySessionIdAndStatus(UUID sessionId, EntityStatus status);

}
