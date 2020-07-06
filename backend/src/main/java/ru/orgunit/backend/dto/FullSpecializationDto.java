package ru.orgunit.backend.dto;

import java.util.List;

public class FullSpecializationDto {

    private SpecializationDto specializationDto;

    private List<CourseDto> courseDtoList;

    public SpecializationDto getSpecializationDto() {
        return specializationDto;
    }

    public void setSpecializationDto(SpecializationDto specializationDto) {
        this.specializationDto = specializationDto;
    }

    public List<CourseDto> getCourseDtoList() {
        return courseDtoList;
    }

    public void setCourseDtoList(List<CourseDto> courseDtoList) {
        this.courseDtoList = courseDtoList;
    }
}
