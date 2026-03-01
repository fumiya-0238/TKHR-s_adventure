package com.example.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class DebugController {
	private final GameService service;

	public DebugController(GameService service) {
		this.service = service;
	}
	
	@PostMapping("/debugPlayer")
	public List<String> debugPlayer(@RequestBody Map<String, Object> request) {
		service.debugPlayer();
		return service.makeList();
	}

	@PostMapping("/debugMonster")
	public Map<String, String> showSampleMonster(@RequestBody Map<String, Object> request) {
		Map<String, String> response = new HashMap<>();
		response.put("imageURL",
				service.getSampleMonsterImage((int) request.get("index"), (int) request.get("listIndex")));
		return response;
	}
	
	@PostMapping("/goDebugDungeon")
	public List<String> goDebugFloor(@RequestBody Map<String, Object> request) {
		service.goDebugFloor((int) request.get("index"), (int) request.get("listIndex"));
		return service.makeList();
	}
	
}
