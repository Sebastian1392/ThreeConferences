package models;

public class Lecturer implements IOConference{

    private static final String TYPE_CONFERENCE = "Lecturer";

    private String lecturerName;
    private OptionsCoferences[] options;

    public Lecturer(String lecturerName){
        this.lecturerName = lecturerName;
        options = new OptionsCoferences[]{OptionsCoferences.DELETE_LECTURER,OptionsCoferences.GET_REPORT};
    }

    @Override
    public boolean isFather() {
        return false;
    }

    @Override
    public String getName() {
        return lecturerName;
    }

    @Override
    public OptionsCoferences[] getOptions() {
        return options;
    }

    @Override
    public String getTag() {
        return TagConstants.LECTURER_TAG;
    }

    @Override
    public String typeConference() {
        return TYPE_CONFERENCE;
    }
    
}
