package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.data.RegexData;
import com.ssafy.trip.util.exception.member.MemberInvalidException;
import com.ssafy.trip.util.exception.trip.CityInvalidException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class Sido {
    @Id
    @Column(name = "sido_code")
    @Comment("코드")
    private long sidoCode;

    @Column(name = "sido_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("이름")
    private String sidoName;

    public static SidoBuilder builder() {
        return Sido.innerBuilder();
    }

    public SidoBuilder sidoCode(long sidoCode) {
        return innerBuilder().sidoCode(sidoCode);
    }
    public SidoBuilder sidoName(String sidoName) throws CityInvalidException {
        if(sidoName == null || sidoName.trim().equals("")) {
            log.error("Sido: 지역 (시, 도) 입력 실패 " + sidoName);
            throw new CityInvalidException();
        }
        return innerBuilder().sidoName(sidoName);
    }
}
