package ru.orgunit.backend.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.orgunit.backend.entities.EntityStatus;
import ru.orgunit.backend.entities.UsersEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UsersEntity, Long> {

    List<UsersEntity> findByLastName(String lastName);
    UsersEntity findFirstByFirstNameAndLastNameAndCreateTimeBefore(String firstName, String lastName, LocalDateTime createTime);

    UsersEntity findByLoginAndStatus(String login, EntityStatus status);

}
