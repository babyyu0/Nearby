package com.my.nearby.area.model.dao;

import com.my.nearby.area.model.vo.GugunVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GugunDao {
    GugunVo select(int code) throws DataAccessException;

    List<GugunVo> selectAllBySidoCode(int sidoCode) throws DataAccessException;

    List<GugunVo> selectAll() throws DataAccessException;
    void insert(GugunVo gugun) throws DataAccessException;
}
