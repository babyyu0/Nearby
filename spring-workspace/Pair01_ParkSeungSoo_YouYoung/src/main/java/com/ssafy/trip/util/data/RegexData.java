package com.ssafy.trip.util.data;

import java.util.HashMap;
import java.util.Map;

public interface RegexData {
    public Map<String, String> regex = new HashMap<>(){{
        put("email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$");
        put("password", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$");
    }};
}
