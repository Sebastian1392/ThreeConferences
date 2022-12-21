package models;

import javax.swing.ImageIcon;

public class User implements IPermission {

    private static final String ICON_CONFERENCE_PATH = "/images/userIcon.png";

    private String userName;
    private ImageIcon icon;

    public User(String userName) {
        this.userName = userName;
        icon = new ImageIcon(getClass().getResource(ICON_CONFERENCE_PATH));
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public String getTag() {
        return TagConstants.USER_TAG;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }

    @Override
    public String toString() {
        return userName;
    }
}
