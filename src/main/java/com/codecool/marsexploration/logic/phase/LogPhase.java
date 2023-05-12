package com.codecool.marsexploration.logic.phase;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Coordinate;

import java.io.IOException;

public class LogPhase implements Phase {
    @Override
    public void perform(Context context) {

//        System.out.println("Entering LogPhase...");// Check if the LogPhase is entered

        int stepNumber = context.getStepNumber();
        String roverId = context.getRover().getId();
        Coordinate roverPosition = context.getRover().getPosition();

        try {
            String logEntry = context.getOutcome() == null
                    ? String.format("STEP %d; EVENT position; UNIT %s; POSITION [%d,%d]%n",
                        stepNumber, roverId, roverPosition.x(), roverPosition.y())
                    : String.format("STEP %d; EVENT outcome; OUTCOME %s%n", stepNumber, context.getOutcome());

            System.out.println(logEntry); // Print the log entry to the console
            context.getLogWriter().write(logEntry);

            context.getLogWriter().flush(); // Flush the BufferedWriter to make sure the log is written to the file

        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
