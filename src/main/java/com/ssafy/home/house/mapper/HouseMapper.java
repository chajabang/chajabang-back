package com.ssafy.home.house.mapper;

import com.ssafy.home.house.dto.House;
import com.ssafy.home.house.dto.HouseDeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {
    List<House> getHouseList(String sido, String gugun, String dong);

    List<String> getDong(String sido, String gugun);

    List<String> getGugun(String sido);

    List<HouseDeal> getHouseDetail(long aptCode);

    List<HouseDeal> getHouseDeals(long aptCode, String floor, String area);

    int checkInterested(String id, long aptCode);

    int addInterested(String id, long aptCode);

    int rmInterested(String id, long aptCode);

    List<House> getInterList(String id);
}
