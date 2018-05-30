package com.service;

import com.model.frontObjects.GameDto;
import com.model.frontObjects.GameReviewDto;
import com.repository.*;
import com.utility.GameReviewMapper;
import com.websecurity.SecurityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameReviewService {

    private final GameReviewRepository gameReviewRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public GameReviewService(final GameReviewRepository gameReviewRepository,
                             final GameRepository gameRepository,
                             final UserRepository userRepository) {
        this.gameReviewRepository = gameReviewRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<GameReviewDto> getAll() {
        final List<GameReview> gameReviews = gameReviewRepository.findAll();
        final List<GameReviewDto> gameReviewDtos = new ArrayList<>();

        GameReviewMapper.toDtoList(gameReviews, gameReviewDtos);
        return gameReviewDtos;
    }

    public void saveReview(final GameReviewDto gameReviewDto) {
        final GameReview g = new GameReview();
        GameReviewMapper.toEntity(gameReviewDto, g);

        g.setGame(gameRepository.findByName(gameReviewDto.getGame()));
        for(User u : userRepository.findAllByName(gameReviewDto.getUser())) {
            if(SecurityService.getLoggedUser().getEmail().equals(u.getEmail())) {
                g.setUser(u);
                break;
            }
        }
    }

    public List<GameReviewDto> getAllForGame(final GameDto gameDto) {
        final Game g = gameRepository.findByName(gameDto.getName());
        final List<GameReview> gameReviews = gameReviewRepository.findAllByGame(g);
        final List<GameReviewDto> gameReviewDtos = new ArrayList<>();

        GameReviewMapper.toDtoList(gameReviews, gameReviewDtos);
        return gameReviewDtos;
    }
}
