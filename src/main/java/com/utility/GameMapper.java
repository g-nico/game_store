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
        if(game.getStock() > 0) gameDto.setInStock(true);
            else gameDto.setInStock(true);
        gameDto.setGenre(game.getGenre());
        gameDto.setImgPath(game.getImgPath());
    }
    
    public static void toGame(GameDto gameDto, Game game) {
        game.setId(gameDto.getId());
        game.setName(gameDto.getName());
        game.setPrice(gameDto.getPrice());
        game.setGenre(gameDto.getGenre());
        game.setImgPath(gameDto.getImgPath());
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
        if(gameDto.getGenre() != null) game.setGenre(gameDto.getGenre());
        if(gameDto.getImgPath() != null) game.setImgPath(gameDto.getImgPath());
    }
}
