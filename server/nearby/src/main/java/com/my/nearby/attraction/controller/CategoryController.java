package com.my.nearby.attraction.controller;

import com.my.nearby.BusinessException;
import com.my.nearby.attraction.model.dto.CategoryResponseDto;
import com.my.nearby.attraction.model.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/cat")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("refresh")
    public ResponseEntity<?> refreshAllCategory() {
        Queue<String> catQueue = new ArrayDeque<>();
        catQueue.add("");  // 첫 분류는 대 분류로 받기

        while (!catQueue.isEmpty()) {
            String catCode = catQueue.poll();
            if (!catCode.isBlank() && !catCode.matches("^[A-Z]{1}[0-9]{2,8}$")) {  // 유효성 검사
                log.error("분류 코드가 유효하지 않습니다 : {}", catCode);
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "분류 코드가 유효하지 않습니다.");
            }
            List<CategoryResponseDto> categoryDtoList = categoryService.refreshCategory(catCode);  // 분류 갱신하기

            for (CategoryResponseDto subCategoryDto : categoryDtoList) {  // 해당 분류의 하위 분류를 찾기 위해 Queue에 삽입
                if (catCode.equals(subCategoryDto.code()) || subCategoryDto.code().length() == 9) continue;
                catQueue.add(subCategoryDto.code());
            }
        }

        return ResponseEntity.ok("");
    }
}
