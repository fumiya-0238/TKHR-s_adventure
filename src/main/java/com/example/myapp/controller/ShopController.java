package com.example.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.service.GameService;

@RestController
public class ShopController {

	private final GameService service;


	public ShopController(GameService service) {
		this.service = service;
	}

	@GetMapping("/shop")
	public Map<String, List<String>> goShop() {
		return service.makeShop();
	}

	@PostMapping("/itemSort")
	public List<String> sortItem(@RequestBody String sort) {
		return service.sortItem(sort);
	}

	@PostMapping("/weaponSort")
	public List<String> sortWeapon(@RequestBody String sort) {
		return service.sortWeapon(sort);
	}

	@PostMapping("/itemBuy")
	public Map<String, List<String>> buyItem(@RequestBody String index) {
		return service.buyItem(Integer.parseInt(index));
	}

	@PostMapping("/weaponBuy")
	public Map<String, List<String>> buyWeapon(@RequestBody Map<String, Object> request) {
		int index = (int) request.get("weaponIndex");
		return service.buyWeapon(index);
	}

	@PostMapping("/serviceBuy")
	public Map<String, List<String>> buy(@RequestBody String index) {
		Map<String, List<String>> map = new HashMap<>();
		if (service.buyService(Integer.parseInt(index))) {
			map.put("serviceList", service.makeShopService());
			map.put("weaponList", service.makeShopWeapon());
		}
		map.put("playerStatus", service.makeList());
		return map;
	}

	@GetMapping("/cancelFusion")
	public Map<String, List<String>> cancelFusion() {
		Map<String, List<String>> map = new HashMap<>();
		service.cancelFusion();
		map.put("weaponList", service.makeShopWeapon());
		map.put("serviceList", service.makeShopService());
		map.put("playerStatus", service.makeList());
		return map;
	}
	
	@PostMapping("/shopItemInfo")
	public List<String> getShopItemInfo(@RequestBody String index) {
		return service.makeShopItemInfo(Integer.parseInt(index));
	}

	@PostMapping("/shopWeaponInfo")
	public List<String> getShopWeaponInfo(@RequestBody String index) {
		return service.makeShopWeaponInfo(Integer.parseInt(index));
	}
}
