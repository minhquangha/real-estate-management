package com.javaweb.respository;

import com.javaweb.respository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode);
}
