import java.util.HashMap;

public class StoryManager {
    private HashMap<String, Story> stories = new HashMap<>();

    public void addStory(Story story) {
        stories.put(story.getId(), story);
    }

    public Story getStory(String id) {
        return stories.get(id);
    }
}
