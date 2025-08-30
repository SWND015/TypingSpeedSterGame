package Game;

public class TypingComponent {
    private String typedText;
    private String targetText;
    private double timeTaken;

    public String getTypedText() {
        return typedText;
    }

    public void setTypedText(String typedText) {
        this.typedText = typedText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }
}
