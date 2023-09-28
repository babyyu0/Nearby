package com.ssafy.trip.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dao.TripDAO;
import com.ssafy.trip.model.vo.GugunVO;
import com.ssafy.trip.model.vo.SidoVO;
import com.ssafy.trip.model.vo.TripTypeVO;
import com.ssafy.trip.model.vo.TripVO;

@Service
public class TripServiceImpl {

	@Autowired
	private TripDAO tripDAO;

	public List<SidoVO> getAllSido() {
		return tripDAO.selectAllSido();
	}
	public SidoVO getOneSido(int sidoCode) {
		return tripDAO.selectOneSido(sidoCode);
	}

	public List<GugunVO> getAllGugunBySidoCode(int sidoCode) {
		return tripDAO.selectAllGugunBySidoCode(sidoCode);
	}
	public GugunVO getOneGugunBySidoCode(int gugunCode, int sidoCode) {
		return tripDAO.selectOneGugunBySidoCode(gugunCode, sidoCode);
	}

	public List<TripTypeVO> getTripType() {
		return tripDAO.selectAllTripType();
	}

	public List<TripVO> getTripByRegion(TripVO trip) {
		return tripDAO.selectTripByRegion(trip);
	}

	public TripVO getRepresentative(int sidoCode) {
		return tripDAO.selectOneTripBySidoCode(sidoCode);
	}

	public List<TripVO> getClosestTrip(TripVO trip) {
		return tripDAO.selectTripByLocation(trip);
	}

	public HashMap<String, Object> getOneTripByContentId(int contentId) {
		return tripDAO.selectOneTripByContentId(contentId);
	}

}
