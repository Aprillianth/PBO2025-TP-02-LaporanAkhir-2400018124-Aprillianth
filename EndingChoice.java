public class EndingChoice implements Choice {
    private String text;

    public EndingChoice(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String choose(Player player) {
        return "END";
    }
}
