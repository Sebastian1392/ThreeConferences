package models;

import javax.swing.ImageIcon;

public class ConferencesPermission implements IPermission {

    private static final String ICON_CONFERENCE_PATH = "/images/topicIcon.png";

    private String conferenceName;
    private ImageIcon icon;

    public ConferencesPermission(String conferenceName) {
        this.conferenceName = conferenceName;
        icon = new ImageIcon(getClass().getResource(ICON_CONFERENCE_PATH));
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
    public String getTag() {
        return TagConstants.CONFERENCES_PERMISSION_TAG;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }

    @Override
    public String toString() {
        return conferenceName;
    }
    
}
