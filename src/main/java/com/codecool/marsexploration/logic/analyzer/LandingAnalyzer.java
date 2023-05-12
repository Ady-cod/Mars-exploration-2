package com.codecool.marsexploration.logic.analyzer;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Outcome;

import java.util.Optional;

public class LandingAnalyzer  implements Analyzer{
    @Override
    public Optional<Outcome> analyze(Context context) {
        char[][] map = context.getMap();
        int x = context.getLandingCoordinate().x();
        int y = context.getLandingCoordinate().y();
        Optional<Outcome> outcome = Optional.empty();
        if (map[x][y] != ' ') {
            outcome = Optional.of(Outcome.WRONG_LANDING_COORDINATES);
        }
        return outcome;
    }
}
