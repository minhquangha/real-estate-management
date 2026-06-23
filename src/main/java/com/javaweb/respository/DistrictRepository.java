package com.javaweb.respository;

import com.javaweb.respository.entity.DistrictEntity;

public interface DistrictRepository {
    public DistrictEntity findNameById(Long id);
}
