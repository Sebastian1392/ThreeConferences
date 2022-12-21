package models;

import javax.swing.ImageIcon;

public class Lecturer implements IOConference {

    private static final String ICON_PATH = "/images/lecturerIcon.png";
    private static final OptionsCoferences[] ALL_OPTIONS = { OptionsCoferences.DELETE_LECTURER, 
        OptionsCoferences.GET_REPORT };

    private String lecturerName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Lecturer(String lecturerName) {
        this.lecturerName = lecturerName;
        options = new OptionsCoferences[] { OptionsCoferences.REQUEST_PERMISSIONS};
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
    public String getTag() {
        return TagConstants.LECTURER_TAG;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return lecturerName;
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
