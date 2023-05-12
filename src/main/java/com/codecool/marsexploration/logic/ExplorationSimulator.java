package com.codecool.marsexploration.logic;

import com.codecool.marsexploration.data.*;
import com.codecool.marsexploration.logic.phase.*;
import com.codecool.marsexploration.logic.routine.ExploringRoutine;
import com.codecool.marsexploration.logic.routine.ReturningRoutine;

import java.util.ArrayList;
import java.util.List;

public class ExplorationSimulator {
    private Context context;

    public void simulate(SimulationInput input) {

        context.getRover().setRoutine(new ExploringRoutine());

        List<Phase> phases = new ArrayList<>();
        phases.add(new ScanPhase());
        phases.add(new LogPhase());
        phases.add(new AnalysisPhase());
        phases.add(new MovementPhase());
        phases.add(new StepIncrementPhase()); // step is incrementing

        // Loop until an outcome is reached
        while (context.getOutcome() == null) {
            for (Phase phase : phases) {
                phase.perform(context);

                // If there's an outcome, break out of the loop
                if (context.getOutcome() != null) {
                    break;
                }
            }
            printMap();
        }

        phases.get(1).perform(context);

        ReturningRoutine returningRoutine = new ReturningRoutine();
        context.getRover().setRoutine(returningRoutine);
//        returningRoutine.move(context);
      phases.get(3).perform(context);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void printMap() {

        // Print the map with the rover's position in red
        char[][] map = context.getMap();
        Rover rover = context.getRover();
        Coordinate roverPosition = rover.getPosition();
        int startX = Math.max(0, roverPosition.x() - 20);
        int endX = Math.min(map.length - 1, roverPosition.x() + 20);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i >= startX && i <= endX) {
                    Coordinate currentCoordinate = new Coordinate(i, j);
                    if (roverPosition.equals(currentCoordinate)) {
                        System.out.print("\033[31mR\033[0m"); // ANSI escape code for red color
                    } else if (isCoordinateInRoverLists(rover, currentCoordinate)) {
                        if (map[i][j] == '~' || map[i][j] == '*' || map[i][j] == 'A') {
                            System.out.print("\033[47m\033[30m" + map[i][j] + "\033[0m"); // ANSI escape code for white background and black foreground


                        } else {
                            System.out.print("\033[33m" + map[i][j] + "\033[0m"); // ANSI escape code for yellow color
                        }
                    } else {
                        System.out.print(map[i][j]);
                    }
                }
            }
            if (i >= startX && i <= endX) {
                System.out.println();
            }
        }

        // Add a delay
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private boolean isCoordinateInRoverLists(Rover rover, Coordinate coordinate) {
        return rover.getMineralCoordinates().contains(coordinate) ||
                rover.getWaterCoordinates().contains(coordinate) ||
                rover.getAlienCoordinates().contains(coordinate) ||
                rover.getMountainCoordinates().contains(coordinate) ||
                rover.getPitCoordinates().contains(coordinate);
    }

}
