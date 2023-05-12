package com.codecool.marsexploration.logic.routine;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ExploringRoutine  implements Routine {

    @Override
    public void move(Context context) {
        List<Coordinate> newNearbyMoves = find_new_nearby_moves(context);

        if (newNearbyMoves.isEmpty()) {
            // No new moves found nearby
            Coordinate nearestUnvisited = find_nearest_unvisited_coordinate(context);
            if (nearestUnvisited != null) {
                // Move towards the nearest unvisited coordinate in the rover's sight range
                Coordinate roverPosition = context.getRover().getPosition();
                Coordinate moveIncrement = calculate_move_towards_valid(roverPosition, nearestUnvisited);
                Coordinate newPosition = new Coordinate(roverPosition.x() + moveIncrement.x(), roverPosition.y() + moveIncrement.y());
                context.getRover().setPosition(newPosition);
                context.getRover().getVisitedCoordinates().add(newPosition);
            } else {
                // No unvisited coordinates found within the rover's sight range.
                // Move towards the furthest coordinate from the landing place.
                Coordinate furthestCoordinate = find_furthest_coordinate_from_landing(context);
                if (furthestCoordinate != null) {
                    context.getRover().setPosition(furthestCoordinate);
                    context.getRover().getVisitedCoordinates().add(furthestCoordinate);
                }
            }
        } else {
            // Pick a valid move from the list, e.g., by choosing the first one in the list
            Coordinate selectedMove = newNearbyMoves.get(0);
            context.getRover().setPosition(selectedMove);
            context.getRover().getVisitedCoordinates().add(selectedMove);
        }
    }

    private Coordinate find_nearest_unvisited_coordinate(Context context) {
        List<Coordinate> visitedCoordinates = context.getRover().getVisitedCoordinates();
        char[][] map = context.getMap();
        Coordinate position = context.getRover().getPosition();
        int sight = context.getRover().getSight();
        int nearestDistance = Integer.MAX_VALUE;
        Coordinate nearestUnvisited = null;

        int minX = Math.max(position.x() - sight, 0);
        int maxX = Math.min(position.x() + sight, map.length - 1);
        int minY = Math.max(position.y() - sight, 0);
        int maxY = Math.min(position.y() + sight, map[0].length - 1);

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                if (!visitedCoordinates.contains(coordinate) && map[i][j] == ' ') {
                    int distance = Math.abs(position.x() - i) + Math.abs(position.y() - j);
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestUnvisited = coordinate;
                    }
                }
            }
        }
        return nearestUnvisited;
    }

    private Coordinate calculate_move_towards_valid(Coordinate rover_position, Coordinate target_position) {
        int deltaX = target_position.x() - rover_position.x();
        int deltaY = target_position.y() - rover_position.y();
        int incrementX = 0;
        int incrementY = 0;

        if (deltaX != 0) {
            incrementX = deltaX > 0 ? 1 : -1;
        }
        if (deltaY != 0) {
            incrementY = deltaY > 0 ? 1 : -1;
        }

        return new Coordinate(incrementX, incrementY);
    }


    private Coordinate find_furthest_coordinate_from_landing(Context context) {
        List<Coordinate> newNearbyMoves = find_new_nearby_moves(context,true);
        Coordinate landingCoordinate = context.getLandingCoordinate();
        int maxDistance = -1;
        Coordinate furthestCoordinate = null;

        for (Coordinate coordinate : newNearbyMoves) {
            int distance = Math.abs(landingCoordinate.x() - coordinate.x()) + Math.abs(landingCoordinate.y() - coordinate.y());
            if (distance > maxDistance) {
                maxDistance = distance;
                furthestCoordinate = coordinate;
            }
        }
        return furthestCoordinate;
    }

    private List<Coordinate> find_new_nearby_moves(Context context,boolean includeVisited) {
        List<Coordinate> nearbyMoves = new ArrayList<>();
        List<Coordinate> visitedCoordinates = context.getRover().getVisitedCoordinates();
        char[][] map = context.getMap();
        Coordinate position = context.getRover().getPosition();
        int x = position.x();
        int y = position.y();

//Define possible move directions: up, down, left, right, up-left, up-right, down-left, down-right
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length) {
                Coordinate newCoordinate = new Coordinate(newX, newY);

                if (map[newX][newY] == ' ' && (includeVisited || !visitedCoordinates.contains(newCoordinate))) {
                    nearbyMoves.add(newCoordinate);
                }
            }
        }

        return nearbyMoves;
    }

    private List<Coordinate> find_new_nearby_moves(Context context) {
        return find_new_nearby_moves(context,false);
    }

}
