package ru.orgunit.backend.repositories.test;

import org.springframework.data.repository.CrudRepository;
import ru.orgunit.backend.entities.test.CourseEntity;
import ru.orgunit.backend.entities.test.Specialization;

import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {


    List<CourseEntity> getAllBySpecializationId(long specializationId);

    CourseEntity getCourseEntityById(long id);

}
