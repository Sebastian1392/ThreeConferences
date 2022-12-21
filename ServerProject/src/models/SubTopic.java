package models;

public class SubTopic implements IOConference{

    private static final String TYPE_CONFERENCE = "Subtopic";

    private String subTopicName;
    private OptionsCoferences[] options;
    
    public SubTopic(String subTopicName){
        this.subTopicName = subTopicName;
        options = new OptionsCoferences[]{OptionsCoferences.ADD_CONFERENCE,OptionsCoferences.DELETE_SUBTOPIC,
        OptionsCoferences.GET_REPORT};
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
    public String typeConference() {
        return TYPE_CONFERENCE;
    }
}
