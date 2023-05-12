package com.codecool.marsexploration;

import com.codecool.marsexploration.data.Context;
import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.Rover;
import com.codecool.marsexploration.data.SimulationInput;
import com.codecool.marsexploration.logic.ExplorationSimulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // Initialize the context variable as null, so it can be accessed in the finally block,
        // allowing us to safely close the log writer.
        Context context = null;

        try  {
            // Initialise all the necessary variables
            ExplorationSimulator simulator = new ExplorationSimulator();
            SimulationInput input = new SimulationInput(
                    "src/main/resources/exploration-3.map",
                    new Coordinate(12, 12),
                    100,
                    "src/main/resources/exploration-test.log");
            char[][] map = parseMapFromFile(input.mapPath());
            Rover rover = new Rover("Rover_1", input.landing(), 10);
            context = new Context(input.timeout(), map, input.landing(), rover, null);
            context.setLogWriter(input.logPath());
            simulator.setContext(context);

            // Run the simulation
            simulator.simulate(input);

        } catch (IOException e) {
          System.err.println("Error: Unable to parse the map from the file.");
          e.printStackTrace();
      } finally {
          if (context != null && context.getLogWriter() != null) {
              try {
                  context.getLogWriter().close();
              } catch (IOException e) {
                  System.err.println("Error: Unable to close the log writer.");
                  e.printStackTrace();
              }
          }
    }
}

    private static char[][] parseMapFromFile(String mapFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(mapFilePath));
        int rows = lines.size();
        int columns = lines.stream().mapToInt(String::length).max().orElse(0);
        char[][] map = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < columns; j++) {
                if (j < line.length()) {
                    map[i][j] = line.charAt(j);
                } else {
                    map[i][j] = ' '; // Fill the remaining spots with space characters
                }
            }
        }
        return map;
    }

}
