package com.my.nearby;

import com.my.nearby.util.KorServiceApiUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KorServiceApiUtilTest {
    private final KorServiceApiUtil korServiceApiUtil;

    @Autowired
    public KorServiceApiUtilTest(KorServiceApiUtil korServiceApiUtil) {
        this.korServiceApiUtil = korServiceApiUtil;
    }

    @Test
    @DisplayName("지역 코드 조회 테스트")
    public void getAreaCodeTest() {
        korServiceApiUtil.getAreaCode("");
    }
    @Test
    @DisplayName("카테고리 조회 테스트")
    public void getCategoryCodeTest() {
        korServiceApiUtil.getCategoryCode("A01");
    }

}
