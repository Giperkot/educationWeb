package ru.orgunit.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.orgunit.backend.dto.*;
import ru.orgunit.backend.services.TestService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController()
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/api/private/test/getSpecializationList")
    public List<SpecializationDto> getSpecializationList(@RequestBody LanguageDto languageDto) throws IOException {
        return testService.getSpecializationList(languageDto);
    }

    @PostMapping("/api/private/test/getFullSpecialization")
    public FullSpecializationDto getFullSpecialization(@RequestBody SimpleWithLanguageDto simpleWithLanguageDto ) throws IOException {
        return testService.getFullSpecialization(simpleWithLanguageDto);
    }

    @PostMapping("/api/private/test/getFullCourseInfo")
    public FullTestDto getFullCourseInfo(@RequestBody SimpleWithLanguageDto simpleWithLanguageDto) throws IOException {
        return testService.getFullCourseInfo(simpleWithLanguageDto);
    }


}
