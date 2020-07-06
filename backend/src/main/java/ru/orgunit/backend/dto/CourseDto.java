package ru.orgunit.backend.dto;

import ru.orgunit.backend.entities.test.CourseEntity;
import ru.orgunit.backend.entities.test.Specialization;

public class CourseDto {

    private long id;

    private String title;

    private String description;

    private int timing;

    public CourseDto(CourseEntity courseEntity) {
        title = courseEntity.getTitle();
        description = courseEntity.getDescription();
        timing = (int)(Math.random() * 4);
        id = courseEntity.getId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
