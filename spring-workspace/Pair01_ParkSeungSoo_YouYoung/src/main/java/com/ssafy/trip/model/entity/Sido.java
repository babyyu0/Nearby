package com.ssafy.trip.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor
public class Sido {
    @Id
    @Column(name = "sido_code")
    @Comment("코드")
    private long sidoCode;

    @Column(name = "sido_name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("이름")
    private String sidoName;

    @Builder
    public Sido(long sidoCode, String sidoName) {
        setSidoCode(sidoCode);
        setSidoName(sidoName);
    }

    public long getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(long sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

}
