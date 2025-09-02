package com.example.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.model.Difficulty;
import com.example.myapp.service.GameService;

@RestController
public class CommandController {
	private final GameService service;

	@Autowired
	public CommandController(GameService service) {
		this.service = service;
	}

	@PostMapping("/difficulty")
	public  List<String> setDifficulty(@RequestBody String difficulty) {
		// 画像パスをモデルに追加
		if (difficulty.equals("beginner")) {
			service.setDifficulty(Difficulty.BEGINNER);
		} else if (difficulty.equals("novice")) {
			service.setDifficulty(Difficulty.NOVICE);
		} else {
			service.setDifficulty(Difficulty.EXPERT);
		}
		service.goFirstFloor();
		return service.makeList(); // Thymeleafテンプレート名
	}

	@GetMapping("/nextfloor")
	public  List<String> nextFloor() {
		service.nextFloor();
		return service.makeList();
	}

	@PostMapping("/command")
	public List<String> command(@RequestBody String command) {
		// 画像パスをモデルに追加
		if (command.equals("attack")) {
			service.playerTurn(1);
		} else if (command.equals("weekAttack")) {
			service.playerTurn(2);
		} else if (command.equals("criticalAttack")) {
			service.playerTurn(3);
		} else if (command.equals("defence")) {
			service.playerTurn(4);
		} else if (command.equals("tameru")) {
			service.playerTurn(5);
		}
		return service.makeList(); // Thymeleafテンプレート名
	}

	@GetMapping("/boost")
	public boolean boost() {
		return service.playerBoost();
	}

	@PostMapping("/item")
	public List<String> useItem(@RequestBody String i) {
		service.useItem(Integer.parseInt(i));
		return service.makeList();
	}
}