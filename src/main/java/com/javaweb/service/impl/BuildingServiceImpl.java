package com.javaweb.service.impl;

import com.javaweb.model.BuildingDTO;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.entity.BuildingEntity;
import com.javaweb.respository.impl.BuildingRepositoryImpl;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public List<BuildingDTO> findAll(String name,Long districtId) {
        List<BuildingDTO> results = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(name,districtId);
        for (BuildingEntity item : buildingEntities){
            BuildingDTO buildingDTO =  new BuildingDTO();
            buildingDTO.setName(item.getName());
            buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
            buildingDTO.setAddress(item.getWard() +" "+ item.getStreet());
            results.add(buildingDTO);
        }
        return results;
    }
}
