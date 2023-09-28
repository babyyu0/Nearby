package com.ssafy.trip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.util.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.model.service.TripService;
import com.ssafy.trip.model.vo.GugunVO;
import com.ssafy.trip.model.vo.SidoVO;
import com.ssafy.trip.model.vo.TripTypeVO;
import com.ssafy.trip.model.vo.TripVO;

@RestController
@RequestMapping("/trip")
@CrossOrigin
public class TripController {

	@Autowired
	private TripService tripService;

	@GetMapping("city")
	public ResponseEntity<?> getCity() {
		try {
			return ResponseEntity.ok(tripService.getCity());
		} catch (MyException e) {
			return new ResponseEntity<>(e.getMessage(), e.getStatus());
		}
	}

}

/*
	@PostMapping("get-gugun")
	public List<GugunVO> getGugun(@RequestBody HashMap<String, ?> map) {
		int sidoCode = (int) map.get("sidoCode");
		List<GugunVO> guguns = tripService.getAllGugunBySidoCode(sidoCode);
		
		return guguns;
	}
	
	@PostMapping("list")
	public List<TripVO> getList(@RequestBody TripVO trip) {
		List<TripVO> trips = tripService.getTripByRegion(trip);
		
		return trips;
	}
	
	@PostMapping("view")
	public HashMap<String, Object> getView(@RequestBody HashMap<String, ?> map) {
		int contentId = Integer.parseInt((String) map.get("contentId"));
		HashMap<String, Object> trip = tripService.getOneTripByContentId(contentId);
		
		return trip;
	}
	
	@PostMapping("get-thumbnail")
	public List<HashMap<String, Object>> getThumbnail() {
		List<HashMap<String, Object>> thumbnails = new ArrayList<>();
		
		// 썸네일 개수
		final int thumbCnt = 3;
		HashMap<String, Object> thumb = new HashMap<>();
		
		List<SidoVO> sidos = tripService.getAllSido();
		boolean[] visited = new boolean[sidos.size()];
		
		for(int i = 0; i < thumbCnt; i++) {
			thumb = new HashMap<>();
			
			int pick = (int)(Math.random() * sidos.size());
			if(visited[pick]) {  // 이미 뽑힌 난수일 경우
				i--;
				continue;
			}
			visited[pick] = true;  // 방문처리
			
			thumb.put("sidoCode", sidos.get(pick).getSidoCode());
			thumb.put("sidoName", sidos.get(pick).getSidoName());
			thumb.put("thumbImg", tripService.getRepresentative(sidos.get(pick).getSidoCode()).getFirstImage());
			
			thumbnails.add(thumb);
		}
		
		return thumbnails;
	}
	
	@PostMapping("get-closest-trip")
	public List<TripVO> getClosestTrip(@RequestBody TripVO trip) {
		List<TripVO> closestTrip = tripService.getClosestTrip(trip);
		
		return closestTrip;
	}
*/
