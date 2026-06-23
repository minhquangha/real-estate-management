package com.javaweb.service.impl;

import com.javaweb.model.BuildingDTO;
import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.DistrictRepository;
import com.javaweb.respository.entity.BuildingEntity;
import com.javaweb.respository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public List<BuildingDTO> findAll(Map<String,Object> params,List<String> typeCode) {
        List<BuildingDTO> results = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params,typeCode);
        for (BuildingEntity it : buildingEntities){
            BuildingDTO buildingDTO =  new BuildingDTO();
            buildingDTO.setName(it.getName());
            DistrictEntity districtEntity = districtRepository.findNameById(it.getDistrictId());
            String districtName = districtEntity.getDistrictName();
            buildingDTO.setAddress(it.getWard()+" "+ it.getStreet()+" "+ districtName);
            buildingDTO.setBrokerageFee(it.getBrokerageFee());
            buildingDTO.setManagerPhoneNumber(it.getManagePhone());
            buildingDTO.setManagerName(it.getManageName());
            buildingDTO.setRentPrice(it.getPriceDescription());
            buildingDTO.setServiceFee(it.getServiceFee());
            results.add(buildingDTO);
        }
        return results;
    }
}
