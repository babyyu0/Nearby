package com.ssafy.trip.global.util.data;

import java.util.HashMap;
import java.util.Map;

public interface ApiData {
    Map<String, String> apiUrl = new HashMap<>(){{
        put("city", "https://apis.data.go.kr/B551011/KorService1/areaCode1");
    }};
}
