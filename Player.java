public class Player {
    private int moralScore = 0;

    public void addMoral(int value) {
        moralScore += value;
    }

    public int getMoralScore() {
        return moralScore;
    }
}
