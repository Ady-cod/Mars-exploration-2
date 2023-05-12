package com.codecool.marsexploration.logic.phase;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Outcome;
import com.codecool.marsexploration.logic.routine.ExploringRoutine;
import com.codecool.marsexploration.logic.routine.ReturningRoutine;
import com.codecool.marsexploration.logic.routine.Routine;

public class MovementPhase implements Phase{
//    private final Routine exploringRoutine = new ExploringRoutine();
//    private Routine returningRoutine = new ReturningRoutine();

    @Override
    public void perform(Context context) {

        context.getRover().getRoutine().move(context);
//        exploringRoutine.move(context);
//        System.out.println("Entering MovementPhase...");

//        if (context.getOutcome() == null) {
//            context.getRover().setRoutine(exploringRoutine);
//            exploringRoutine.move(context);
//
//        } else {
//            context.getRover().setRoutine(returningRoutine);
//            returningRoutine.move(context);
//        }
    }
}

