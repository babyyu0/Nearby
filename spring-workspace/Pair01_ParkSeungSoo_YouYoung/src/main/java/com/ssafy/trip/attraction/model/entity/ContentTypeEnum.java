package com.ssafy.trip.attraction.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ContentTypeEnum {
    TYPE_ATTRACTION(12, "관광지"),
    TYPE_CULTURE(14, "문화시설"),
    TYPE_FESTIVAL(15, "축제공연행사"),
    TYPE_LEPORTS(28, "레포츠"),
    TYPE_STAY(32, "숙박"),
    TYPE_SHOP(38, "쇼핑"),
    TYPE_RESTAURANT(39, "음식점");

    private final int code;
    private final String name;
}
