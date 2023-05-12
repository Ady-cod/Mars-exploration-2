package com.codecool.marsexploration.logic.analyzer;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.data.Rover;

import java.util.Optional;

public class AlienAnalyzer implements Analyzer{
    @Override
    public Optional<Outcome> analyze(Context context) {
        Rover rover = context.getRover();
        int aliensFound = rover.getAlienCoordinates().size();
        if (aliensFound != 0) {
            return Optional.of(Outcome.NOT_COLONIZABLE_ALIENS);
        }

        return Optional.empty();
    }
}
