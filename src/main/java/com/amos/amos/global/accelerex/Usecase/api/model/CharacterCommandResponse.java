package com.amos.amos.global.accelerex.Usecase.api.model;

import com.amos.amos.global.accelerex.domain.model.CharacterCommand;
import lombok.Data;

import java.util.List;

@Data
public class CharacterCommandResponse {
    private List<CharacterCommand> character;
    private int numberOfCharacters;

    public CharacterCommandResponse(List<CharacterCommand> character, int numberOfCharacters) {
        this.character = character;
        this.numberOfCharacters = numberOfCharacters;
    }
}
