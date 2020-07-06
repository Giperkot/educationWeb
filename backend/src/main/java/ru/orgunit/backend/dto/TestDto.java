package ru.orgunit.backend.dto;

import ru.orgunit.backend.entities.test.Test;

public class TestDto {

    private long id;

    private String title;

    private String description;

    public TestDto(Test courseEntity) {
        title = courseEntity.getTitle();
        description = courseEntity.getDescription();
        id = courseEntity.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
