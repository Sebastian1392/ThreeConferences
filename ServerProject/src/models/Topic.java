package models;

public class Topic implements IOConference{

    private static final String TYPE_CONFERENCE = "Topic";

    private String topicName;
    private OptionsCoferences[] options;

    public Topic(String topicName){
        this.topicName = topicName;
        options = new OptionsCoferences[]{OptionsCoferences.ADD_SUBTOPIC,OptionsCoferences.ADD_CONFERENCE,
            OptionsCoferences.DELETE_TOPIC,OptionsCoferences.GET_REPORT};
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
    public String getTag() {
        return TagConstants.TOPIC_TAG;
    }

    @Override
    public String typeConference() {
        return TYPE_CONFERENCE;
    }
}
