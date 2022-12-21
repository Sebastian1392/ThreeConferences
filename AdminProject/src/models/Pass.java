package models;

public class Pass {

    private String nodeName;
    private OptionsCoferences[] optionsAvailables;
    

    public Pass(String nodeName, OptionsCoferences[] optionsAvailables) {
        this.nodeName = nodeName;
        this.optionsAvailables = optionsAvailables;
    }

    public OptionsCoferences[] getOptionsAvailables() {
        return optionsAvailables;
    }

    public void setOptionsAvailables(OptionsCoferences[] optionsAvailables) {
        this.optionsAvailables = optionsAvailables;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
