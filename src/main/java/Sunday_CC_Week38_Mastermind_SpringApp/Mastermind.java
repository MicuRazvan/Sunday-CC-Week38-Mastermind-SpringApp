package Sunday_CC_Week38_Mastermind_SpringApp;

import java.util.*;

public class Mastermind {
    private String difficulty;
    private int columns;
    private List<String> secretCode;
    private List<Attempt> attempts = new ArrayList<>();
    private boolean won = false;

    public static final List<String> COLORS = Arrays.asList("Red", "Blue", "Green", "Yellow", "Orange", "Purple");

    public Mastermind(String difficulty) {
        this.difficulty = difficulty;
        this.columns = difficulty.equals("extra") ? 8 : 4;
        this.secretCode = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < columns; i++) {
            secretCode.add(COLORS.get(rand.nextInt(COLORS.size())));
        }
    }

    public void makeGuess(List<String> guess) {
        int blackPegs = 0;
        int whitePegs = 0;

        List<String> tempSecret = new ArrayList<>(secretCode);
        List<String> tempGuess = new ArrayList<>(guess);

        for (int i = 0; i < columns; i++) {
            if (tempGuess.get(i).equals(tempSecret.get(i))) {
                blackPegs++;
                tempSecret.set(i, null);
                tempGuess.set(i, null);
            }
        }

        for (int i = 0; i < columns; i++) {
            if (tempGuess.get(i) != null) {
                if (tempSecret.contains(tempGuess.get(i))) {
                    whitePegs++;
                    tempSecret.set(tempSecret.indexOf(tempGuess.get(i)), null);
                }
            }
        }

        attempts.add(0, new Attempt(guess, blackPegs, whitePegs));
        if (blackPegs == columns) this.won = true;
    }

    public String getDifficulty() { return difficulty; }
    public int getColumns() { return columns; }
    public List<Attempt> getAttempts() { return attempts; }
    public boolean isWon() { return won; }
    public List<String> getSecretCode() { return secretCode; }

    public record Attempt(List<String> colors, int blackPegs, int whitePegs) {}
}