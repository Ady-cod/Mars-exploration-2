package com.codecool.marsexploration.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Context {
    private int stepNumber;
    private int timeout;
    private char [][] map;
    private Coordinate landingCoordinate;
    private Rover rover;

    private Outcome outcome;

    private BufferedWriter logWriter;
    public Context(int timeout, char[][] map, Coordinate landingCoordinate, Rover rover, Outcome outcome) {
        this.stepNumber = 0;
        this.timeout = timeout;
        this.map = map;
        this.landingCoordinate = landingCoordinate;
        this.rover = rover;
        this.outcome = outcome;
    }

    public void setLogWriter(String logPath) throws IOException {
        this.logWriter = new BufferedWriter(new FileWriter(logPath));
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public int getTimeout() {
        return timeout;
    }

    public char[][] getMap() {
        return map;
    }

    public Coordinate getLandingCoordinate() {
        return landingCoordinate;
    }

    public Rover getRover() {
        return rover;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public BufferedWriter getLogWriter() {
        return logWriter;
    }

    public void incrementStepNumber () {
        stepNumber++;
    }

}


