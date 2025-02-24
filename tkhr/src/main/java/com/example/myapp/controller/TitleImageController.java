package com.example.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.myapp.repository.ImagesRepository;

@Controller
public class TitleImageController {

    @GetMapping("/")
    public String showImage(Model model) {
        // 画像パスをモデルに追加
       
        model.addAttribute("title_Image", ImagesRepository.INSTANCE.getTitle());
       // model.addAttribute("beginner_Button", ImagesRepository.INSTANCE.getBeginnerButton());
        //model.addAttribute("novice_Button", ImagesRepository.INSTANCE.getNoviceButton());
        //model.addAttribute("expert_Button", ImagesRepository.INSTANCE.getExpertButton());
        return "index"; // Thymeleafテンプレート名
    }
}