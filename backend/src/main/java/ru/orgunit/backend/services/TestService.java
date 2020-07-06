package ru.orgunit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orgunit.backend.dto.*;
import ru.orgunit.backend.entities.test.CourseEntity;
import ru.orgunit.backend.entities.test.Specialization;
import ru.orgunit.backend.entities.test.Test;
import ru.orgunit.backend.repositories.test.CourseRepository;
import ru.orgunit.backend.repositories.test.SpecializationRepository;
import ru.orgunit.backend.repositories.test.TestRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TestService {

    private final SpecializationRepository specializationRepository;

    private final CourseRepository courseRepository;

    private final TestRepository testRepository;

    @Autowired
    public TestService(SpecializationRepository specializationRepository, CourseRepository courseRepository, TestRepository testRepository) {
        this.specializationRepository = specializationRepository;
        this.courseRepository = courseRepository;
        this.testRepository = testRepository;
    }

    public List<SpecializationDto> getSpecializationList(LanguageDto languageDto) throws IOException {

        List<Specialization> specList = specializationRepository.findAllByLanguage(languageDto.getLanguage());
        List <SpecializationDto> result = new ArrayList<>();

        for (Specialization specialization : specList) {
            SpecializationDto specializationDto = new SpecializationDto(specialization);
            result.add(specializationDto);
        }

        return result;
    }

    public FullSpecializationDto getFullSpecialization (SimpleWithLanguageDto simpleWithLanguageDto) {

        long specializationId = simpleWithLanguageDto.getSpecializationId();
        Specialization specialization = specializationRepository.findSpecializationById(specializationId);

        List<CourseEntity> courseList = courseRepository.getAllBySpecializationId(specializationId);

        SpecializationDto specializationDto = new SpecializationDto(specialization);
        List<CourseDto> courseDtoList = new ArrayList<>();

        for (CourseEntity courseEntity : courseList) {
            CourseDto courseDto = new CourseDto(courseEntity);
            courseDtoList.add(courseDto);
        }

        FullSpecializationDto fullSpecializationDto = new FullSpecializationDto();
        fullSpecializationDto.setCourseDtoList(courseDtoList);
        fullSpecializationDto.setSpecializationDto(specializationDto);

        return fullSpecializationDto;
    }

    public FullTestDto getFullCourseInfo (SimpleWithLanguageDto simpleWithLanguageDto) {

        long specializationId = simpleWithLanguageDto.getSpecializationId();
        CourseEntity courseEntity = courseRepository.getCourseEntityById(specializationId);

        List<Test> courseList = testRepository.getAllByCourseId(specializationId);

        CourseDto specializationDto = new CourseDto(courseEntity);
        List<TestDto> courseDtoList = new ArrayList<>();

        for (Test testEntity : courseList) {
            TestDto courseDto = new TestDto(testEntity);
            courseDtoList.add(courseDto);
        }

        FullTestDto fullSpecializationDto = new FullTestDto();
        fullSpecializationDto.setCourseDto(specializationDto);
        fullSpecializationDto.setTestDtoList(courseDtoList);

        return fullSpecializationDto;
    }

}
