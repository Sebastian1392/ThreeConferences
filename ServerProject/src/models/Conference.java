package models;

public class Conference implements IOConference{

    public static final String TYPE_CONFERENCE = "Conference";

    private String conferenceName;
    private OptionsCoferences[] options;
    
    public Conference(String conferenceName) {
        this.conferenceName = conferenceName;
        options = new OptionsCoferences[]{OptionsCoferences.ADD_LECTURER,OptionsCoferences.ADD_ASSISTANT,
            OptionsCoferences.DELETE_CONFERENCE,OptionsCoferences.GET_REPORT};
    }
        
    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return conferenceName;
    }

    @Override
    public OptionsCoferences[] getOptions() {
        return options;
    }

    @Override
    public String getTag() {
        return TagConstants.CONFERENCE_TAG;
    }

    @Override
    public String typeConference() {
        return TYPE_CONFERENCE;
    }
}
