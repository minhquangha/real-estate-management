package com.javaweb.api;
import com.javaweb.model.BuildingDTO;
import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/api/building/")
    public List<BuildingDTO> getBuilding(@RequestParam  Map<String,Object> params,
                                         @RequestParam(name="typeCode",required = false) List<String> typeCode){

        List<BuildingDTO> buildingDTOS = buildingService.findAll(params,typeCode);
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
