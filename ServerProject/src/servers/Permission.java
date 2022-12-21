package servers;

import models.OptionsCoferences;

public class Permission {

    private String nodeName;
    private OptionsCoferences options;

    public Permission(String nodeName, OptionsCoferences options) {
        this.nodeName = nodeName;
        this.options = options;
    }

    public OptionsCoferences getOptions() {
        return options;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setOptions(OptionsCoferences options) {
        this.options = options;
    }
}
