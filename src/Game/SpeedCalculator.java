package Game;
public class SpeedCalculator extends TypingComponent {
public double calculateWPM() {
    String typed = getTypedText();
    
    double timeMillis = getTimeTaken();
        if (timeMillis == 0 || typed == null) {
            return 0.0; 
        }

    int charCount = typed.length(); // includes spaces & punctuation
    double timeInMinutes = timeMillis / 60000.0;

    return (charCount / 5.0) / timeInMinutes; // standard wpm calculation

	}
}
