package com.example.myapp.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class ShopController {
	private final GameService service;

	@Autowired
	public ShopController(GameService service) {
		this.service = service;
	}
	
	@GetMapping("/shop")
	public Map<String,String> goShop() {
		return service.makeShop();
	}
	
	@PostMapping("/itemSort")
	public Map<String,String> sortItem(@RequestBody String sort) {
		service.sortItem(sort);
		return service.showItem();
	}
	
	@PostMapping("/itemBuy")
	public Map<String,String> buyItem(@RequestBody String index) {
		service.buyItem(index);
		Map<String,String> map = new HashMap<>();
		map = service.showItem();
		map.putAll(service.makeMap());
		return map;
	}
}
