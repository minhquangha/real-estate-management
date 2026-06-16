package com.javaweb.respository.entity;

public class BuildingEntity {
    private String name;
    private String numberOfBasement;
    private String street ;
    private String ward;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(String numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }
}
