package ru.orgunit.backend.repositories.test;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.orgunit.backend.entities.UsersEntity;
import ru.orgunit.backend.entities.test.Specialization;

import java.util.List;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long> {

//    @Query(value = "select * FROM specialization where lang = cast(?1 as text) limit 20", nativeQuery = true)
    List<Specialization> findAllByLanguage(String language);

    Specialization findSpecializationById(long id);

}
