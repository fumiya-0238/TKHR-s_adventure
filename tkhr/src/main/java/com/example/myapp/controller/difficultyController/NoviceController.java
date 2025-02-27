package com.example.myapp.controller.difficultyController;

import com.example.myapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.myapp.model.difficulty.Novice;

@RestController
public class NoviceController {
    private final GameService service;

    @Autowired
    public NoviceController(GameService service) {
        this.service = service;
    }

    @GetMapping("/novice")
    public String handleSubmit() {
        service.setDifficulty(new Novice());
        service.goFirstFloor();
        return service.makeJSON();
    }
}