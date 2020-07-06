package ru.orgunit.backend.dto;

import java.util.UUID;

public class ServiceComboboxDto extends DataObject {

    private UUID id;
    private String serviceName;

    public ServiceComboboxDto() {
    }

    public ServiceComboboxDto(UUID id, String serviceName) {
        this.id = id;
        this.serviceName = serviceName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
