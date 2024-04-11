package com.my.nearby.area.model.vo;

import com.my.nearby.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GugunVo {
    private final int code;
    private final int sidoCode;
    private final String name;

    public GugunVo(int code, int sidoCode, String name) {
        if(code < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "하위 행정구역이 유효하지 않습니다.");
        }
        if(sidoCode < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "하위 행정구역이 유효하지 않습니다.");
        }
        if(name == null || name.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "하위 행정구역이 유효하지 않습니다.");
        }
        this.code = code;
        this.sidoCode = sidoCode;
        this.name = name;
    }
}
