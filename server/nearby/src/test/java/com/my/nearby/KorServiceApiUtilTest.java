package com.my.nearby;

import com.my.nearby.area.util.KorServiceApiUtil;
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
    @DisplayName("행정구역 조회 테스트")
    public void getAreaCodeTest() {
        korServiceApiUtil.getAreaCode("");
    }

}
