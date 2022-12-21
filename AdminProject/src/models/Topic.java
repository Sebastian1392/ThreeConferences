package models;

import javax.swing.ImageIcon;

public class Topic implements IOConference {

    private static final String ICON_PATH = "/images/topicIcon.png";
    private static final String TYPE = "Topic";

    private String topicName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Topic(String topicName) {
        this.topicName = topicName;
        options = new OptionsCoferences[] { OptionsCoferences.ADD_SUBTOPIC, OptionsCoferences.ADD_CONFERENCE,
                OptionsCoferences.DELETE_TOPIC, OptionsCoferences.GET_REPORT };
        icon = new ImageIcon(getClass().getResource(ICON_PATH));
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return topicName;
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
        return topicName;
    }
}
