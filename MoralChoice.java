public class MoralChoice implements Choice {
    private String text;
    private int moralEffect;
    private String nextStoryId;

    public MoralChoice(String text, int moralEffect, String nextStoryId) {
        this.text = text;
        this.moralEffect = moralEffect;
        this.nextStoryId = nextStoryId;
    }

    public String getText() {
        return text;
    }

    public String choose(Player player) {
        player.addMoral(moralEffect);
        return nextStoryId;
    }
}
