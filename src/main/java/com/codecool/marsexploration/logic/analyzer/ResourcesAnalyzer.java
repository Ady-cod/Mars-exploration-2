package com.codecool.marsexploration.logic.analyzer;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.data.Rover;

import java.util.Optional;

public class ResourcesAnalyzer implements Analyzer{
    private static final int MIN_MINERALS = 4;
    private static final int MIN_WATER = 3;
    private static final double MIN_SCANNED_AREA_PERCENTAGE = 0.01;
    @Override
    public Optional<Outcome> analyze(Context context) {
        Rover rover = context.getRover();
        int mineralsFound = rover.getMineralCoordinates().size();
        int waterFound = rover.getWaterCoordinates().size();
        int totalMapArea = context.getMap().length * context.getMap()[0].length;
        double scannedAreaPercentage = (double) rover.getVisitedCoordinates().size() / totalMapArea;

        if (mineralsFound >= MIN_MINERALS && waterFound >= MIN_WATER) {
            return Optional.of(Outcome.COLONIZABLE);
        } else if (scannedAreaPercentage >= MIN_SCANNED_AREA_PERCENTAGE && (mineralsFound == 0 || waterFound == 0)) {
            return Optional.of(Outcome.NOT_COLONIZABLE_RESOURCES);
        }
        return Optional.empty();
    }
}
