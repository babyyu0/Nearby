package com.my.nearby;

import com.my.nearby.attraction.model.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {
    private final CategoryService categoryService;

    @Autowired
    public CategoryServiceTest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Test
    @DisplayName("카테고리 갱신 서비스")
    public void refreshCategoryTest() {
        categoryService.refreshCategory("");
    }
}
