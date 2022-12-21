package models;

import javax.swing.ImageIcon;

public class Option implements IPermission {

    private static final String ICON_CONFERENCE_PATH = "/images/permissionIcon.png";

    private String optionName;
    private ImageIcon icon;

    public Option(String conferenceName) {
        this.optionName = conferenceName;
        icon = new ImageIcon(getClass().getResource(ICON_CONFERENCE_PATH));
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return optionName;
    }

    @Override
    public String getTag() {
        return TagConstants.OPTION_TAG;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }
    
    @Override
    public String toString() {
        return optionName;
    }
}
