package com.example.myapp.controller.difficultyController;

import com.example.myapp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.myapp.model.difficulty.Expert;

@RestController
public class ExpertController {
    private final GameService service;

    @Autowired
    public ExpertController(GameService service) {
        this.service = service;
    }

    @GetMapping("/expert")
    public String handleSubmit() {
        service.setDifficulty(new Expert());
        service.goFirstFloor();
        return service.makeJSON();
    }
}