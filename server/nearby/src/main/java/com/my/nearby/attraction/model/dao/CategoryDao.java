package com.my.nearby.attraction.model.dao;

import com.my.nearby.attraction.model.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {
    void insert(CategoryVo categoryVo) throws DataAccessException;

    List<CategoryVo> selectAllByCode(String code) throws DataAccessException;
}
