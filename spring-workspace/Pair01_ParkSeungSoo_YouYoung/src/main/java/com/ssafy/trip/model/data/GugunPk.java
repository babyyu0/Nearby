package com.ssafy.trip.model.data;

import com.ssafy.trip.model.entity.Sido;
import com.ssafy.trip.util.exception.common.CityInvalidException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Embeddable
@Slf4j
public class GugunPk implements Serializable {
    private long gugunCode;
    private Sido sido;

    public long getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(long gugunCode) throws CityInvalidException {
        if(gugunCode <= 0) {
            log.error("Gugun: 구군 코드 받기 실패");
            throw new CityInvalidException();
        }
        this.gugunCode = gugunCode;
    }

    public Sido getSido() {
        return sido;
    }

    public void setSido(Sido sido) {
        this.sido = sido;
    }
}
