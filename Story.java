import java.util.ArrayList;

public class Story {
    private String id;
    private String title;
    private String description;
    private ArrayList<Choice> choices = new ArrayList<>();

    public Story(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void addChoice(Choice choice) {
        choices.add(choice);
    }

    public Choice getChoice(int index) {
        return choices.get(index);
    }
}
