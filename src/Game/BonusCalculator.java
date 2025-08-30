package Game;

public class BonusCalculator extends SpeedCalculator {
    private boolean bonusApplied = false;

    /**
     * Calculates a total score based on WPM and accuracy.
     * Bonus is applied only if accuracy is above 70%.
     */
    public double calculateScore(double wpm, double accuracy) {
        double score = wpm + accuracy; 

        if (accuracy > 70.0) {
            bonusApplied = true;
            score += 10; // Bonus points
        } else {
            bonusApplied = false;
        }

        return score;
    }

    public boolean isBonusApplied() {
        return bonusApplied;
    }
}