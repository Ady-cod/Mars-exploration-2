package com.codecool.marsexploration.logic.routine;

import com.codecool.marsexploration.data.Context;

public class ReturningRoutine implements Routine {
    @Override
    public void move(Context context) {
        context.getRover().setPosition(context.getLandingCoordinate());
        System.out.println("Rover has teleported to the spaceship, the mission is finished with the outcome: "
                + context.getOutcome());
    }
}

