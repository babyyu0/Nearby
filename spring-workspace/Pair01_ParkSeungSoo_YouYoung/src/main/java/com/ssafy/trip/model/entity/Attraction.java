package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.exception.trip.AttractionInvalidException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class Attraction {

    @Column(name = "id", nullable = false)
    @Comment("콘텐츠 번호")
    @Id private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_type_id", nullable = false)
    @Comment("관광 타입")
        private ContentType contentType;

    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(100) CHARACTER SET UTF8")
    @Comment("이름")
    private String title;

    @Column(name = "addr1", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("주소")
    private String addr1;

    @Column(name = "addr2", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET UTF8")
    @Comment("상세 주소")
    private String addr2;

    @Column(name = "tel", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("전화번호")
    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "gugun_code", nullable = false, columnDefinition = "BIGINT COMMENT '구군 코드'"),
            @JoinColumn(name = "sido_code", nullable = false, columnDefinition = "BIGINT COMMENT '시도 코드'"),
    })
    private Gugun gugun;

    @Column(name = "created_at", nullable = false)
    @Comment("생성 시간")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment("수정 시간")
    private LocalDateTime updatedAt;

    @Column(name = "map_x", nullable = false)
    @Comment("x 좌표")
    private double mapX;

    @Column(name = "map_y", nullable = false)
    @Comment("y 좌표")
    private double mapY;

    public AttractionBuilder builder() {
        return Attraction.innerBuilder();
    }

    public AttractionBuilder id(long id) throws AttractionInvalidException {
        if(id < 0) {
            log.error("Attraction: 고유번호 입력 실패 " + id);
            throw new AttractionInvalidException();
        }
        return innerBuilder().id(id);
    }

    public AttractionBuilder contentType(ContentType contentType) {
        return innerBuilder().contentType(contentType);
    }

    public AttractionBuilder title(String title) throws AttractionInvalidException {
        if(title == null || title.trim().equals("") || 100 < title.length()) {
            log.error("Attraction: 제목 입력 실패 " + title);
            throw new AttractionInvalidException();
        }
        return innerBuilder().title(title);
    }

    public AttractionBuilder addr1(String addr1) throws AttractionInvalidException {
        if(addr1 == null || addr1.trim().equals("")) {
            log.error("Attraction: 주소 입력 실패 " + addr1);
            throw new AttractionInvalidException();
        }
        return innerBuilder().addr1(addr1);
    }
    public AttractionBuilder addr2(String addr2) throws AttractionInvalidException {
        if(addr2 == null) {
            log.error("Attraction: 상세 주소 입력 실패 " + addr2);
            throw new AttractionInvalidException();
        }
        return innerBuilder().addr2(addr2);
    }

    public AttractionBuilder tel(String tel) throws AttractionInvalidException {
        if(tel == null || tel.trim().equals("")) {
            log.error("Attraction: 주소 입력 실패 " + tel);
            throw new AttractionInvalidException();
        }
        return innerBuilder().tel(tel);
    }

    public AttractionBuilder gugun(Gugun gugun) {
        return innerBuilder().gugun(gugun);
    }

    public AttractionBuilder createdAt(LocalDateTime createdAt) {
        return innerBuilder().createdAt(createdAt);
    }

    public AttractionBuilder updatedAt(LocalDateTime updatedAt) {
        return innerBuilder().updatedAt(updatedAt);
    }

    public AttractionBuilder mapX(double mapX) throws AttractionInvalidException {
        if(mapX < 0) {
            log.error("Attraction: X 좌표 입력 실패 " + mapX);
            throw new AttractionInvalidException();
        }
        return innerBuilder().mapX(mapX);
    }

    public AttractionBuilder mapY(double mapY) throws AttractionInvalidException {
        if(mapY < 0) {
            log.error("Attraction: Y 좌표 입력 실패 " + mapY);
            throw new AttractionInvalidException();
        }
        return innerBuilder().mapY(mapY);
    }
}
