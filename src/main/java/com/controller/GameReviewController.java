package com.controller;

import com.model.frontObjects.GameDto;
import com.model.frontObjects.GameReviewDto;
import com.service.GameReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GameReviewController {

    private final GameReviewService gameReviewService;

    @Autowired
    public GameReviewController(final GameReviewService gameReviewService) {
        this.gameReviewService = gameReviewService;
    }

    @PostMapping(value = "/gameReviews")
    public String getReviews(@RequestBody final GameDto gameDto, final Model model) {
        model.addAttribute("review", gameReviewService.getAllForGame(gameDto));

        return "/gameReview";
    }

    @PostMapping(value = "/gameReviews/add")
    public String addReview(@RequestBody final GameReviewDto gameReviewDto) {
        gameReviewService.saveReview(gameReviewDto);

        return "redirect:/gameReview";
    }
}
