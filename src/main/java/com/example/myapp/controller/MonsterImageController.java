package com.example.myapp.controller;

import com.example.myapp.service.GameService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonsterImageController {
    private final GameService service;

    @Autowired
    public MonsterImageController(GameService service) {
        this.service = service;
    }

    @GetMapping("/monster")
    public Map<String, String> showImage() {
        // 画像パスをモデルに追加
        Map<String, String> response = new HashMap<>();
        // "{imageUrl:\"" + service.getMonsterImage() + "\"}";
        response.put("imageURL", service.getMonsterImage());
        return response;
    }
}