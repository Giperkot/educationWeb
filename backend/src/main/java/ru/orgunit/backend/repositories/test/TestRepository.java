package ru.orgunit.backend.repositories.test;

import org.springframework.data.repository.CrudRepository;
import ru.orgunit.backend.entities.test.CourseEntity;
import ru.orgunit.backend.entities.test.Test;

import java.util.List;

public interface TestRepository  extends CrudRepository<Test, Long> {


    List<Test> getAllByCourseId(long id);


}
