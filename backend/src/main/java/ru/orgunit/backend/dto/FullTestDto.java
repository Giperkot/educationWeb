package ru.orgunit.backend.dto;

import java.util.List;

public class FullTestDto {
    private CourseDto courseDto;

    private List<TestDto> testDtoList;

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public List<TestDto> getTestDtoList() {
        return testDtoList;
    }

    public void setTestDtoList(List<TestDto> testDtoList) {
        this.testDtoList = testDtoList;
    }
}
