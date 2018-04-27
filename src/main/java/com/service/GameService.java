package com.service;

import com.model.frontObjects.GameDto;
import com.repository.Game;
import com.repository.GameRepository;
import com.utility.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameDto addGame(final GameDto gameDto) {
        Game g = new Game();
        GameMapper.toGame(gameDto, g);
        gameRepository.save(g);

        return gameDto;
    }

    public List<GameDto> getAll(){
        List<Game> games = gameRepository.findAll();
        List<GameDto> gameDtos = new ArrayList<>();
        gameDtos = GameMapper.toGameDtoList(games, gameDtos);

        return gameDtos;
    }

    public void updateGame(final GameDto gameDto) {
        Game g = gameRepository.getOne(gameDto.getId());
        GameMapper.updateGame(gameDto, g);
        gameRepository.save(g);
    }

    public void deleteGame(final Long id) {
        gameRepository.delete(id);
    }

    public List<GameDto> getCart(final List<Long> ids) {
        List<Game> games = gameRepository.findAll(ids);
        List<GameDto> gameDtos = new ArrayList<>();
        gameDtos = GameMapper.toGameDtoList(games, gameDtos);

        return gameDtos;
    }
}
