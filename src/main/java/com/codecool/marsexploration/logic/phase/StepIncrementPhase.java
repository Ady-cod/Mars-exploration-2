package com.codecool.marsexploration.logic.phase;

import com.codecool.marsexploration.data.Context;

public class StepIncrementPhase implements Phase{
    @Override
    public void perform(Context context) {

//        System.out.println("Entering StepIncrementPhase...");

        context.incrementStepNumber();
    }
}
