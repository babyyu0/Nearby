package com.ssafy.trip.global.data;

public interface RegexPattern {
    String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,20}$";
    String PASSWORD = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,20}$";
    String TEL = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}$";
    String TEL2 = "^[0-9]*$";
}
