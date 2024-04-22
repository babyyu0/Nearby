package com.my.nearby.attraction.model.service;

import com.my.nearby.attraction.model.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findSubCategoryList(String categoryCode);
    List<CategoryResponseDto> refreshCategory(String categoryCode);
}
