package com.javaweb.respository.impl;

import com.javaweb.respository.BuildingRepository;
import com.javaweb.respository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    String USER = "root";
    String PASSWORD = "123456";


    public static void joinTable(Map<String,Object> params, List<String> typeCode,StringBuilder sql){
        String staffId = (String) params.get("staffId");
        if (StringUtil.checkString(staffId)){
            sql.append("  inner join assignment_building on b.id = assignment_building.building_id");
        }
        if(typeCode != null && !typeCode.isEmpty()){
            sql.append(" inner join building_rent_type on b.id = building_rent_type.building_id");
            sql.append(" inner join rent_type on rent_type.id = building_rent_type.rent_type_id");
        }
        String rentAreaTo = (String) params.get("areaTo");
        String rentAreaFrom =(String)  params.get("areaFrom");
        if (rentAreaTo !=null && !rentAreaTo.isEmpty() || rentAreaFrom != null  && !rentAreaFrom.isEmpty()){
            sql.append(" inner join rent_area on  rent_area.building_id = b.id");
        }

    }
    public static void queryNormal(Map<String,Object> params, StringBuilder where){
        for (Map.Entry<String,Object> it : params.entrySet()){
            if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area")
                        && it.getKey().startsWith("rentPrice")){
                String value = it.getValue().toString();
                if(StringUtil.checkString(value)){
                    if(NumberUtil.checkNumber(value)){
                        where.append(" and b."+it.getKey()+ "="+value);
                    }else{
                        where.append(" and b." + it.getKey()+" like '%"+value+"%' ");
                    }
                }
            }
        }
    }
    public static void querySpecial(Map<String,Object> params , List<String> typeCode,StringBuilder where){
        String staffId = (String) params.get("staffId");
        if(StringUtil.checkString(staffId)){
            where.append(" and assignment_building.staff_id = "+ staffId);
        }
        String rentAreaTo = (String) params.get("areaTo");
        String rentAreaFrom = (String) params.get("areaFrom");

        if(StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentAreaTo)== true){
            if(StringUtil.checkString(rentAreaFrom)){
                where.append(" and rent_area.value >="+ rentAreaFrom);
            }
            if (StringUtil.checkString(rentAreaTo)){
                where.append(" and rent_area.value < "+ rentAreaTo);
            }
        }


        String rentPriceTo = (String) params.get("priceTo");
        String rentPriceFrom = (String) params.get("priceFrom");

        if(StringUtil.checkString(rentPriceTo) == true || StringUtil.checkString(rentPriceFrom)== true){
            if(StringUtil.checkString(rentAreaFrom)){
                where.append(" and rent_price.value >="+ rentPriceFrom);
            }
            if (StringUtil.checkString(rentAreaTo)){
                where.append(" and rent_price.value < "+ rentPriceTo);
            }
        }
        if(typeCode!=null && !typeCode.isEmpty()){
            List<String> code =  new ArrayList<>();
            for(String item: typeCode){
                code.add("'"+item+"'");
            }
            where.append(" and rent_type.code in "+ String.join(",",code)+ ")");
        }
    }

    @Override
    public List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode) {
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.district_id,b.street,b.ward,b.number_of_basement," +
                "b.rent_price,b.manager_name,b.manager_phone_number,b.service_fee,b.brokerage_fee,b.floor_area from building b ");
        joinTable(params,typeCode,sql);
        StringBuilder where =  new StringBuilder(" where 1=1 ");
        queryNormal(params,sql);
        querySpecial(params,typeCode,where);
        sql.append(where);
        System.out.println(sql);



        List<BuildingEntity> results =  new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            Statement stmt =  conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.valueOf(sql))

        ) {
            while (rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getInt("id"));
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setDistrictId(rs.getLong("district_id"));
                buildingEntity.setNumberOfBasement(rs.getInt("number_of_basement"));
                buildingEntity.setFloorArea(rs.getInt("floor_area"));
                buildingEntity.setPrice(rs.getInt("rent_price"));
                buildingEntity.setServiceFee(rs.getString("service_fee"));
                buildingEntity.setBrokerageFee(rs.getString("brokerage_fee"));
                buildingEntity.setManageName(rs.getString("manager_name"));
                buildingEntity.setManagePhone(rs.getString("manager_phone_number"));
                results.add(buildingEntity);

                results.add(buildingEntity);
            }

        }
        catch (SQLException se){
            se.printStackTrace();
        }
        return results;
    }
}
