package com.javaweb.respository.impl;

import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    String USER = "root";
    String PASSWORD = "123456";
    @Override
    public List<BuildingEntity> findAll(String name,Long districtId) {
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM building b WHERE 1=1 "
        );

        if (name != null && !name.isEmpty()) {
            sql.append("AND b.name LIKE '%" + name + "%' ");
        }

        if (districtId != null) {
            sql.append("AND b.district_id = " + districtId + " ");
        }
        List<BuildingEntity> results =  new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement stmt =  conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.valueOf(sql))
        ) {
            while (rs.next()) {
                BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("name"));
                building.setStreet(rs.getString("street"));
                building.setWard((rs.getString("ward")));
                building.setNumberOfBasement(String.valueOf(rs.getInt("number_of_basement")));
                results.add(building);
            }

        }
        catch (SQLException se){
            se.printStackTrace();
        }
        return results;
    }
}
