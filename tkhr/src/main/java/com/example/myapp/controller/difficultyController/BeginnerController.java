package com.example.myapp.controller.difficultyController;

import com.example.myapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.myapp.model.difficulty.Beginner;

@RestController
public class BeginnerController {
    private final GameService service;

    @Autowired
    public BeginnerController(GameService service) {
        this.service = service;
    }

    @GetMapping("/beginner")
    public String handleSubmit() {
        service.setDifficulty(new Beginner());
        service.goFirstFloor();
        return service.makeJSON();
    }
}
