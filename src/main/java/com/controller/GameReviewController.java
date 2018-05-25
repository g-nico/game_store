package com.controller;

import com.model.frontObjects.GameReviewDto;
import com.repository.GameReview;
import com.service.GameReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameReviewController {

    @Autowired
    private GameReviewService gameReviewService;

    @GetMapping(value = "/gameReviews")
    public String getReviews(Model model) {
        model.addAttribute("reviews", gameReviewService.getAll());

        return "/gameReview";
    }

    @PostMapping(value = "/gameReviews")
    public String addReview(@RequestBody GameReviewDto gameReviewDto) {
        gameReviewService.saveReview(gameReviewDto);

        return "redirect:/gameReview";
    }
}
