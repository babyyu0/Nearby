package com.ssafy.trip.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssafy.trip.model.vo.GugunVO;
import com.ssafy.trip.model.vo.SidoVO;
import com.ssafy.trip.model.vo.TripTypeVO;
import com.ssafy.trip.model.vo.TripVO;

@Mapper
@Repository
public interface TripDAO {

	List<SidoVO> selectAllSido() throws DataAccessException;
	
	SidoVO selectOneSido(int sidoCode) throws DataAccessException;

	List<GugunVO> selectAllGugunBySidoCode(int sidoCode) throws DataAccessException;
	GugunVO selectOneGugunBySidoCode(int gugunCode, int sidoCode) throws DataAccessException;

	List<TripTypeVO> selectAllTripType() throws DataAccessException;

	List<TripVO> selectTripByRegion(TripVO trip) throws DataAccessException;

	TripVO selectOneTripBySidoCode(int sidoCode) throws DataAccessException;

	List<TripVO> selectTripByLocation(TripVO trip) throws DataAccessException;

	HashMap<String, Object> selectOneTripByContentId(int contentId) throws DataAccessException;
	
}
