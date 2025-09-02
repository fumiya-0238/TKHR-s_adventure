package com.example.myapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class InfoController {
	private final GameService service;

	@Autowired
	public InfoController(GameService service) {
		this.service = service;
	}

	@GetMapping("/conditionconfirm")
	public Map<String, String> conditionConfirm() {
		return service.makeConditionMap();
	}

	@PostMapping("/itemInfo")
	public List<String> getItemInfo(@RequestBody String index) {
		return service.makePlayerItemInfo(Integer.parseInt(index));
	}

	@GetMapping("/weaponInfo")
	public Map<String, String> getWeaponInfo() {
		return service.makeWeaponInfo();
	}

	@GetMapping("/myWeaponInfo")
	public List<String> getMyWeaponInfo() {
		return service.makeMyWeaponInfo();
	}

	@PostMapping("/subPageInfo")
	public List<String> getrelationInfo(@RequestBody Map<String, Object> request) {
		return service.makeSubPageInfo((String) request.get("page"), (Integer)request.get("index"));
	}
}
