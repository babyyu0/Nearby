package com.my.nearby.area.model.vo;

import com.my.nearby.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SidoVo {
    private final int code;
    private final String name;

    public SidoVo(int code, String name) {
        if(code < 0) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "행정구역이 유효하지 않습니다.");
        }
        if(name == null || name.isBlank()) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "행정구역이 유효하지 않습니다.");
        }
        this.code = code;
        this.name = name;
    }
}
