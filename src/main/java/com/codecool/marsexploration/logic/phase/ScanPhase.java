package com.codecool.marsexploration.logic.phase;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.Rover;

public class ScanPhase implements Phase{
    @Override
    public void perform(Context context) {

//        System.out.println("Entering ScanPhase...");

        Rover rover = context.getRover();
        char[][] map = context.getMap();
        int sight = rover.getSight();

        int mapWidth = map[0].length;
        int mapHeight = map.length;

        Coordinate roverPosition = rover.getPosition();
        int startX = Math.max(0, roverPosition.x() - sight);
        int startY = Math.max(0, roverPosition.y() - sight);
        int endX = Math.min(mapWidth - 1, roverPosition.x() + sight);
        int endY = Math.min(mapHeight - 1, roverPosition.y() + sight);

        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                char mapSymbol = map[i][j];

                switch (mapSymbol) {
                    case ' ':
                        break;
                    case '^':
                        if (!rover.getMountainCoordinates().contains(coordinate)) {
                            rover.addMountainCoordinate(coordinate);
                        }
                        break;
                    case '#':
                        if (!rover.getPitCoordinates().contains(coordinate)) {
                            rover.addPitCoordinate(coordinate);
                        }
                        break;
                    case '*':
                        if (!rover.getMineralCoordinates().contains(coordinate)) {
                            rover.addMineralCoordinate(coordinate);
                        }
                        break;
                    case '~':
                        if (!rover.getWaterCoordinates().contains(coordinate)) {
                            rover.addWaterCoordinate(coordinate);
                        }
                        break;
                    case 'A':
                        if (!rover.getAlienCoordinates().contains(coordinate)) {
                            rover.addAlienCoordinate(coordinate);
                        }
                        break;
                }
            }
        }

    }
}

