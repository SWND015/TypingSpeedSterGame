package Game;

import java.util.Scanner;

public class TypingSpeedster {

    private WordGenerator wordGenerator;
    private AccuracyCalculator accuracyCalculator;
    private BonusCalculator bonusCalculator;
    private Scanner scanner;
    private PerformanceRecorder performanceRecorder;

    public TypingSpeedster() {
        wordGenerator = new WordGenerator();
        accuracyCalculator = new AccuracyCalculator();
        bonusCalculator = new BonusCalculator();
        scanner = new Scanner(System.in);
        performanceRecorder = new PerformanceRecorder();
    }

    public void startTypingSession(int difficultyLevel, int words) {
        System.out.println("Get ready! Your sentence is being generated...");

        String sentenceToType = wordGenerator.generateSentence(difficultyLevel, words);
        System.out.println("\nType the following sentence:\n\n" + sentenceToType);
        
        System.out.println("\nPress enter and start typing below...");
        scanner.nextLine(); // storing that extra enter thats pressed to start typing
        

        long startTime = System.currentTimeMillis();
        String typedInput = scanner.nextLine();
        long endTime = System.currentTimeMillis();

        double timeTaken = endTime - startTime;

        // Set data in component
        accuracyCalculator.setTypedText(typedInput);
        accuracyCalculator.setTargetText(sentenceToType);
        accuracyCalculator.setTimeTaken(timeTaken);

        bonusCalculator.setTypedText(typedInput);
        bonusCalculator.setTargetText(sentenceToType);
        bonusCalculator.setTimeTaken(timeTaken);

        // Calculate metrics
        double wpm = bonusCalculator.calculateWPM();
        double accuracy = accuracyCalculator.calculateAccuracy();
        double finalScore = bonusCalculator.calculateScore(wpm, accuracy);

        // Record performance
        performanceRecorder.addRecord(wpm, accuracy, finalScore);

        // Display Results
        System.out.printf("\nTyping Results:\n");
        System.out.printf("Words per Minute (WPM): %.2f\n", wpm);
        System.out.printf("Accuracy: %.2f%%\n", accuracy);
        if (bonusCalculator.isBonusApplied()) {
            System.out.println("Bonus Applied: Yes (+10 points)");
        } else {
            System.out.println("Bonus Applied: No");
        }
        System.out.printf("Final Score: %.2f\n", finalScore);
    }

    public void showRecords() {
        performanceRecorder.showRecords();
    }

    public void clearRecords() {
        performanceRecorder.clearRecords();
    }

    public static void main(String[] args) {
        TypingSpeedster game = new TypingSpeedster();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Typing Speed Ster Game!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Start Typing Session");
            System.out.println("2. Show Records");
            System.out.println("3. Exit");
            System.out.println("4. Clear Records");
            System.out.print("Choose an option: ");
            String choice = input.nextLine();

            if (choice.equals("1")) {
                System.out.println("Choose difficulty level: 1 = Easy | 2 = Medium | 3 = Hard");
                int level = input.nextInt();
                System.out.println("Choose number of words to type: ");
                int wordCount = input.nextInt();
                input.nextLine(); 
                game.startTypingSession(level, wordCount);
            } else if (choice.equals("2")) {
                game.showRecords();
            } else if (choice.equals("3")) {
                System.out.println("Thank you for playing!");
                break;
            } else if (choice.equals("4")) {
                game.clearRecords();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
