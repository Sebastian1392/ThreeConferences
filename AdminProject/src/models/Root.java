package models;

import javax.swing.ImageIcon;

public class Root implements IOConference{

    private static final String ICON_CONFERENCE_PATH = "/images/rootIcon.png";
    private static final String TYPE = "Root";

    private String rootName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Root(String rootName) {
        this.rootName = rootName;
        options = new OptionsCoferences[] { OptionsCoferences.ADD_TOPIC };
        icon = new ImageIcon(getClass().getResource(ICON_CONFERENCE_PATH));
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
    public String getType() {
        return TYPE;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
    
    @Override
    public String toString() {
        return rootName;
    }
}
