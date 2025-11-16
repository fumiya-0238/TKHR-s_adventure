package com.example.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class MonsterController {

	private final GameService service;

	public MonsterController(GameService service) {
		this.service = service;
	}

	@GetMapping("/monster")
	public Map<String, String> showMonster() {
		Map<String, String> response = new HashMap<>();
		response.put("imageURL", service.getMonsterImage());
		return response;
	}
}
