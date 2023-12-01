package com.ssafy.trip.attraction.model.repository.custom;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ssafy.trip.attraction.model.entity.QAttraction.attraction;
import static com.ssafy.trip.attraction.model.entity.QAttractionHeart.attractionHeart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttractionHeartCustomRepositoryImpl implements AttractionHeartCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tuple> findAllByOrderByCountDesc() {
        return queryFactory.select(
                        attraction.as("attraction"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(attractionHeart.member.count())
                                                .from(attractionHeart)
                                                .where(attractionHeart.attraction.eq(attraction)),
                                        "memberCount")
                )
                .from(attraction)
                .orderBy(Expressions.stringPath("memberCount").desc())
                .limit(4)
                .fetch();
    }
}
