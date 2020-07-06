package ru.orgunit.backend.dto;

import ru.orgunit.backend.entities.test.Specialization;

public class SpecializationDto {

    private long id;

    private String title;

    private String description;

    private Boolean state;

    private int timing;

    private String language;

    public SpecializationDto (Specialization specialization) {
        this.setDescription(specialization.getDescription());
        this.setLanguage(specialization.getLanguage());
        this.setTiming(specialization.getTiming());
        this.setTitle(specialization.getTitle());
        this.setState(false);
        id = specialization.getId();
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
