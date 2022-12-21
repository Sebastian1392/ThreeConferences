package models;

import javax.swing.ImageIcon;

public class SubTopic implements IOConference {

    private static final String ICON_PATH = "/images/subtopicIcon.png";
    private static final OptionsCoferences[] ALL_OPTIONS = { OptionsCoferences.ADD_CONFERENCE, OptionsCoferences.DELETE_SUBTOPIC,
        OptionsCoferences.GET_REPORT };
    
        private String subTopicName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public SubTopic(String subTopicName) {
        this.subTopicName = subTopicName;
        options = new OptionsCoferences[] { OptionsCoferences.REQUEST_PERMISSIONS};
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
    public String getTag() {
        return TagConstants.SUB_TOPIC_TAG;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return subTopicName;
    }

    @Override
    public void setOptions(OptionsCoferences[] options) {
        this.options = options;
    }
    
    @Override
    public OptionsCoferences[] getAllOptions() {
        return ALL_OPTIONS;
    }
}
