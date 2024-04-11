package com.my.nearby.area.model.dao;

import com.my.nearby.area.model.vo.SidoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SidoDao {
    SidoVo select(int code) throws DataAccessException;

    List<SidoVo> selectAll() throws DataAccessException;
    void insert(SidoVo sido) throws DataAccessException;
}
