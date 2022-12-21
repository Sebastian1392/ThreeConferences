package models;

import javax.swing.ImageIcon;

public class Assistant implements IOConference {

    private static final String ICON_PATH = "/images/assistantIcon.png";
    private static final String TYPE = "Assistant";

    private String assistantName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Assistant(String assistantName) {
        this.assistantName = assistantName;
        options = new OptionsCoferences[] { OptionsCoferences.DELETE_ASSISTANT, OptionsCoferences.GET_REPORT };
        icon = new ImageIcon(getClass().getResource(ICON_PATH));
    }

    @Override
    public boolean isFather() {
        return false;
    }

    @Override
    public String getName() {
        return assistantName;
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
        return assistantName;
    }
}
