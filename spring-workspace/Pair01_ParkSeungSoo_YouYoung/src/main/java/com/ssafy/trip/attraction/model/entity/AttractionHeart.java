package com.ssafy.trip.attraction.model.entity;

import com.ssafy.trip.area.model.entity.Gugun;
import com.ssafy.trip.global.model.entity.Base;
import com.ssafy.trip.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AttractionHeart extends Base {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유 번호")
    @Id int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_code")
    @Comment("관광지 고유 번호")
    private Attraction attraction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    @Comment("회원 고유 번호")
    private Member member;
}
