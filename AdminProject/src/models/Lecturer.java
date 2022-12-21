package models;

import javax.swing.ImageIcon;

public class Lecturer implements IOConference {

    private static final String ICON_PATH = "/images/lecturerIcon.png";
    private static final String TYPE = "Lecturer";

    private String lecturerName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Lecturer(String lecturerName) {
        this.lecturerName = lecturerName;
        options = new OptionsCoferences[] { OptionsCoferences.DELETE_LECTURER, OptionsCoferences.GET_REPORT };
        icon = new ImageIcon(getClass().getResource(ICON_PATH));
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
    public String getType() {
        return TYPE;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return lecturerName;
    }
    
}
