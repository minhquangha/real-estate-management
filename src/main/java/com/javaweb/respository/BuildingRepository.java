package com.javaweb.respository;

import com.javaweb.respository.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepository {
    List<BuildingEntity> findAll(String name,Long districtId);
}
