package com.ssafy.trip.model.dao;

import com.ssafy.trip.model.vo.Sido;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SidoDao {
    List<Sido> selectAll() throws DataAccessException;
}
