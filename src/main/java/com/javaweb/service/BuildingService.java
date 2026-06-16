package com.javaweb.service;

import com.javaweb.model.BuildingDTO;

import java.util.List;

public interface BuildingService {
    public List<BuildingDTO> findAll(String name,Long districtId);
}
