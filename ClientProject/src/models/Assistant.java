package models;

import javax.swing.ImageIcon;

public class Assistant implements IOConference {

    private static final String ICON_PATH = "/images/assistantIcon.png";
    private static final OptionsCoferences[] ALL_OPTIONS = {OptionsCoferences.DELETE_ASSISTANT, 
        OptionsCoferences.GET_REPORT };

    private String assistantName;
    private OptionsCoferences[] options;
    private ImageIcon icon;

    public Assistant(String assistantName) {
        this.assistantName = assistantName;
        options = new OptionsCoferences[] { OptionsCoferences.REQUEST_PERMISSIONS};
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
    public String getTag() {
        return TagConstants.ASSISTANT_TAG;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return assistantName;
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
