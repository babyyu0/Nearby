package com.ssafy.trip.model.entity;

import com.ssafy.trip.util.exception.trip.AttractionInvalidException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "innerBuilder")
@Getter
@Slf4j
public class ContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20) CHARACTER SET UTF8")
    @Comment("이름")
    private String name;

    public ContentTypeBuilder builder() {
        return ContentType.innerBuilder();
    }

    public ContentTypeBuilder id(long id) throws AttractionInvalidException {
        if(0 < id) {
            log.error("ContentType: 고유번호 입력 실패 " + id);
            throw new AttractionInvalidException();
        }
        return innerBuilder().id(id);
    }

    public ContentTypeBuilder name(String name) throws AttractionInvalidException {
        if(name == null || name.trim().equals("") || 20 < name.length()) {
            log.error("Attraction: 제목 입력 실패 " + name);
            throw new AttractionInvalidException();
        }
        return innerBuilder().name(name);
    }
}
