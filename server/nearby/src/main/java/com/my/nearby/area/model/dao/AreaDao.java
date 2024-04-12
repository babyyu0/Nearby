package com.my.nearby.area.model.dao;

import com.my.nearby.area.model.vo.AreaVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AreaDao {
    AreaVo select(int areaCode, int sigunguCode) throws DataAccessException;
    List<AreaVo> selectAll() throws DataAccessException;
    void insert(AreaVo area) throws DataAccessException;
}
