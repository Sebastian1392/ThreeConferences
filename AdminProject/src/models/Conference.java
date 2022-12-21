package models;

import javax.swing.ImageIcon;

public class Conference implements IOConference {

    private static final String ICON_PATH = "/images/conferenceIcon.png";
    private static final String TYPE = "Conference";

    private String conferenceName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Conference(String conferenceName) {
        this.conferenceName = conferenceName;
        options = new OptionsCoferences[] { OptionsCoferences.ADD_LECTURER, OptionsCoferences.ADD_ASSISTANT,
                OptionsCoferences.DELETE_CONFERENCE, OptionsCoferences.GET_REPORT };
        icon = new ImageIcon(getClass().getResource(ICON_PATH));
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
    public String getType() {
        return TYPE;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return conferenceName;
    }
}
