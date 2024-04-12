package com.my.nearby;

import com.my.nearby.area.model.service.AreaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AreaServiceTest {
    private final AreaService areaService;

    @Autowired
    public AreaServiceTest(AreaService areaService) {
        this.areaService = areaService;
    }

    @Test
    @DisplayName("지역 갱신 서비스")
    public void refreshArea() {
        areaService.refreshArea();
    }
    @Test
    @DisplayName("시군구 갱신 서비스")
    public void refreshSigungu() {
        areaService.refreshSigungu(32);
    }
}
