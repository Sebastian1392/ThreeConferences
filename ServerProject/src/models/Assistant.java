package models;

public class Assistant implements IOConference{

    private static final String TYPE_CONFERENCE = "Assistant";

    private String assistantName;
    private OptionsCoferences[] options;

    public Assistant(String assistantName) {
        this.assistantName = assistantName;
        options = new OptionsCoferences[]{OptionsCoferences.DELETE_ASSISTANT,OptionsCoferences.GET_REPORT};
    }

    @Override
    public boolean isFather() {
        return false;
    }

    @Override
    public String getName() {
        return assistantName;
    }

    @Override
    public OptionsCoferences[] getOptions() {
        return options;
    }

    @Override
    public String getTag() {
        return TagConstants.ASSISTANT_TAG;
    }

    @Override
    public String typeConference() {
        return TYPE_CONFERENCE;
    }
}
