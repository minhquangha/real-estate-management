package com.javaweb.respository.impl;

import com.javaweb.respository.DistrictRepository;
import com.javaweb.respository.entity.DistrictEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    String USER = "root";
    String PASSWORD = "123456";
    @Override
    public DistrictEntity findNameById(Long id) {
        String sql = "select name from district where id = " + id+ ";";
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement stmt =  conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                DistrictEntity districtEntity =  new DistrictEntity();
                String name = rs.getString("name");
                districtEntity.setDistrictName(name);
                return districtEntity;
            }
        }
        catch (SQLException se){
            se.printStackTrace();
        }
        return null;
    }
}
