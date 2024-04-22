package com.my.nearby.attraction.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.nearby.BusinessException;
import com.my.nearby.attraction.model.dao.CategoryDao;
import com.my.nearby.attraction.model.dto.CategoryDto;
import com.my.nearby.attraction.model.dto.CategoryResponseDto;
import com.my.nearby.attraction.model.vo.CategoryVo;
import com.my.nearby.util.KorServiceApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findSubCategoryList(String categoryCode) {
        if(!categoryCode.isEmpty() && !categoryCode.matches("^[A-Z]{1}[0-9]{2,8}$")) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "카테고리 코드가 유효하지 않습니다.");
        }
        List<CategoryVo> subCategoryList = categoryDao.selectAllByCode(categoryCode);

        List<CategoryResponseDto> subCategoryDtoList = new ArrayList<>();
        for(CategoryVo subCategory : subCategoryList) {
            subCategoryDtoList.add(CategoryResponseDto.from(subCategory));
        }

        return subCategoryDtoList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<CategoryResponseDto> refreshCategory(String categoryCode) {
        JsonNode jsonNode = KorServiceApiUtil.getCategoryCode(categoryCode);
        ObjectMapper objectMapper = new ObjectMapper();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        int i = 0;
        try {
            while (i < jsonNode.size()) {
                categoryDtoList.add(objectMapper.treeToValue(jsonNode.get(i++), CategoryDto.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        List<CategoryResponseDto> subCategoryDtoList = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtoList) {
            CategoryVo category = new CategoryVo(categoryDto.code(), categoryDto.name());
            categoryDao.insert(category);
            subCategoryDtoList.add(CategoryResponseDto.from(category));
        }

        return subCategoryDtoList;
    }
}
