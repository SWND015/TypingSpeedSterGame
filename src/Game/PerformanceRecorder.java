package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PerformanceRecorder {
    public static class Record {
       public double wpm;
       public double accuracy;
       public double score;

       public Record(double wpm, double accuracy, double score) {
            this.wpm = wpm;
            this.accuracy = accuracy;
            this.score = score;
        }
    }

    private List<Record> records = new ArrayList<>();

    public void addRecord(double wpm, double accuracy, double score) {
    	try (PrintWriter writer = new PrintWriter(new FileWriter("records.txt", true))) {

    	    String formattedWpm = String.format("%.2f", wpm);
    	    String formattedAccuracy = String.format("%.2f", accuracy);


    	    int userCount = 1;
    	    try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {
    	        while (reader.readLine() != null) {
    	            userCount++;
    	        }
    	    } catch (IOException ignored) {
    	        // file might not exist initially
    	    }

    	    writer.printf("Session %d: WPM: %s   Accuracy: %s%%%n", userCount, formattedWpm, formattedAccuracy);
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
        records.add(new Record(wpm, accuracy, score));
    }
    

    public List<Record> showRecords() {
    	
    	
        System.out.println("\n--- Typing Records ---");
        if (records.isEmpty()) {
            System.out.println("No records yet.");
            return records;
        }
        int i = 1;
        for (Record r : records) {
            System.out.printf("Session %d: WPM: %.2f | Accuracy: %.2f%% | Score: %.2f\n", i++, r.wpm, r.accuracy, r.score);
        }
        return new ArrayList<>(records);
    }

    public void clearRecords() {
        records.clear();
        System.out.println("All records have been cleared.");
    }
    public PerformanceRecorder() {
        // Load records from log.txt if it exists
        try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Example line: Session 1: WPM: 45.67   Accuracy: 98.00%
                String[] parts = line.split(":");
                if (parts.length >= 3) {
                    String[] wpmPart = parts[2].trim().split(" ");
                    String[] accPart = parts[3].trim().split(" ");
                    try {
                        double wpm = Double.parseDouble(wpmPart[1]);
                        double accuracy = Double.parseDouble(accPart[1].replace("%", ""));
                        // Score is not saved in file, so set to 0 or recalculate if needed
                        records.add(new Record(wpm, accuracy, 0));
                    } catch (Exception ignore) {}
                }
            }
        } catch (IOException ignored) {
            // File may not exist yet
        }
    }
}

