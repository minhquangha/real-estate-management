package com.javaweb.api;
import com.javaweb.model.BuildingDTO;
import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/api/building/")
    public List<BuildingDTO> getBuilding(@RequestParam(name="name",required = false) String name,@RequestParam(name = "district_id",required = false) Long districtId){
        List<BuildingDTO> buildingDTOS = buildingService.findAll(name,districtId);
        return buildingDTOS;
    }

    public void validField(BuildingDTO building){
        if (building.getName().isEmpty() || building.getName() == null || building.getNumberOfBasement() == null){
                throw new  FieldRequiredException("Looix field");
        }
    }


    @DeleteMapping(value = "/api/building/{id}")
    public void deleteBuilding(@PathVariable Integer id){
        System.out.println("Đã xóa " + id);

    }

}
