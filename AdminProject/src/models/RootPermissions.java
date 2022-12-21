package models;

import javax.swing.ImageIcon;

public class RootPermissions implements IPermission{

    private static final String ICON_PERMISSION_PATH = "/images/keyIcon.png";

    private String rootName;
    private ImageIcon icon;

    public RootPermissions(String rootName) {
        this.rootName = rootName;
        icon = new ImageIcon(getClass().getResource(ICON_PERMISSION_PATH));
    }

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return rootName;
    }

    @Override
    public String getTag() {
        return TagConstants.ROOT_TAG;
    }

    @Override
    public String toString() {
        return rootName;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }
     
}
