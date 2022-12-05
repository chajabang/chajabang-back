package com.ssafy.home.house.service;

import com.ssafy.home.house.dto.House;
import com.ssafy.home.house.dto.HouseDeal;
import com.ssafy.home.house.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    HouseMapper houseMapper;

    public List<House> getHouseList(String sido, String gugun, String dong) {
        return houseMapper.getHouseList(sido, gugun, dong);
    }

    public List<String> getDong(String sido, String gugun) {
        return houseMapper.getDong(sido, gugun);
    }

    public List<String> getGugun(String sido) {
        return houseMapper.getGugun(sido);
    }

    public List<HouseDeal> getHouseDetail(long aptCode) {
        return houseMapper.getHouseDetail(aptCode);
    }

    public List<HouseDeal> getHouseDeals(long aptCode, String floor, String area) {
        return houseMapper.getHouseDeals(aptCode, floor, area);
    }

    public int checkInterested(String id, long aptCode) {
        return houseMapper.checkInterested(id, aptCode);
    }

    public int addInterested(String id, long aptCode) {
        return houseMapper.addInterested(id, aptCode);
    }

    public int rmInterested(String id, long aptCode) {
        return houseMapper.rmInterested(id, aptCode);
    }

    public List<House> getInterList(String id) {
        return houseMapper.getInterList(id);
    }
}
