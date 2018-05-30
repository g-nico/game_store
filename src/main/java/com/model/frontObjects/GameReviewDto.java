package com.model.frontObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameReviewDto {

    private Long id;
    private String user;
    private String game;
    private String gamePhoto;
    private List<String> comments;

}
