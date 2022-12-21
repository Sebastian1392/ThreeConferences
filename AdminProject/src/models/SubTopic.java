package models;

import javax.swing.ImageIcon;

public class SubTopic implements IOConference {

    private static final String ICON_PATH = "/images/subtopicIcon.png";
    private static final String TYPE = "Subtopic";

    private String subTopicName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public SubTopic(String subTopicName) {
        this.subTopicName = subTopicName;
        options = new OptionsCoferences[] { OptionsCoferences.ADD_CONFERENCE, OptionsCoferences.DELETE_SUBTOPIC,
                OptionsCoferences.GET_REPORT };
        icon = new ImageIcon(getClass().getResource(ICON_PATH));
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return subTopicName;
    }

    @Override
    public OptionsCoferences[] getOptions() {
        return options;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return subTopicName;
    }
    
}
