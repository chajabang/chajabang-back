package com.ssafy.home.house.controller;

import com.ssafy.home.board.controller.BoardController;
import com.ssafy.home.house.dto.House;
import com.ssafy.home.house.dto.HouseDeal;
import com.ssafy.home.house.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/house")
public class HouseController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    HouseService houseService;

    @GetMapping("/houselist")
    public ResponseEntity<?> getHouseList(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun, @RequestParam("dong") String dong) {
        logger.info("House getHouseList 호출");
        List<House> list = houseService.getHouseList(sido, gugun, dong);
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("아파트 매매정보 불러오기 실패", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dong")
    public ResponseEntity<?> getDong(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun) {
        logger.info("House getDong 호출");
        List<String> list = houseService.getDong(sido, gugun);
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("동 목록 불러오기 실패", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gugun")
    public ResponseEntity<?> getGugun(@RequestParam("sido") String sido) {
        logger.info("House getGugun 호출");
        List<String> list = houseService.getGugun(sido);
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("구군 목록 불러오기 실패", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetail(@RequestParam("aptCode") long aptCode) {
        logger.info("House getDetail 호출");
        List<HouseDeal> list = houseService.getHouseDetail(aptCode);
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("상세정보 불러오기 실패", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deal")
    public ResponseEntity<?> getDeals(@RequestParam("aptCode") long aptCode, @RequestParam("floor") String floor, @RequestParam("area") String area) {
        logger.info("House getDeals 호출");
        List<HouseDeal> list = houseService.getHouseDeals(aptCode, floor, area);
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("가격정보 불러오기 실패", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/checkinter")
    public ResponseEntity<?> checkInterested(@RequestParam("id") String id, @RequestParam("aptCode") long aptCode) {
        logger.info("House checkInterested 호출");
        int count = -1;
        count = houseService.checkInterested(id, aptCode);
        if(count > 0) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } else if(count == 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/addinter")
    public ResponseEntity<?> addInterested(@RequestParam("id") String id, @RequestParam("aptCode") long aptCode) {
        logger.info("House addInterested 호출");
        int count = -1;
        count = houseService.addInterested(id, aptCode);
        if(count > 0) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } else if(count == 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/rminter")
    public ResponseEntity<?> rmInterested(@RequestParam("id") String id, @RequestParam("aptCode") long aptCode) {
        logger.info("House rmInterested 호출");
        int count = -1;
        count = houseService.rmInterested(id, aptCode);
        if(count > 0) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } else if(count == 0) {
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/interestlist")
    public ResponseEntity<?> getInterList(@RequestParam("id") String id) {
        logger.info("House getInterList 호출");
        List<House> list = houseService.getInterList(id);
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("관심 매물정보 불러오기 실패", HttpStatus.NOT_FOUND);
        }
    }

}
