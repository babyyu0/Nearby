package com.ssafy.trip.attraction.model.repository.custom;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.trip.attraction.model.entity.QAttraction.attraction;

@Repository
@RequiredArgsConstructor
public class AttractionCustomRepositoryImpl implements AttractionCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tuple> findAllByOrderByMeterAsc(double latitude, double longitude) {
        NumberExpression<Double> attLatitude = attraction.attractionInfo.latitude.doubleValue();  // 위도
        NumberExpression<Double> attLongitude = attraction.attractionInfo.longitude.doubleValue();  // 경도
        Expression<Double> myLatitude = Expressions.constant(latitude);  // 위도
        Expression<Double> myLongitude = Expressions.constant(longitude);  // 경도
        return queryFactory.select(
                        attraction.as("attraction"),
                        MathExpressions.acos(
                                MathExpressions.cos(MathExpressions.radians(attLatitude))
                                        .multiply(MathExpressions.cos(MathExpressions.radians(myLatitude)))
                                        .multiply(MathExpressions.cos(MathExpressions.radians(myLongitude).subtract(MathExpressions.radians(attLongitude))))
                                        .add(MathExpressions.sin(MathExpressions.radians(attLatitude))
                                                .multiply(MathExpressions.sin(MathExpressions.radians(myLatitude)))
                                        )
                        ).multiply(6371000).abs().as("dist")).from(attraction)
                .orderBy(Expressions.stringPath("dist").asc())
                .limit(4)
                .fetch();
    }
}
