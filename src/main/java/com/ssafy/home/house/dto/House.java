package com.ssafy.home.house.dto;

public class House {
    private long aptCode;
    private int bonbun, bubun, roadNameBonbun, roadNameBubun;
    private String dong, roadName, apartmentName, lat, lng;

    public House() {
    }

    public House(long aptCode, int bonbun, int bubun, int roadNameBonbun, int roadNameBubun, String dong,
                 String roadName, String apartmentName, String lat, String lng) {
        setBonbun(bonbun);
        setBubun(bubun);
        setRoadNameBonbun(roadNameBonbun);
        setRoadNameBubun(roadNameBubun);
        setAptCode(aptCode);
        setDong(dong);
        setRoadName(roadName);
        setApartmentName(apartmentName);
        setLat(lat);
        setLng(lng);
    }

    public int getBonbun() {
        return bonbun;
    }

    public void setBonbun(int bonbun) {
        if(bonbun < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.bonbun = bonbun;
        }
    }

    public int getBubun() {
        return bubun;
    }

    public void setBubun(int bubun) {
        if(bubun < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.bubun = bubun;
        }
    }

    public int getRoadNameBonbun() {
        return roadNameBonbun;
    }

    public void setRoadNameBonbun(int roadNameBonbun) {
        if (roadNameBonbun < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.roadNameBonbun = roadNameBonbun;
        }
    }

    public int getRoadNameBubun() {
        return roadNameBubun;
    }

    public void setRoadNameBubun(int roadNameBubun) {
        if (roadNameBubun < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            this.roadNameBubun = roadNameBubun;
        }
    }

    public long getAptCode() {
        return aptCode;
    }

    public void setAptCode(long aptCode) {
        if (aptCode >= 0) {
            this.aptCode = aptCode;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        if (dong != null) {
            this.dong = dong;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        if (roadName != null) {
            this.roadName = roadName;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }

    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        if (apartmentName != null) {
            this.apartmentName = apartmentName;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }

    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        if (lat != null) {
            this.lat = lat;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        if (lng != null) {
            this.lng = lng;
        } else {
            System.out.println("유효하지 않은 값입니다.");
        }
    }

}
