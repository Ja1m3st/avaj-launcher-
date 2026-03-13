package ro.academyplus.avaj.simulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    
    private static BufferedWriter writer;

    public static void init() {
        try {
            writer = new BufferedWriter(new FileWriter("simulation.txt", false));
        } catch (IOException e) {
            System.out.println("Error creating simulation file: " + e.getMessage());
        }
    }

    public static void log(String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }
    
}
