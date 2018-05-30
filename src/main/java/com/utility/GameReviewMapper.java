package com.utility;

import com.model.frontObjects.GameReviewDto;
import com.repository.GameReview;

import java.util.List;
import java.util.stream.Collectors;

public class GameReviewMapper {

    public static void toDto(GameReview gameReview, GameReviewDto gameReviewDto) {
        gameReviewDto.setId(gameReview.getId());
        gameReviewDto.setComments(gameReview.getComments());
        gameReviewDto.setGame(gameReview.getGame().getName());
        gameReviewDto.setGamePhoto(gameReview.getGame().getImgPath());
        gameReviewDto.setUser(gameReview.getUser().getName());
    }

    public static void toEntity(GameReviewDto gameReviewDto, GameReview gameReview) {
        gameReview.setId(gameReviewDto.getId());
        gameReview.setComments(gameReviewDto.getComments());
    }

    public static void toDtoList(List<GameReview> gameReviews, List<GameReviewDto> gameReviewDtos) {
        gameReviewDtos = gameReviews.stream()
                .map(gameReviewDto -> {
                    GameReviewDto g = new GameReviewDto();
                    toDto(gameReviewDto, g);
                    return g;
                }).collect(Collectors.toList());
    }
}
