package com.example.myapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class CommandController {

	private final GameService service;

	public CommandController(GameService service) {
		this.service = service;
	}
	
	@PostMapping("/difficulty")
	public List<String> setDifficulty(@RequestBody String difficulty) {
		if (difficulty.equals("beginner")) {
			service.goFirstFloor(1);
		} else if (difficulty.equals("novice")) {
			service.goFirstFloor(2);
		} else if (difficulty.equals("expert")) {
			service.goFirstFloor(3);
		} else if (difficulty.equals("shortage")) {
			service.goFirstFloor(4);
		} else if (difficulty.equals("inflation")) {
			service.goFirstFloor(5);
		} else if (difficulty.equals("convenience")) {
			service.goFirstFloor(6);
		}else {
			service.getDebugData();
		}
		return service.makeList();
	}

	@GetMapping("/nextfloor")
	public List<String> nextFloor() {
		return service.nextFloor();
	}

	@PostMapping("/command")
	public List<String> command(@RequestBody String command) {
		// 画像パスをモデルに追加
		int n = 0;
		if (command.equals("attack")) {
			n = 1;
		} else if (command.equals("weekAttack")) {
			n = 2;
		} else if (command.equals("criticalAttack")) {
			n = 3;
		} else if (command.equals("defence")) {
			n = 4;
		} else if (command.equals("tameru")) {
			n = 5;
		}
		return service.playerTurn(n);
	}

	@GetMapping("/boost")
	public List<String> boost() {
		return service.playerBoost();
	}

	@GetMapping("/battleLog")
	public List<String> battleLog() {
		return service.getBattleLog();
	}

	@PostMapping("/item")
	public List<String> useItem(@RequestBody String i) {
		return service.useItem(Integer.parseInt(i));
	}

	@GetMapping("/turnStart")
	public void turnStart() {
		service.resetCommandClickFlags();
	}
}