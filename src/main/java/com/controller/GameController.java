package com.controller;

import com.model.frontObjects.GameDto;
import com.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping(value = "saveGame")
    public GameDto saveGame(@RequestBody GameDto gameDto) {
        return gameService.addGame(gameDto);
    }

    @GetMapping(value = "getAllGames")
    public List<GameDto> getAllGames() {
        return gameService.getAll();
    }

    @PutMapping(value = "updateGame")
    public void updateGame(@RequestBody GameDto gameDto, @RequestParam Long id) {
        gameDto.setId(id);
        gameService.updateGame(gameDto);
    }

    @DeleteMapping(value = "deleteGame")
    public void delete(@RequestParam Long id) {
        gameService.deleteGame(id);
    }

}
