package com.my.nearby.area.model.dto;

public record AreaCodeDto(
        int rnum,
        int code,
        String name
) {
    public AreaCodeDto(int rnum, int code, String name) {
        this.rnum = rnum;
        this.code = code;
        this.name = name;
    }
}
