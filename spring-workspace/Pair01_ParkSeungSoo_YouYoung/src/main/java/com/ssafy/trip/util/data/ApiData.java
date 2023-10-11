package com.ssafy.trip.util.data;

import java.util.HashMap;
import java.util.Map;

public interface ApiData {
    public Map<String, String> apiUrl = new HashMap<>(){{
        put("sido", "https://apis.data.go.kr/B551011/KorService1/areaCode1");
    }};
}
