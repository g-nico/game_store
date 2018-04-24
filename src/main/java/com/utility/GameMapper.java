package com.utility;

import com.model.frontObjects.GameDto;
import com.repository.Game;

import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {

    public static void toGameDto(Game game, GameDto gameDto) {
        gameDto.setId(game.getId());
        gameDto.setName(game.getName());
        gameDto.setPrice(game.getPrice());
        gameDto.setStock(game.getStock());
        gameDto.setGenre(game.getGenre());
    }
    
    public static void toGame(GameDto gameDto, Game game) {
        game.setId(gameDto.getId());
        game.setName(gameDto.getName());
        game.setPrice(gameDto.getPrice());
        game.setStock(gameDto.getStock());
        game.setGenre(gameDto.getGenre());
    }

    public static List<GameDto> toGameDtoList(final List<Game> games, List<GameDto> gameDtos) {
        gameDtos = games.stream()
                .map(game -> {
                    GameDto gameDto = new GameDto();
                    GameMapper.toGameDto(game, gameDto);
                    return gameDto;
                })
                .collect(Collectors.toList());
        return gameDtos;
    }

    public static void updateGame(GameDto gameDto, Game game) {
        if(gameDto.getId()!= null) game.setId(gameDto.getId());
        if(gameDto.getName() != null) game.setName(gameDto.getName());
        if(gameDto.getPrice() != null) game.setPrice(gameDto.getPrice());
        if(gameDto.getStock() != null) game.setStock(gameDto.getStock());
        if(gameDto.getGenre() != null) game.setGenre(gameDto.getGenre());
    }
}
