package ru.orgunit.backend.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.orgunit.backend.entities.SpringSessionEntity;

public interface SpringSessionRepository  extends CrudRepository<SpringSessionEntity, Long> {

    SpringSessionEntity getFirstBySessionId(String sesssionId);

}
