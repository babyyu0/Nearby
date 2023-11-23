package com.ssafy.trip.controller;

import com.ssafy.trip.aop.TimeTrace;
import com.ssafy.trip.model.service.TripService;
import com.ssafy.trip.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
	private final TripService tripService;
	@GetMapping("sido/refresh")
	public ResponseEntity<?> refreshSido() throws MyException {
		return ResponseEntity.ok(tripService.refreshSido());
	}
	@GetMapping("gugun/refresh")
	public ResponseEntity<?> refreshGugun() throws MyException {
		return ResponseEntity.ok(tripService.refreshGugun());
	}
	@TimeTrace
	@GetMapping("sido")
	public ResponseEntity<?> getSido() throws MyException {
		return ResponseEntity.ok(tripService.getSido());
	}
	@TimeTrace
	@GetMapping("gugun")
	public ResponseEntity<?> getGugun() throws MyException {
		return ResponseEntity.ok(tripService.getGugun());
	}

}

/*
	
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
		
		List<Sido> sidos = tripService.getAllSido();
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
