package com.example.myapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class InfoController {

	private final GameService service;

	public InfoController(GameService service) {
		this.service = service;
	}

	@GetMapping("/conditionconfirm")
	public Map<String, List<String>> conditionConfirm() {
		return service.makeConditionMap();
	}

	@PostMapping("/itemInfo")
	public List<String> getItemInfo(@RequestBody String index) {
		return service.makePlayerItemInfo(Integer.parseInt(index));
	}

	@GetMapping("/myWeaponInfo")
	public List<String> getMyWeaponInfo() {
		return service.makeMyWeaponInfo();
	}

	@GetMapping("/monsterInfo")
	public List<String> getMonsterInfo() {
		return service.makeBattleMonsterInfo();
	}

	@PostMapping("/subPageInfo")
	public List<String> getRelationInfo(@RequestBody Map<String, Object> request) {
		return service.makeSubPageInfo((int) request.get("index"), (boolean) request.get("toggle"));
	}

	@GetMapping("/backPage")
	public void getInfo() {
		service.backPage();
	}

	@GetMapping("/closeInfo")
	public boolean closeInfo() {
		return service.closePage();
	}
}
