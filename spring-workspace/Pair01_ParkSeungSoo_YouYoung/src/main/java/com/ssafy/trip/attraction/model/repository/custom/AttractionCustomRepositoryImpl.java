package com.ssafy.trip.attraction.model.repository.custom;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.trip.attraction.model.entity.QAttraction.attraction;
import static com.ssafy.trip.attraction.model.entity.QAttractionHeart.attractionHeart;

@Repository
@RequiredArgsConstructor
public class AttractionCustomRepositoryImpl implements AttractionCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tuple> findAllByOrderByMeterAsc(double latitude, double longitude) {
        return null;
    }
}
