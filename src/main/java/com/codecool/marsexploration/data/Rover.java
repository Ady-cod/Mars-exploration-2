package com.codecool.marsexploration.data;

import com.codecool.marsexploration.logic.routine.Routine;

import java.util.ArrayList;
import java.util.List;

public class Rover {
    private String id;
    private Coordinate position;
    private int sight;
    private Routine routine;
    List<Coordinate> visitedCoordinates ;
    List<Coordinate> mineralCoordinates ;
    List<Coordinate> waterCoordinates ;
    List<Coordinate> alienCoordinates ;
    List<Coordinate> mountainCoordinates ;
    List<Coordinate> pitCoordinates ;


    public Rover(String id, Coordinate position, int sight) {
        this.id = id;
        this.position = position;
        this.sight = sight;
        this.visitedCoordinates = new ArrayList<>();
        this.mineralCoordinates = new ArrayList<>();
        this.waterCoordinates = new ArrayList<>();
        this.alienCoordinates = new ArrayList<>();
        this.mountainCoordinates = new ArrayList<>();
        this.pitCoordinates = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Coordinate getPosition() {
        return position;
    }

    public int getSight() {
        return sight;
    }

    public Routine getRoutine() {
        return routine;
    }

    public List<Coordinate> getVisitedCoordinates() {
        return visitedCoordinates;
    }

    public List<Coordinate> getMineralCoordinates() {
        return mineralCoordinates;
    }

    public List<Coordinate> getWaterCoordinates() {
        return waterCoordinates;
    }

    public List<Coordinate> getAlienCoordinates() {
        return alienCoordinates;
    }

    public List<Coordinate> getPitCoordinates() {
        return pitCoordinates;
    }

    public List<Coordinate> getMountainCoordinates() {
        return mountainCoordinates;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public void addVisitedCoordinate(Coordinate coordinate) {
        visitedCoordinates.add(coordinate);
    }

    public void addMineralCoordinate(Coordinate coordinate) {
        mineralCoordinates.add(coordinate);
    }

    public void addWaterCoordinate(Coordinate coordinate) {
        waterCoordinates.add(coordinate);
    }

    public void addAlienCoordinate(Coordinate coordinate) {
        alienCoordinates.add(coordinate);
    }

    public void addMountainCoordinate(Coordinate coordinate) {
        mountainCoordinates.add(coordinate);
    }

    public void addPitCoordinate(Coordinate coordinate) {
        pitCoordinates.add(coordinate);
    }
}
