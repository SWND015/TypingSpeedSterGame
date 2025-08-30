package Game;

public class AccuracyCalculator extends TypingComponent {
  
  public double calculateAccuracy() {
      String typed = getTypedText();
      String target = getTargetText();
      //new line

      if (typed == null || typed.isEmpty()) return 0.0;

      int correctChars = 0;
      int minLength = Math.min(typed.length(), target.length());

      for (int i = 0; i < minLength; i++) {
          if (typed.charAt(i) == target.charAt(i)) {
              correctChars++;
          }
      }


      return ((double) correctChars / target.length()) * 100;
  }

  
}
