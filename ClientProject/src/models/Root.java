package models;

import javax.swing.ImageIcon;

public class Root implements IOConference {

    private static final String ICON_PATH = "/images/rootIcon.png";
    private static final OptionsCoferences[] ALL_OPTIONS = { OptionsCoferences.ADD_TOPIC };

    private String rootName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Root(String rootName) {
        this.rootName = rootName;
        options = new OptionsCoferences[] { OptionsCoferences.REQUEST_PERMISSIONS};
        icon = new ImageIcon(getClass().getResource(ICON_PATH));
    }

    public Root(String rootName, OptionsCoferences[] options) {
        this.rootName = rootName;
        this.options = options;
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
    public OptionsCoferences[] getOptions() {
        return options;
    }

    @Override
    public String getTag() {
        return TagConstants.ROOT_TAG;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
    
    @Override
    public String toString() {
        return rootName;
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
