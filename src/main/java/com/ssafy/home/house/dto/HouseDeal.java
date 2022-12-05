package com.ssafy.home.house.dto;

public class HouseDeal {
    private long no, aptCode;
    private int dealYear, dealMonth, dealDay;
    private String dealAmount, area, floor;

    public HouseDeal() {
    }

    public HouseDeal(long no, long aptCode, int dealYear, int dealMonth, int dealDay, String dealAmount, String area, String floor) {
        setNo(no);
        setAptCode(aptCode);
        setDealYear(dealYear);
        setDealMonth(dealMonth);
        setDealDay(dealDay);
        setDealAmount(dealAmount);
        setArea(area);
        setFloor(floor);
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        if (no < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.no = no;
        }
    }

    public long getAptCode() {
        return aptCode;
    }

    public void setAptCode(long aptCode) {
        if (aptCode < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.aptCode = aptCode;
        }
    }

    public int getDealYear() {
        return dealYear;
    }

    public void setDealYear(int dealYear) {
        if (dealYear < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.dealYear = dealYear;
        }
    }

    public int getDealMonth() {
        return dealMonth;
    }

    public void setDealMonth(int dealMonth) {
        if (dealMonth < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.dealMonth = dealMonth;
        }
    }

    public int getDealDay() {
        return dealDay;
    }

    public void setDealDay(int dealDay) {
        if (dealDay < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.dealDay = dealDay;
        }
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        if (dealAmount != null) {
            this.dealAmount = dealAmount;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        if (area != null) {
            this.area = area;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        if (floor != null) {
            this.floor = floor;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

}
