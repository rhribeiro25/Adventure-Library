package com.pictet.technologies.adventurelibrary.domain.service;

import com.pictet.technologies.adventurelibrary.domain.model.Consequence;
import com.pictet.technologies.adventurelibrary.domain.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameConsequenceService {

    public void apply(Game game, Consequence consequence) {
        if (consequence == null || consequence.getType() == null) {
            return;
        }

        int value = normalizeValue(consequence.getValue());

        switch (consequence.getType()) {
            case LOSE_HEALTH -> loseHealth(game, value);
            case GAIN_HEALTH -> gainHealth(game, value);
        }
    }

    private void loseHealth(Game game, int value) {
        game.setHealth(Math.max(0, currentHealth(game) - value));
    }

    private void gainHealth(Game game, int value) {
        game.setHealth(currentHealth(game) + value);
    }

    private int currentHealth(Game game) {
        return game.getHealth() == null ? 0 : game.getHealth();
    }

    private int normalizeValue(Integer value) {
        if (value == null || value < 0) {
            return 0;
        }

        return value;
    }
}